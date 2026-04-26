package com.example.ryservice.util;

import com.example.ryservice.entity.GbOrderEntity;
import com.example.ryservice.entity.SystemDictionaryEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 瑞云订单字段转换辅助组件。
 */
@Component
public class RyOrderConvertHelper {

    private final ObjectMapper objectMapper;
    private final CityCodeUtil cityCodeUtil;

    /**
     * 创建瑞云订单字段转换辅助组件。
     *
     * @param objectMapper JSON 解析器
     * @param cityCodeUtil 地区编码转换工具
     */
    public RyOrderConvertHelper(ObjectMapper objectMapper, CityCodeUtil cityCodeUtil) {
        this.objectMapper = objectMapper;
        this.cityCodeUtil = cityCodeUtil;
    }

    /**
     * 计算并写回省市区编码。
     *
     * @param order 工单实体
     */
    public void applyCityCodes(GbOrderEntity order) {
        order.setProvinceCode("0");
        order.setCityCode("0");
        order.setAreaCode("0");

        String cityCode = cityCodeUtil.getCityCode(order.getProvinceName(), order.getCityName(), order.getAreaName());
        if (cityCode == null || cityCode.trim().isEmpty()) {
            return;
        }
        String[] codeArray = cityCode.split(",");
        if (codeArray.length >= 1) {
            order.setProvinceCode(codeArray[0]);
        }
        if (codeArray.length >= 2) {
            order.setCityCode(codeArray[1]);
        }
        if (codeArray.length >= 3) {
            order.setAreaCode(codeArray[2]);
        }
    }

    /**
     * 将瑞云工单类型编码转换为系统工单类型名称。
     *
     * @param type 瑞云工单类型编码
     * @return 系统工单类型名称
     */
    public String resolveWorkOrderType(int type) {
        if (type == 140) {
            return "勘测工单";
        }
        if (type == 2) {
            return "安装工单";
        }
        return "维修工单";
    }

    /**
     * 根据工单类型名称匹配系统字典编码。
     *
     * @param workOrderType 工单类型名称
     * @param dictionaryList 工单类型字典集合
     * @return 系统工单类型编码
     */
    public String resolveWorkOrderTypeCode(String workOrderType, List<SystemDictionaryEntity> dictionaryList) {
        if (workOrderType == null || dictionaryList == null) {
            return null;
        }
        for (SystemDictionaryEntity dictionary : dictionaryList) {
            if (dictionary.getName() != null && dictionary.getName().trim().equals(workOrderType)) {
                return dictionary.getNumber();
            }
        }
        return null;
    }

    /**
     * 将 JSON 节点转换为 `Date`。
     *
     * @param node 时间节点
     * @return 转换后的时间，失败时返回 `null`
     */
    public Date toDate(JsonNode node) {
        if (node == null || node.isMissingNode() || node.isNull()) {
            return null;
        }
        try {
            return objectMapper.convertValue(node, Date.class);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    /**
     * 读取 JSON 节点中的字符串字段。
     *
     * @param node JSON 节点
     * @param fieldName 字段名
     * @return 字段值，不存在时返回空字符串
     */
    public String text(JsonNode node, String fieldName) {
        JsonNode value = node.path(fieldName);
        return value.isMissingNode() || value.isNull() ? "" : value.asText("");
    }

    /**
     * 兼容直辖市场景下的城市名称。
     *
     * @param provinceName 省份名称
     * @param cityName 城市名称
     * @return 归一化后的城市名称
     */
    public String normalizedCityName(String provinceName, String cityName) {
        if ("市辖区".equals(cityName)) {
            return provinceName;
        }
        return cityName;
    }

    /**
     * 清洗地址中重复的省市区前缀。
     *
     * @param address 原始详细地址
     * @param provinceName 省份名称
     * @param cityName 城市名称
     * @param areaName 区县名称
     * @return 清洗后的详细地址
     */
    public String cleanAddress(String address, String provinceName, String cityName, String areaName) {
        String safeAddress = defaultString(address);
        String province = defaultString(provinceName);
        String city = defaultString(cityName);
        String area = defaultString(areaName);
        String[] prefixes = {
                province + city + area,
                province + "-" + city + "-" + area,
                province + "," + city + "," + area,
                province.replace("省", "") + city + area,
                province.replace("省", "") + "-" + city + "-" + area,
                province.replace("省", "") + "," + city + "," + area
        };
        for (String prefix : prefixes) {
            if (!prefix.isEmpty()) {
                safeAddress = safeAddress.replace(prefix, "");
            }
        }
        safeAddress = safeAddress.replace("中国", "").replace("中国大陆", "");
        if (safeAddress.startsWith(",")) {
            safeAddress = safeAddress.substring(1);
        }
        if (safeAddress.startsWith("-")) {
            safeAddress = safeAddress.substring(1);
        }
        return safeAddress.trim();
    }

    /**
     * 拼接完整地址。
     *
     * @param provinceName 省份名称
     * @param cityName 城市名称
     * @param areaName 区县名称
     * @param address 详细地址
     * @return 完整地址
     */
    public String joinAddress(String provinceName, String cityName, String areaName, String address) {
        return String.join(" ",
                defaultString(provinceName),
                defaultString(cityName),
                defaultString(areaName),
                defaultString(address)).trim().replaceAll("\\s+", " ");
    }

    /**
     * 返回第一个非空白字符串。
     *
     * @param values 候选字符串数组
     * @return 第一个非空白字符串，若都为空则返回空字符串
     */
    public String firstNotBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
        }
        return "";
    }

    /**
     * 将可能为 `null` 的字符串转换为空字符串。
     *
     * @param value 原始字符串
     * @return 安全字符串
     */
    public String defaultString(String value) {
        return value == null ? "" : value;
    }
}
