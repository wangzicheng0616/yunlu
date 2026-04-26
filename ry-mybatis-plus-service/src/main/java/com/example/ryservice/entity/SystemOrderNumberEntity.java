package com.example.ryservice.entity;


public class SystemOrderNumberEntity {

    private Long id;

    private String prefix;

    private String suffix;

    private String number;

    private Long type;

    private Long digit;

    private String format;

    private Long startNumber;

    private Long currentNumber;

    private Long isCircle;

    private String randomSetting;

    /**
     * 获取id。
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id。
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取prefix。
     *
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * 设置prefix。
     *
     * @param prefix prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 获取suffix。
     *
     * @return suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 设置suffix。
     *
     * @param suffix suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * 获取number。
     *
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置number。
     *
     * @param number number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取type。
     *
     * @return type
     */
    public Long getType() {
        return type;
    }

    /**
     * 设置type。
     *
     * @param type type
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * 获取digit。
     *
     * @return digit
     */
    public Long getDigit() {
        return digit;
    }

    /**
     * 设置digit。
     *
     * @param digit digit
     */
    public void setDigit(Long digit) {
        this.digit = digit;
    }

    /**
     * 获取format。
     *
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     * 设置format。
     *
     * @param format format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 获取startNumber。
     *
     * @return startNumber
     */
    public Long getStartNumber() {
        return startNumber;
    }

    /**
     * 设置startNumber。
     *
     * @param startNumber startNumber
     */
    public void setStartNumber(Long startNumber) {
        this.startNumber = startNumber;
    }

    /**
     * 获取currentNumber。
     *
     * @return currentNumber
     */
    public Long getCurrentNumber() {
        return currentNumber;
    }

    /**
     * 设置currentNumber。
     *
     * @param currentNumber currentNumber
     */
    public void setCurrentNumber(Long currentNumber) {
        this.currentNumber = currentNumber;
    }

    /**
     * 获取isCircle。
     *
     * @return isCircle
     */
    public Long getIsCircle() {
        return isCircle;
    }

    /**
     * 设置isCircle。
     *
     * @param isCircle isCircle
     */
    public void setIsCircle(Long isCircle) {
        this.isCircle = isCircle;
    }

    /**
     * 获取randomSetting。
     *
     * @return randomSetting
     */
    public String getRandomSetting() {
        return randomSetting;
    }

    /**
     * 设置randomSetting。
     *
     * @param randomSetting randomSetting
     */
    public void setRandomSetting(String randomSetting) {
        this.randomSetting = randomSetting;
    }
}
