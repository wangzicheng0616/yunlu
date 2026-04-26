package com.example.ryservice.util;

import cn.hutool.core.util.URLUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * 瑞云开放平台签名工具。
 */
public final class RySignUtil {

    /**
     * 禁止实例化工具类。
     */
    private RySignUtil() {
    }

    /**
     * 对瑞云请求地址进行签名并拼接查询参数。
     *
     * @param url 请求地址
     * @param tenant 租户编码
     * @param appId 应用编号
     * @param secretKey 密钥
     * @param params 业务参数
     * @return 带签名的请求地址
     */
    public static String signUrl(String url,
                                 String tenant,
                                 String appId,
                                 String secretKey,
                                 Map<String, Object> params) {
        Map<String, Object> signedParams = new LinkedHashMap<>();
        signedParams.put("$tenant", tenant);
        signedParams.put("$timestamp", System.currentTimeMillis());
        signedParams.put("$reqid", UUID.randomUUID().toString());
        signedParams.put("$appid", appId);
        if (params != null && !params.isEmpty()) {
            signedParams.putAll(params);
        }
        signedParams.put("$sign", buildSign(signedParams, secretKey));
        String separator = url.endsWith("?") ? "" : "?";
        return url + separator + URLUtil.buildQuery(signedParams, StandardCharsets.UTF_8);
    }

    /**
     * 生成瑞云请求签名。
     *
     * @param params 待签名参数
     * @param secretKey 密钥
     * @return 签名结果
     */
    private static String buildSign(Map<String, Object> params, String secretKey) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder content = new StringBuilder();
        for (String key : keys) {
            Object value = params.get(key);
            if (value != null) {
                content.append(value);
            }
        }
        content.append(secretKey);
        return sha256(content.toString()).toUpperCase(Locale.ROOT);
    }

    /**
     * 计算字符串的 SHA-256 摘要。
     *
     * @param value 原始字符串
     * @return 十六进制摘要字符串
     */
    private static String sha256(String value) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte item : digest) {
                String hex = Integer.toHexString(item & 0xFF);
                if (hex.length() == 1) {
                    builder.append('0');
                }
                builder.append(hex);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 algorithm not available", ex);
        }
    }
}
