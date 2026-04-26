package com.example.ryservice.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 地区编码转换工具。
 */
@Component
public class CityCodeUtil {

    private final JsonNode cityTree;

    /**
     * 加载地区编码资源。
     *
     * @param objectMapper JSON 解析器
     */
    public CityCodeUtil(ObjectMapper objectMapper) {
        try (InputStream inputStream = new ClassPathResource("json/city.json").getInputStream()) {
            this.cityTree = objectMapper.readTree(inputStream);
        } catch (Exception ex) {
            throw new IllegalStateException("加载 city.json 失败", ex);
        }
    }

    /**
     * 根据省市区名称查询编码串。
     *
     * @param provinceName 省份名称
     * @param cityName 城市名称
     * @param areaName 区县名称
     * @return 以逗号分隔的省市区编码
     */
    public String getCityCode(String provinceName, String cityName, String areaName) {
        if (provinceName == null || cityName == null || areaName == null) {
            return "";
        }
        for (JsonNode province : cityTree) {
            if (!provinceName.equals(province.path("text").asText())) {
                continue;
            }
            StringBuilder code = new StringBuilder(province.path("value").asText(""));
            for (JsonNode city : province.path("children")) {
                if (!cityName.equals(city.path("text").asText())) {
                    continue;
                }
                code.append(",").append(city.path("value").asText(""));
                for (JsonNode area : city.path("children")) {
                    if (areaName.equals(area.path("text").asText())) {
                        code.append(",").append(area.path("value").asText(""));
                        return code.toString();
                    }
                }
                return code.toString();
            }
            return code.toString();
        }
        return "";
    }
}
