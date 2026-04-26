package com.example.ryservice.service;

import cn.hutool.http.HttpRequest;
import com.example.ryservice.entity.GbOrderEntity;
import com.example.ryservice.entity.SystemDictionaryEntity;
import com.example.ryservice.config.RyOpenApiProperties;
import com.example.ryservice.model.RyOrderSyncResult;
import com.example.ryservice.mapper.GbOrderMapper;
import com.example.ryservice.mapper.SystemDictionaryMapper;
import com.example.ryservice.util.RyOrderConvertHelper;
import com.example.ryservice.util.RySignUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 瑞云订单服务，负责拉取瑞云工单、转换展示模型并按旧系统规则写入 `gb_order`。
 */
@Service
public class RyOrderService {

    private static final Logger log = LoggerFactory.getLogger(RyOrderService.class);

    private final RyOpenApiProperties properties;
    private final ObjectMapper objectMapper;
    private final RyOrderConvertHelper ryOrderConvertHelper;
    private final GbOrderMapper gbOrderMapper;
    private final SystemDictionaryMapper systemDictionaryMapper;
    private final SystemOrderNumberService systemOrderNumberService;

    /**
     * 创建瑞云订单服务。
     *
     * @param properties 瑞云开放平台配置
     * @param objectMapper JSON 解析器
     * @param ryOrderConvertHelper 瑞云订单字段转换辅助组件
     * @param gbOrderMapper 工单表 Mapper
     * @param systemDictionaryMapper 字典表 Mapper
     * @param systemOrderNumberService 单号生成服务
     */
    public RyOrderService(RyOpenApiProperties properties,
                          ObjectMapper objectMapper,
                          RyOrderConvertHelper ryOrderConvertHelper,
                          GbOrderMapper gbOrderMapper,
                          SystemDictionaryMapper systemDictionaryMapper,
                          SystemOrderNumberService systemOrderNumberService) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.ryOrderConvertHelper = ryOrderConvertHelper;
        this.gbOrderMapper = gbOrderMapper;
        this.systemDictionaryMapper = systemDictionaryMapper;
        this.systemOrderNumberService = systemOrderNumberService;
    }

    /**
     * 拉取瑞云订单并写入 `gb_order`。
     *
     * @param pageIndex 页码，从 1 开始
     * @param pageSize 每页条数
     * @param filter 瑞云筛选条件
     * @return 同步结果
     */
    @Transactional(rollbackFor = Exception.class)
    public RyOrderSyncResult syncOrders(Integer pageIndex, Integer pageSize, String filter) {
        int resolvedPageIndex = resolvePageIndex(pageIndex);
        int resolvedPageSize = resolvePageSize(pageSize);
        log.info("开始同步瑞云订单: pageIndex={}, pageSize={}, filter={}", resolvedPageIndex, resolvedPageSize, resolveFilter(filter));
        String body = requestOrders(resolvedPageIndex, resolvedPageSize, filter);
        RyOrderSyncResult result = persistOrders(body);
        log.info("瑞云订单同步完成: total={}, saved={}, skipped={}",
                result.getTotal(),
                result.getSaved(),
                result.getSkipped());
        return result;
    }

    /**
     * 请求瑞云开放平台订单接口。
     *
     * @param pageIndex 已归一化的页码
     * @param pageSize 已归一化的每页条数
     * @param filter 原始筛选条件
     * @return 瑞云接口原始响应
     */
    private String requestOrders(int pageIndex, int pageSize, String filter) {
        Map<String, Object> params = buildRequestParameters(pageIndex, pageSize, filter);
        String signedUrl = buildSignedUrl(params);
        return HttpRequest.get(signedUrl).execute().body();
    }

    /**
     * 构造瑞云接口请求参数。
     *
     * @param pageIndex 已归一化的页码
     * @param pageSize 已归一化的每页条数
     * @param filter 原始筛选条件
     * @return 请求参数集合
     */
    private Map<String, Object> buildRequestParameters(int pageIndex, int pageSize, String filter) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("$paging", "true");
        params.put("$pageSize", pageSize);
        params.put("$pageIndex", pageIndex);
        params.put("$filter", resolveFilter(filter));
        return params;
    }

    /**
     * 生成带签名的瑞云接口地址。
     *
     * @param params 请求参数集合
     * @return 完整请求地址
     */
    private String buildSignedUrl(Map<String, Object> params) {
        String url = properties.getBaseUrl()
                + "/t/"
                + properties.getTenant()
                + "/open/api/dynamic/"
                + properties.getEntityName();
        return RySignUtil.signUrl(
                url,
                properties.getTenant(),
                properties.getAppId(),
                properties.getSecretKey(),
                params
        );
    }

    /**
     * 归一化页码参数。
     *
     * @param pageIndex 原始页码
     * @return 合法页码
     */
    private int resolvePageIndex(Integer pageIndex) {
        return pageIndex == null || pageIndex < 1 ? 1 : pageIndex;
    }

    /**
     * 归一化分页大小参数。
     *
     * @param pageSize 原始分页大小
     * @return 合法分页大小
     */
    private int resolvePageSize(Integer pageSize) {
        return pageSize == null || pageSize < 1 ? properties.getDefaultPageSize() : pageSize;
    }

    /**
     * 归一化筛选条件参数。
     *
     * @param filter 原始筛选条件
     * @return 合法筛选条件
     */
    private String resolveFilter(String filter) {
        return filter == null || filter.trim().isEmpty()
                ? properties.getDefaultFilter()
                : filter.trim();
    }

    /**
     * 解析瑞云返回结果并完成数据库写入。
     *
     * @param body 瑞云接口原始响应
     * @return 同步结果
     */
    private RyOrderSyncResult persistOrders(String body) {
        try {
            JsonNode root = objectMapper.readTree(body);
            int errorCode = root.path("ErrorCode").asInt(-1);
            String message = root.path("Message").asText("");
            if (errorCode != 0) {
                throw new IllegalStateException("瑞云接口调用失败: " + message);
            }

            List<SystemDictionaryEntity> dictionaryList = systemDictionaryMapper.selectByParentNumber("WORK_ORDER_TYPE");
            SystemDictionaryEntity orderLevel = systemDictionaryMapper.selectByNumber("WORK_ORDER_LEVEL_1");
            JsonNode entities = root.path("Data").path("Entities");

            RyOrderSyncResult result = new RyOrderSyncResult();
            if (!entities.isArray()) {
                return result;
            }

            for (JsonNode item : entities) {
                result.setTotal(result.getTotal() + 1);
                String promoter = ryOrderConvertHelper.text(item, "new_name");
                if (promoter.isEmpty()) {
                    result.setSkipped(result.getSkipped() + 1);
                    continue;
                }
                if (gbOrderMapper.selectByPromoter(promoter) != null) {
                    log.info("瑞云订单重复，跳过写入数据库: promoter={}", promoter);
                    result.setSkipped(result.getSkipped() + 1);
                    continue;
                }

                GbOrderEntity order = buildOrder(item, dictionaryList, orderLevel);
                try {
                    gbOrderMapper.insert(order);
                    log.info("瑞云订单已插入数据库: orderNo={}, promoter={}, userName={}, userTelephone={}, fullAddress={}",
                            order.getOrderNo(),
                            order.getPromoter(),
                            order.getUserName(),
                            order.getUserTelephone(),
                            order.getFullAddress());
                } catch (Exception ex) {
                    log.error("瑞云订单写入数据库失败: orderNo={}, promoter={}, userName={}, userTelephone={}, fullAddress={}",
                            order.getOrderNo(),
                            order.getPromoter(),
                            order.getUserName(),
                            order.getUserTelephone(),
                            order.getFullAddress(),
                            ex);
                    throw ex;
                }
                result.setSaved(result.getSaved() + 1);
                result.getOrderNos().add(order.getOrderNo());
            }
            return result;
        } catch (Exception ex) {
            throw new IllegalStateException("瑞云订单写入数据库失败: " + ex.getMessage(), ex);
        }
    }

    /**
     * 按旧系统口径构建 `gb_order` 实体。
     *
     * @param item 瑞云订单节点
     * @param dictionaryList 工单类型字典集合
     * @param orderLevel 工单级别字典
     * @return 待写入的工单实体
     */
    private GbOrderEntity buildOrder(JsonNode item,
                                     List<SystemDictionaryEntity> dictionaryList,
                                     SystemDictionaryEntity orderLevel) {
        Date now = new Date();
        GbOrderEntity order = new GbOrderEntity();
        order.setOrderNo(systemOrderNumberService.getGbOrderNo());
        order.setPromoter(ryOrderConvertHelper.text(item, "new_name"));
        order.setWorkOrderType(ryOrderConvertHelper.resolveWorkOrderType(item.path("new_type").asInt()));
        order.setWorkOrderTypeCode(ryOrderConvertHelper.resolveWorkOrderTypeCode(order.getWorkOrderType(), dictionaryList));
        order.setUserName(ryOrderConvertHelper.firstNotBlank(
                ryOrderConvertHelper.text(item, "new_contact_idname"),
                ryOrderConvertHelper.text(item, "new_contact")));
        order.setUserTelephone(ryOrderConvertHelper.firstNotBlank(
                ryOrderConvertHelper.text(item, "new_phone"),
                ryOrderConvertHelper.text(item, "new_feedbacktel")));
        order.setProvinceName(ryOrderConvertHelper.text(item, "new_province_idname"));
        order.setCityName(ryOrderConvertHelper.normalizedCityName(
                order.getProvinceName(),
                ryOrderConvertHelper.text(item, "new_city_idname")));
        order.setAreaName(ryOrderConvertHelper.text(item, "new_county_idname"));
        order.setAddress(ryOrderConvertHelper.cleanAddress(
                ryOrderConvertHelper.text(item, "new_address"),
                order.getProvinceName(),
                order.getCityName(),
                order.getAreaName()));
        order.setFullAddress(ryOrderConvertHelper.joinAddress(
                order.getProvinceName(),
                order.getCityName(),
                order.getAreaName(),
                order.getAddress()));
        ryOrderConvertHelper.applyCityCodes(order);
        order.setOrderLevelName(orderLevel == null ? null : orderLevel.getName());
        order.setOrderLevelCode(orderLevel == null ? null : orderLevel.getNumber());
        order.setOrderLevelValue(orderLevel == null || orderLevel.getVal() == null ? null : new BigDecimal(orderLevel.getVal()));
        order.setReqDate(ryOrderConvertHelper.toDate(item.path("new_appointmenttime")));
        order.setWorkRemark(ryOrderConvertHelper.text(item, "new_memo"));
        order.setSupplyName("云鹿");
        order.setStatus(0L);
        order.setOrderDate(now);
        order.setCreateTime(now);
        order.setUpdateTime(now);
        order.setDelFlag(0L);
        order.setIsPay(0L);
        order.setOrderType(7L);
        order.setUserEvaluate(-1L);
        order.setEvaluate(-1L);
        order.setResponseTime(BigDecimal.ZERO);
        order.setUserId(0L);
        order.setWorkId(0L);
        order.setOperationUserId(0L);
        order.setCreaterId(0L);
        order.setUpdaterId(0L);
        order.setMasterAmount(BigDecimal.ZERO);
        order.setConsumablesAmount(BigDecimal.ZERO);
        order.setEmptyAmount(BigDecimal.ZERO);
        return order;
    }
}
