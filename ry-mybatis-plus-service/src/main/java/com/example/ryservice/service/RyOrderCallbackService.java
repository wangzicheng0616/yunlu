package com.example.ryservice.service;

import cn.hutool.http.HttpRequest;
import com.example.ryservice.config.RyOpenApiProperties;
import com.example.ryservice.entity.GbOrderEntity;
import com.example.ryservice.mapper.GbOrderMapper;
import com.example.ryservice.util.RySignUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 瑞云订单回传服务。
 */
@Service
public class RyOrderCallbackService {

    private static final Logger log = LoggerFactory.getLogger(RyOrderCallbackService.class);

    private final RyOpenApiProperties properties;
    private final GbOrderMapper gbOrderMapper;
    private final ObjectMapper objectMapper;

    /**
     * 创建瑞云订单回传服务。
     *
     * @param properties 瑞云开放平台配置
     * @param gbOrderMapper 工单表 Mapper
     * @param objectMapper JSON 解析器
     */
    public RyOrderCallbackService(RyOpenApiProperties properties,
                                  GbOrderMapper gbOrderMapper,
                                  ObjectMapper objectMapper) {
        this.properties = properties;
        this.gbOrderMapper = gbOrderMapper;
        this.objectMapper = objectMapper;
    }

    /**
     * 按本地订单信息回传瑞云订单。
     *
     * @param orderId 本地工单主键
     */
    public void updateRyInfo(Long orderId) {
        GbOrderEntity gbOrder = getValidOrder(orderId);
        log.info("开始回传瑞云订单信息: orderId={}, orderNo={}, promoter={}",
                gbOrder.getId(),
                gbOrder.getOrderNo(),
                gbOrder.getPromoter());

        ObjectNode ryOrder = fetchRyOrderByPromoter(gbOrder.getPromoter());
        applyOrderUpdates(ryOrder, gbOrder);
        saveRyOrder(ryOrder, gbOrder);

        log.info("瑞云订单信息回传成功: orderId={}, orderNo={}, promoter={}",
                gbOrder.getId(),
                gbOrder.getOrderNo(),
                gbOrder.getPromoter());
    }

    /**
     * 查询并校验本地工单。
     *
     * @param orderId 本地工单主键
     * @return 合法的本地工单
     */
    private GbOrderEntity getValidOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("订单ID不能为空");
        }

        GbOrderEntity gbOrder = gbOrderMapper.selectById(orderId);
        if (gbOrder == null) {
            throw new IllegalStateException("没有找到订单");
        }
        if (isBlank(gbOrder.getPromoter())) {
            throw new IllegalStateException("订单中[甲方工单号]信息异常");
        }
        if (isBlank(gbOrder.getSupplyName()) || !"云鹿".equals(gbOrder.getSupplyName().trim())) {
            throw new IllegalStateException("订单中[甲方]为非【云鹿】,请更换订单重试。");
        }
        if (isBlank(gbOrder.getOperationMemo()) && gbOrder.getServiceTime() == null) {
            throw new IllegalStateException("订单中[运营备注和实际上门时间不能同时为空]");
        }
        return gbOrder;
    }

    /**
     * 按甲方工单号查询瑞云订单。
     *
     * @param promoter 甲方工单号
     * @return 瑞云订单对象
     */
    private ObjectNode fetchRyOrderByPromoter(String promoter) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("$paging", "true");
            params.put("$pageSize", "20");
            params.put("$pageIndex", "1");
            params.put("$filter", "(new_name eq '" + escapeFilterValue(promoter.trim()) + "')");

            String signedUrl = RySignUtil.signUrl(buildDynamicUrl(), properties.getTenant(), properties.getAppId(), properties.getSecretKey(), params);
            String result = HttpRequest.get(signedUrl).execute().body();
            JsonNode root = objectMapper.readTree(result);

            int errorCode = root.path("ErrorCode").asInt(-1);
            String message = root.path("Message").asText("");
            if (errorCode != 0) {
                throw new IllegalStateException("云鹿订单查询异常：" + message);
            }

            JsonNode entities = root.path("Data").path("Entities");
            if (!entities.isArray() || entities.size() == 0) {
                throw new IllegalStateException("没有找到云鹿订单，请检查云鹿订单号");
            }
            return (ObjectNode) entities.get(0).deepCopy();
        } catch (Exception ex) {
            throw new IllegalStateException("查询瑞云订单失败: " + ex.getMessage(), ex);
        }
    }

    /**
     * 将本地工单的回传字段写入瑞云订单对象。
     *
     * @param ryOrder 瑞云订单对象
     * @param gbOrder 本地工单
     */
    private void applyOrderUpdates(ObjectNode ryOrder, GbOrderEntity gbOrder) {
        if (!isBlank(gbOrder.getOperationMemo())) {
            ryOrder.put("wisedeer_wdgc", gbOrder.getOperationMemo().trim());
        }
        if (gbOrder.getServiceTime() != null) {
            ryOrder.putPOJO("new_appointmenttime", gbOrder.getServiceTime());
        }
    }

    /**
     * 调用瑞云保存接口提交更新。
     *
     * @param ryOrder 瑞云订单对象
     * @param gbOrder 本地工单
     */
    private void saveRyOrder(ObjectNode ryOrder, GbOrderEntity gbOrder) {
        try {
            String signedUrl = RySignUtil.signUrl(buildSaveUrl(), properties.getTenant(), properties.getAppId(), properties.getSecretKey(), new LinkedHashMap<>());
            String requestBody = objectMapper.writeValueAsString(ryOrder);
            String result = HttpRequest.post(signedUrl)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .execute()
                    .body();

            JsonNode root = objectMapper.readTree(result);
            int errorCode = root.path("ErrorCode").asInt(-1);
            String message = root.path("Message").asText("");
            if (errorCode != 0) {
                log.error("瑞云订单回传失败: orderId={}, orderNo={}, promoter={}, message={}",
                        gbOrder.getId(),
                        gbOrder.getOrderNo(),
                        gbOrder.getPromoter(),
                        message);
                throw new IllegalStateException("云鹿订单更新异常：" + message);
            }
        } catch (Exception ex) {
            log.error("调用瑞云保存接口失败: orderId={}, orderNo={}, promoter={}",
                    gbOrder.getId(),
                    gbOrder.getOrderNo(),
                    gbOrder.getPromoter(),
                    ex);
            throw new IllegalStateException("回传瑞云订单失败: " + ex.getMessage(), ex);
        }
    }

    /**
     * 构造瑞云动态实体查询地址。
     *
     * @return 查询地址
     */
    private String buildDynamicUrl() {
        return properties.getBaseUrl()
                + "/t/"
                + properties.getTenant()
                + "/open/api/dynamic/"
                + properties.getEntityName();
    }

    /**
     * 构造瑞云动态实体保存地址。
     *
     * @return 保存地址
     */
    private String buildSaveUrl() {
        return buildDynamicUrl() + "/save";
    }

    /**
     * 转义筛选表达式中的单引号。
     *
     * @param value 原始值
     * @return 转义后的值
     */
    private String escapeFilterValue(String value) {
        return value.replace("'", "''");
    }

    /**
     * 判断字符串是否为空白。
     *
     * @param value 待判断字符串
     * @return 是否为空白
     */
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
