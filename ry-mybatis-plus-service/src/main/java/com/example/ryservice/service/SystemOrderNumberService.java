package com.example.ryservice.service;

import com.example.ryservice.entity.SystemOrderNumberEntity;
import com.example.ryservice.mapper.SystemOrderNumberMapper;
import com.example.ryservice.util.DateNumberUtil;
import com.example.ryservice.util.OrderNumberUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 系统单号生成服务。
 */
@Service
public class SystemOrderNumberService {

    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final SystemOrderNumberMapper systemOrderNumberMapper;

    /**
     * 创建系统单号生成服务。
     *
     * @param systemOrderNumberMapper 单号规则 Mapper
     */
    public SystemOrderNumberService(SystemOrderNumberMapper systemOrderNumberMapper) {
        this.systemOrderNumberMapper = systemOrderNumberMapper;
    }

    /**
     * 生成国标工单单号。
     *
     * @return 工单单号
     */
    @Transactional(rollbackFor = Exception.class)
    public String getGbOrderNo() {
        return DateNumberUtil.getYearDays() + getOrderNumber("SEAR039");
    }

    /**
     * 按系列编码规则生成业务单号。
     *
     * @param number 系列编码
     * @return 业务单号
     */
    @Transactional(rollbackFor = Exception.class)
    public String getOrderNumber(String number) {
        SystemOrderNumberEntity orderNumber = systemOrderNumberMapper.selectByNumber(number);
        if (orderNumber == null) {
            throw new IllegalStateException("系列编码未找到: " + number);
        }

        String body;
        long type = orderNumber.getType() == null ? 1L : orderNumber.getType();
        if (type == 0L) {
            String format = orderNumber.getFormat();
            DateTimeFormatter formatter = (format == null || format.trim().isEmpty())
                    ? DEFAULT_FORMAT
                    : DateTimeFormatter.ofPattern(format.trim());
            body = LocalDate.now().format(formatter);
        } else if (type == 2L) {
            long digit = orderNumber.getDigit() == null || orderNumber.getDigit() <= 0 ? 3L : orderNumber.getDigit();
            String randomSetting = orderNumber.getRandomSetting();
            String setting = (randomSetting == null || randomSetting.trim().isEmpty())
                    ? "char_number char_lower"
                    : randomSetting.trim();
            body = OrderNumberUtil.getRandom(digit, Arrays.asList(setting.split(" ")));
        } else {
            long digit = orderNumber.getDigit() == null || orderNumber.getDigit() <= 0 ? 2L : orderNumber.getDigit();
            long nextNumber = (orderNumber.getCurrentNumber() == null ? 0L : orderNumber.getCurrentNumber()) + 1L;
            systemOrderNumberMapper.updateCurrentNumber(orderNumber.getId(), nextNumber);
            body = DateNumberUtil.padRight(String.valueOf(nextNumber), (int) digit, '0');
        }

        String prefix = orderNumber.getPrefix() == null ? "" : orderNumber.getPrefix().trim();
        String suffix = orderNumber.getSuffix() == null ? "" : orderNumber.getSuffix().trim();
        return prefix + body + suffix;
    }
}
