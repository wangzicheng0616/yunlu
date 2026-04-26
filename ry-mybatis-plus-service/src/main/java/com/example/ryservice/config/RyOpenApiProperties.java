package com.example.ryservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ry.openapi")
public class RyOpenApiProperties {

    private String baseUrl = "https://ap9.fscloud.com.cn";
    private String tenant = "wisedeer";
    private String appId = "lt_ry";
    private String secretKey = "K7isfXwAhA69opaUzETJiXPVZiXp1i19iTdAu0kh";
    private String entityName = "new_srv_workorder";
    private String defaultFilter = "";
    private int defaultPageSize = 20;

    /**
     * 获取baseUrl。
     *
     * @return baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * 设置baseUrl。
     *
     * @param baseUrl baseUrl
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * 获取tenant。
     *
     * @return tenant
     */
    public String getTenant() {
        return tenant;
    }

    /**
     * 设置tenant。
     *
     * @param tenant tenant
     */
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    /**
     * 获取appId。
     *
     * @return appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置appId。
     *
     * @param appId appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 获取secretKey。
     *
     * @return secretKey
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * 设置secretKey。
     *
     * @param secretKey secretKey
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * 获取entityName。
     *
     * @return entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * 设置entityName。
     *
     * @param entityName entityName
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * 获取defaultFilter。
     *
     * @return defaultFilter
     */
    public String getDefaultFilter() {
        return defaultFilter;
    }

    /**
     * 设置defaultFilter。
     *
     * @param defaultFilter defaultFilter
     */
    public void setDefaultFilter(String defaultFilter) {
        this.defaultFilter = defaultFilter;
    }

    /**
     * 获取defaultPageSize。
     *
     * @return defaultPageSize
     */
    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    /**
     * 设置defaultPageSize。
     *
     * @param defaultPageSize defaultPageSize
     */
    public void setDefaultPageSize(int defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }
}
