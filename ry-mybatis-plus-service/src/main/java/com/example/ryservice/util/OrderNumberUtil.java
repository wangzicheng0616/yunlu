package com.example.ryservice.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 随机序列号工具。
 */
public final class OrderNumberUtil {

    private static final Map<String, String> SOURCE_MAP = new HashMap<>();

    static {
        SOURCE_MAP.put("char_number", "0123456789");
        SOURCE_MAP.put("char_lower", "abcdefghijklmnopqrstuvwxyz");
        SOURCE_MAP.put("char_upper", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        SOURCE_MAP.put("char_punctuation", "~!@#$%^&*()_+}{|:?><");
    }

    /**
     * 禁止实例化工具类。
     */
    private OrderNumberUtil() {
    }

    /**
     * 根据字符源配置生成随机字符串。
     *
     * @param len 目标长度
     * @param sourceList 字符源配置列表
     * @return 随机字符串
     */
    public static String getRandom(long len, List<String> sourceList) {
        StringBuilder source = new StringBuilder();
        for (String item : sourceList) {
            String chars = SOURCE_MAP.get(item);
            if (chars != null) {
                source.append(chars);
            }
        }
        if (source.length() == 0) {
            throw new IllegalArgumentException("生成序列基本字符数据异常");
        }

        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (long i = 0; i < len; i++) {
            result.append(source.charAt(random.nextInt(source.length())));
        }
        return result.toString();
    }
}
