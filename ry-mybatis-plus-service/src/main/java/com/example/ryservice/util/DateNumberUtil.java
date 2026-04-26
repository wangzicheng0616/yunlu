package com.example.ryservice.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期和补位工具。
 */
public final class DateNumberUtil {

    private static final DateTimeFormatter YYMMDD = DateTimeFormatter.ofPattern("yyMMdd");

    /**
     * 禁止实例化工具类。
     */
    private DateNumberUtil() {
    }

    /**
     * 获取 `yyMMdd` 格式的日期字符串。
     *
     * @return 日期字符串
     */
    public static String getYearDays() {
        return LocalDate.now().format(YYMMDD);
    }

    /**
     * 对字符串执行左侧补位。
     *
     * @param src 原始字符串
     * @param len 目标长度
     * @param ch 补位字符
     * @return 补位后的字符串
     */
    public static String padRight(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] result = new char[len];
        System.arraycopy(src.toCharArray(), 0, result, diff, src.length());
        for (int i = 0; i < diff; i++) {
            result[i] = ch;
        }
        return new String(result);
    }
}
