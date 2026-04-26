package com.example.ryservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

@TableName("gb_order")
public class GbOrderEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("order_no")
    private String orderNo;

    @TableField("user_name")
    private String userName;

    @TableField("user_id")
    private Long userId;

    @TableField("user_telephone")
    private String userTelephone;

    @TableField("work_id")
    private Long workId;

    @TableField("worker_name")
    private String workerName;

    @TableField("worker_wx_no")
    private String workerWxNo;

    @TableField("worker_telephone")
    private String workerTelephone;

    @TableField("staff_name")
    private String staffName;

    @TableField("staff_telephone")
    private String staffTelephone;

    @TableField("provincename")
    private String provinceName;

    @TableField("address")
    private String address;

    @TableField("status")
    private Long status;

    @TableField("work_order_type")
    private String workOrderType;

    @TableField("revenue")
    private BigDecimal revenue;

    @TableField("original_amount")
    private BigDecimal originalAmount;

    @TableField("promoter_wx_id")
    private String promoterWxId;

    @TableField("promoter")
    private String promoter;

    @TableField("customer")
    private String customer;

    @TableField("order_date")
    private Date orderDate;

    @TableField("service_time")
    private Date serviceTime;

    @TableField("end_date")
    private Date endDate;

    @TableField("close_date")
    private Date closeDate;

    @TableField("note")
    private String note;

    @TableField("end_pic1")
    private String endPic1;

    @TableField("end_pic2")
    private String endPic2;

    @TableField("end_pic3")
    private String endPic3;

    @TableField("end_pic")
    private String endPic;

    @TableField("customer_intention")
    private String customerIntention;

    @TableField("user_sign_url")
    private String userSignUrl;

    @TableField("user_evaluate")
    private Long userEvaluate;

    @TableField("user_evaluate_comment")
    private String userEvaluateComment;

    @TableField("remarks")
    private String remarks;

    @TableField("evaluate")
    private Long evaluate;

    @TableField("evaluate_comment")
    private String evaluateComment;

    @TableField("del_flag")
    private Long delFlag;

    @TableField("pay_method")
    private String payMethod;

    @TableField("Remark")
    private String remark;

    @TableField("Key1")
    private String key1;

    @TableField("Key2")
    private String key2;

    @TableField("Key3")
    private String key3;

    @TableField("Key4")
    private String key4;

    @TableField("Key5")
    private String key5;

    @TableField("CreateTime")
    private Date createTime;

    @TableField("CreaterId")
    private Long createrId;

    @TableField("UpdateTime")
    private Date updateTime;

    @TableField("UpdaterId")
    private Long updaterId;

    @TableField("provincecode")
    private String provinceCode;

    @TableField("citycode")
    private String cityCode;

    @TableField("areacode")
    private String areaCode;

    @TableField("cityname")
    private String cityName;

    @TableField("areaname")
    private String areaName;

    @TableField("fulladdress")
    private String fullAddress;

    @TableField("req_date")
    private Date reqDate;

    @TableField("workRemark")
    private String workRemark;

    @TableField("pay_wxpay")
    private String payWxPay;

    @TableField("pay_alipay")
    private String payAliPay;

    @TableField("pay_account")
    private String payAccount;

    @TableField("pay_bank")
    private String payBank;

    @TableField("pay_bank_addr")
    private String payBankAddr;

    @TableField("isPay")
    private Long isPay;

    @TableField("worker_telephone1")
    private String workerTelephone1;

    @TableField("work_order_type_code")
    private String workOrderTypeCode;

    @TableField("subsidy_amount")
    private BigDecimal subsidyAmount;

    @TableField("worker_memo")
    private String workerMemo;

    @TableField("order_type")
    private Long orderType;

    @TableField("appid")
    private String appid;

    @TableField("apiUserId")
    private String apiUserId;

    @TableField("custompic")
    private String customPic;

    @TableField("passAuditTime")
    private Date passAuditTime;

    @TableField("dealOrderTime")
    private Date dealOrderTime;

    @TableField("responseTime")
    private BigDecimal responseTime;

    @TableField("orderLevelName")
    private String orderLevelName;

    @TableField("orderLevelCode")
    private String orderLevelCode;

    @TableField("orderLevelValue")
    private BigDecimal orderLevelValue;

    @TableField("operationUserId")
    private Long operationUserId;

    @TableField("operationUserName")
    private String operationUserName;

    @TableField("operationUserNo")
    private String operationUserNo;

    @TableField("operationMemo")
    private String operationMemo;

    @TableField("supplyName")
    private String supplyName;

    @TableField("masterAmount")
    private BigDecimal masterAmount;

    @TableField("consumablesAmount")
    private BigDecimal consumablesAmount;

    @TableField("emptyAmount")
    private BigDecimal emptyAmount;

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
     * 获取orderNo。
     *
     * @return orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置orderNo。
     *
     * @param orderNo orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取userName。
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置userName。
     *
     * @param userName userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取userId。
     *
     * @return userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置userId。
     *
     * @param userId userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取userTelephone。
     *
     * @return userTelephone
     */
    public String getUserTelephone() {
        return userTelephone;
    }

    /**
     * 设置userTelephone。
     *
     * @param userTelephone userTelephone
     */
    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    /**
     * 获取workId。
     *
     * @return workId
     */
    public Long getWorkId() {
        return workId;
    }

    /**
     * 设置workId。
     *
     * @param workId workId
     */
    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    /**
     * 获取workerName。
     *
     * @return workerName
     */
    public String getWorkerName() {
        return workerName;
    }

    /**
     * 设置workerName。
     *
     * @param workerName workerName
     */
    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    /**
     * 获取workerWxNo。
     *
     * @return workerWxNo
     */
    public String getWorkerWxNo() {
        return workerWxNo;
    }

    /**
     * 设置workerWxNo。
     *
     * @param workerWxNo workerWxNo
     */
    public void setWorkerWxNo(String workerWxNo) {
        this.workerWxNo = workerWxNo;
    }

    /**
     * 获取workerTelephone。
     *
     * @return workerTelephone
     */
    public String getWorkerTelephone() {
        return workerTelephone;
    }

    /**
     * 设置workerTelephone。
     *
     * @param workerTelephone workerTelephone
     */
    public void setWorkerTelephone(String workerTelephone) {
        this.workerTelephone = workerTelephone;
    }

    /**
     * 获取staffName。
     *
     * @return staffName
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置staffName。
     *
     * @param staffName staffName
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * 获取staffTelephone。
     *
     * @return staffTelephone
     */
    public String getStaffTelephone() {
        return staffTelephone;
    }

    /**
     * 设置staffTelephone。
     *
     * @param staffTelephone staffTelephone
     */
    public void setStaffTelephone(String staffTelephone) {
        this.staffTelephone = staffTelephone;
    }

    /**
     * 获取provinceName。
     *
     * @return provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置provinceName。
     *
     * @param provinceName provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 获取address。
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置address。
     *
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取status。
     *
     * @return status
     */
    public Long getStatus() {
        return status;
    }

    /**
     * 设置status。
     *
     * @param status status
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * 获取workOrderType。
     *
     * @return workOrderType
     */
    public String getWorkOrderType() {
        return workOrderType;
    }

    /**
     * 设置workOrderType。
     *
     * @param workOrderType workOrderType
     */
    public void setWorkOrderType(String workOrderType) {
        this.workOrderType = workOrderType;
    }

    /**
     * 获取revenue。
     *
     * @return revenue
     */
    public BigDecimal getRevenue() {
        return revenue;
    }

    /**
     * 设置revenue。
     *
     * @param revenue revenue
     */
    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    /**
     * 获取originalAmount。
     *
     * @return originalAmount
     */
    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    /**
     * 设置originalAmount。
     *
     * @param originalAmount originalAmount
     */
    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    /**
     * 获取promoterWxId。
     *
     * @return promoterWxId
     */
    public String getPromoterWxId() {
        return promoterWxId;
    }

    /**
     * 设置promoterWxId。
     *
     * @param promoterWxId promoterWxId
     */
    public void setPromoterWxId(String promoterWxId) {
        this.promoterWxId = promoterWxId;
    }

    /**
     * 获取promoter。
     *
     * @return promoter
     */
    public String getPromoter() {
        return promoter;
    }

    /**
     * 设置promoter。
     *
     * @param promoter promoter
     */
    public void setPromoter(String promoter) {
        this.promoter = promoter;
    }

    /**
     * 获取customer。
     *
     * @return customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * 设置customer。
     *
     * @param customer customer
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * 获取orderDate。
     *
     * @return orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 设置orderDate。
     *
     * @param orderDate orderDate
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 获取serviceTime。
     *
     * @return serviceTime
     */
    public Date getServiceTime() {
        return serviceTime;
    }

    /**
     * 设置serviceTime。
     *
     * @param serviceTime serviceTime
     */
    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * 获取endDate。
     *
     * @return endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate。
     *
     * @param endDate endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取closeDate。
     *
     * @return closeDate
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * 设置closeDate。
     *
     * @param closeDate closeDate
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * 获取note。
     *
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置note。
     *
     * @param note note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取endPic1。
     *
     * @return endPic1
     */
    public String getEndPic1() {
        return endPic1;
    }

    /**
     * 设置endPic1。
     *
     * @param endPic1 endPic1
     */
    public void setEndPic1(String endPic1) {
        this.endPic1 = endPic1;
    }

    /**
     * 获取endPic2。
     *
     * @return endPic2
     */
    public String getEndPic2() {
        return endPic2;
    }

    /**
     * 设置endPic2。
     *
     * @param endPic2 endPic2
     */
    public void setEndPic2(String endPic2) {
        this.endPic2 = endPic2;
    }

    /**
     * 获取endPic3。
     *
     * @return endPic3
     */
    public String getEndPic3() {
        return endPic3;
    }

    /**
     * 设置endPic3。
     *
     * @param endPic3 endPic3
     */
    public void setEndPic3(String endPic3) {
        this.endPic3 = endPic3;
    }

    /**
     * 获取endPic。
     *
     * @return endPic
     */
    public String getEndPic() {
        return endPic;
    }

    /**
     * 设置endPic。
     *
     * @param endPic endPic
     */
    public void setEndPic(String endPic) {
        this.endPic = endPic;
    }

    /**
     * 获取customerIntention。
     *
     * @return customerIntention
     */
    public String getCustomerIntention() {
        return customerIntention;
    }

    /**
     * 设置customerIntention。
     *
     * @param customerIntention customerIntention
     */
    public void setCustomerIntention(String customerIntention) {
        this.customerIntention = customerIntention;
    }

    /**
     * 获取userSignUrl。
     *
     * @return userSignUrl
     */
    public String getUserSignUrl() {
        return userSignUrl;
    }

    /**
     * 设置userSignUrl。
     *
     * @param userSignUrl userSignUrl
     */
    public void setUserSignUrl(String userSignUrl) {
        this.userSignUrl = userSignUrl;
    }

    /**
     * 获取userEvaluate。
     *
     * @return userEvaluate
     */
    public Long getUserEvaluate() {
        return userEvaluate;
    }

    /**
     * 设置userEvaluate。
     *
     * @param userEvaluate userEvaluate
     */
    public void setUserEvaluate(Long userEvaluate) {
        this.userEvaluate = userEvaluate;
    }

    /**
     * 获取userEvaluateComment。
     *
     * @return userEvaluateComment
     */
    public String getUserEvaluateComment() {
        return userEvaluateComment;
    }

    /**
     * 设置userEvaluateComment。
     *
     * @param userEvaluateComment userEvaluateComment
     */
    public void setUserEvaluateComment(String userEvaluateComment) {
        this.userEvaluateComment = userEvaluateComment;
    }

    /**
     * 获取remarks。
     *
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置remarks。
     *
     * @param remarks remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取evaluate。
     *
     * @return evaluate
     */
    public Long getEvaluate() {
        return evaluate;
    }

    /**
     * 设置evaluate。
     *
     * @param evaluate evaluate
     */
    public void setEvaluate(Long evaluate) {
        this.evaluate = evaluate;
    }

    /**
     * 获取evaluateComment。
     *
     * @return evaluateComment
     */
    public String getEvaluateComment() {
        return evaluateComment;
    }

    /**
     * 设置evaluateComment。
     *
     * @param evaluateComment evaluateComment
     */
    public void setEvaluateComment(String evaluateComment) {
        this.evaluateComment = evaluateComment;
    }

    /**
     * 获取delFlag。
     *
     * @return delFlag
     */
    public Long getDelFlag() {
        return delFlag;
    }

    /**
     * 设置delFlag。
     *
     * @param delFlag delFlag
     */
    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取payMethod。
     *
     * @return payMethod
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * 设置payMethod。
     *
     * @param payMethod payMethod
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * 获取remark。
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark。
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取key1。
     *
     * @return key1
     */
    public String getKey1() {
        return key1;
    }

    /**
     * 设置key1。
     *
     * @param key1 key1
     */
    public void setKey1(String key1) {
        this.key1 = key1;
    }

    /**
     * 获取key2。
     *
     * @return key2
     */
    public String getKey2() {
        return key2;
    }

    /**
     * 设置key2。
     *
     * @param key2 key2
     */
    public void setKey2(String key2) {
        this.key2 = key2;
    }

    /**
     * 获取key3。
     *
     * @return key3
     */
    public String getKey3() {
        return key3;
    }

    /**
     * 设置key3。
     *
     * @param key3 key3
     */
    public void setKey3(String key3) {
        this.key3 = key3;
    }

    /**
     * 获取key4。
     *
     * @return key4
     */
    public String getKey4() {
        return key4;
    }

    /**
     * 设置key4。
     *
     * @param key4 key4
     */
    public void setKey4(String key4) {
        this.key4 = key4;
    }

    /**
     * 获取key5。
     *
     * @return key5
     */
    public String getKey5() {
        return key5;
    }

    /**
     * 设置key5。
     *
     * @param key5 key5
     */
    public void setKey5(String key5) {
        this.key5 = key5;
    }

    /**
     * 获取createTime。
     *
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime。
     *
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取createrId。
     *
     * @return createrId
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 设置createrId。
     *
     * @param createrId createrId
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 获取updateTime。
     *
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置updateTime。
     *
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取updaterId。
     *
     * @return updaterId
     */
    public Long getUpdaterId() {
        return updaterId;
    }

    /**
     * 设置updaterId。
     *
     * @param updaterId updaterId
     */
    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    /**
     * 获取provinceCode。
     *
     * @return provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 设置provinceCode。
     *
     * @param provinceCode provinceCode
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * 获取cityCode。
     *
     * @return cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置cityCode。
     *
     * @param cityCode cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取areaCode。
     *
     * @return areaCode
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置areaCode。
     *
     * @param areaCode areaCode
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取cityName。
     *
     * @return cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置cityName。
     *
     * @param cityName cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取areaName。
     *
     * @return areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置areaName。
     *
     * @param areaName areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 获取fullAddress。
     *
     * @return fullAddress
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * 设置fullAddress。
     *
     * @param fullAddress fullAddress
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    /**
     * 获取reqDate。
     *
     * @return reqDate
     */
    public Date getReqDate() {
        return reqDate;
    }

    /**
     * 设置reqDate。
     *
     * @param reqDate reqDate
     */
    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    /**
     * 获取workRemark。
     *
     * @return workRemark
     */
    public String getWorkRemark() {
        return workRemark;
    }

    /**
     * 设置workRemark。
     *
     * @param workRemark workRemark
     */
    public void setWorkRemark(String workRemark) {
        this.workRemark = workRemark;
    }

    /**
     * 获取payWxPay。
     *
     * @return payWxPay
     */
    public String getPayWxPay() {
        return payWxPay;
    }

    /**
     * 设置payWxPay。
     *
     * @param payWxPay payWxPay
     */
    public void setPayWxPay(String payWxPay) {
        this.payWxPay = payWxPay;
    }

    /**
     * 获取payAliPay。
     *
     * @return payAliPay
     */
    public String getPayAliPay() {
        return payAliPay;
    }

    /**
     * 设置payAliPay。
     *
     * @param payAliPay payAliPay
     */
    public void setPayAliPay(String payAliPay) {
        this.payAliPay = payAliPay;
    }

    /**
     * 获取payAccount。
     *
     * @return payAccount
     */
    public String getPayAccount() {
        return payAccount;
    }

    /**
     * 设置payAccount。
     *
     * @param payAccount payAccount
     */
    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    /**
     * 获取payBank。
     *
     * @return payBank
     */
    public String getPayBank() {
        return payBank;
    }

    /**
     * 设置payBank。
     *
     * @param payBank payBank
     */
    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    /**
     * 获取payBankAddr。
     *
     * @return payBankAddr
     */
    public String getPayBankAddr() {
        return payBankAddr;
    }

    /**
     * 设置payBankAddr。
     *
     * @param payBankAddr payBankAddr
     */
    public void setPayBankAddr(String payBankAddr) {
        this.payBankAddr = payBankAddr;
    }

    /**
     * 获取isPay。
     *
     * @return isPay
     */
    public Long getIsPay() {
        return isPay;
    }

    /**
     * 设置isPay。
     *
     * @param isPay isPay
     */
    public void setIsPay(Long isPay) {
        this.isPay = isPay;
    }

    /**
     * 获取workerTelephone1。
     *
     * @return workerTelephone1
     */
    public String getWorkerTelephone1() {
        return workerTelephone1;
    }

    /**
     * 设置workerTelephone1。
     *
     * @param workerTelephone1 workerTelephone1
     */
    public void setWorkerTelephone1(String workerTelephone1) {
        this.workerTelephone1 = workerTelephone1;
    }

    /**
     * 获取workOrderTypeCode。
     *
     * @return workOrderTypeCode
     */
    public String getWorkOrderTypeCode() {
        return workOrderTypeCode;
    }

    /**
     * 设置workOrderTypeCode。
     *
     * @param workOrderTypeCode workOrderTypeCode
     */
    public void setWorkOrderTypeCode(String workOrderTypeCode) {
        this.workOrderTypeCode = workOrderTypeCode;
    }

    /**
     * 获取subsidyAmount。
     *
     * @return subsidyAmount
     */
    public BigDecimal getSubsidyAmount() {
        return subsidyAmount;
    }

    /**
     * 设置subsidyAmount。
     *
     * @param subsidyAmount subsidyAmount
     */
    public void setSubsidyAmount(BigDecimal subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    /**
     * 获取workerMemo。
     *
     * @return workerMemo
     */
    public String getWorkerMemo() {
        return workerMemo;
    }

    /**
     * 设置workerMemo。
     *
     * @param workerMemo workerMemo
     */
    public void setWorkerMemo(String workerMemo) {
        this.workerMemo = workerMemo;
    }

    /**
     * 获取orderType。
     *
     * @return orderType
     */
    public Long getOrderType() {
        return orderType;
    }

    /**
     * 设置orderType。
     *
     * @param orderType orderType
     */
    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取appid。
     *
     * @return appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设置appid。
     *
     * @param appid appid
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 获取apiUserId。
     *
     * @return apiUserId
     */
    public String getApiUserId() {
        return apiUserId;
    }

    /**
     * 设置apiUserId。
     *
     * @param apiUserId apiUserId
     */
    public void setApiUserId(String apiUserId) {
        this.apiUserId = apiUserId;
    }

    /**
     * 获取customPic。
     *
     * @return customPic
     */
    public String getCustomPic() {
        return customPic;
    }

    /**
     * 设置customPic。
     *
     * @param customPic customPic
     */
    public void setCustomPic(String customPic) {
        this.customPic = customPic;
    }

    /**
     * 获取passAuditTime。
     *
     * @return passAuditTime
     */
    public Date getPassAuditTime() {
        return passAuditTime;
    }

    /**
     * 设置passAuditTime。
     *
     * @param passAuditTime passAuditTime
     */
    public void setPassAuditTime(Date passAuditTime) {
        this.passAuditTime = passAuditTime;
    }

    /**
     * 获取dealOrderTime。
     *
     * @return dealOrderTime
     */
    public Date getDealOrderTime() {
        return dealOrderTime;
    }

    /**
     * 设置dealOrderTime。
     *
     * @param dealOrderTime dealOrderTime
     */
    public void setDealOrderTime(Date dealOrderTime) {
        this.dealOrderTime = dealOrderTime;
    }

    /**
     * 获取responseTime。
     *
     * @return responseTime
     */
    public BigDecimal getResponseTime() {
        return responseTime;
    }

    /**
     * 设置responseTime。
     *
     * @param responseTime responseTime
     */
    public void setResponseTime(BigDecimal responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * 获取orderLevelName。
     *
     * @return orderLevelName
     */
    public String getOrderLevelName() {
        return orderLevelName;
    }

    /**
     * 设置orderLevelName。
     *
     * @param orderLevelName orderLevelName
     */
    public void setOrderLevelName(String orderLevelName) {
        this.orderLevelName = orderLevelName;
    }

    /**
     * 获取orderLevelCode。
     *
     * @return orderLevelCode
     */
    public String getOrderLevelCode() {
        return orderLevelCode;
    }

    /**
     * 设置orderLevelCode。
     *
     * @param orderLevelCode orderLevelCode
     */
    public void setOrderLevelCode(String orderLevelCode) {
        this.orderLevelCode = orderLevelCode;
    }

    /**
     * 获取orderLevelValue。
     *
     * @return orderLevelValue
     */
    public BigDecimal getOrderLevelValue() {
        return orderLevelValue;
    }

    /**
     * 设置orderLevelValue。
     *
     * @param orderLevelValue orderLevelValue
     */
    public void setOrderLevelValue(BigDecimal orderLevelValue) {
        this.orderLevelValue = orderLevelValue;
    }

    /**
     * 获取operationUserId。
     *
     * @return operationUserId
     */
    public Long getOperationUserId() {
        return operationUserId;
    }

    /**
     * 设置operationUserId。
     *
     * @param operationUserId operationUserId
     */
    public void setOperationUserId(Long operationUserId) {
        this.operationUserId = operationUserId;
    }

    /**
     * 获取operationUserName。
     *
     * @return operationUserName
     */
    public String getOperationUserName() {
        return operationUserName;
    }

    /**
     * 设置operationUserName。
     *
     * @param operationUserName operationUserName
     */
    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    /**
     * 获取operationUserNo。
     *
     * @return operationUserNo
     */
    public String getOperationUserNo() {
        return operationUserNo;
    }

    /**
     * 设置operationUserNo。
     *
     * @param operationUserNo operationUserNo
     */
    public void setOperationUserNo(String operationUserNo) {
        this.operationUserNo = operationUserNo;
    }

    /**
     * 获取operationMemo。
     *
     * @return operationMemo
     */
    public String getOperationMemo() {
        return operationMemo;
    }

    /**
     * 设置operationMemo。
     *
     * @param operationMemo operationMemo
     */
    public void setOperationMemo(String operationMemo) {
        this.operationMemo = operationMemo;
    }

    /**
     * 获取supplyName。
     *
     * @return supplyName
     */
    public String getSupplyName() {
        return supplyName;
    }

    /**
     * 设置supplyName。
     *
     * @param supplyName supplyName
     */
    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    /**
     * 获取masterAmount。
     *
     * @return masterAmount
     */
    public BigDecimal getMasterAmount() {
        return masterAmount;
    }

    /**
     * 设置masterAmount。
     *
     * @param masterAmount masterAmount
     */
    public void setMasterAmount(BigDecimal masterAmount) {
        this.masterAmount = masterAmount;
    }

    /**
     * 获取consumablesAmount。
     *
     * @return consumablesAmount
     */
    public BigDecimal getConsumablesAmount() {
        return consumablesAmount;
    }

    /**
     * 设置consumablesAmount。
     *
     * @param consumablesAmount consumablesAmount
     */
    public void setConsumablesAmount(BigDecimal consumablesAmount) {
        this.consumablesAmount = consumablesAmount;
    }

    /**
     * 获取emptyAmount。
     *
     * @return emptyAmount
     */
    public BigDecimal getEmptyAmount() {
        return emptyAmount;
    }

    /**
     * 设置emptyAmount。
     *
     * @param emptyAmount emptyAmount
     */
    public void setEmptyAmount(BigDecimal emptyAmount) {
        this.emptyAmount = emptyAmount;
    }
}
