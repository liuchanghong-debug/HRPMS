package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbAccumulationFund
 * @description TODO
 * @create 2019/11/19  21:41
 * @versiion 1.0
 * @Description:公积金表
 */
@Entity
public class TbAccumulationFund {
    private Integer id;//编号  主键
    private String name;//客户名称
    private String idCard;//身份证号  非空
    private String accountNo;//公积金账户 非空
    private String payDate;//缴费期间
    private Double payMoney;//缴费金额
    private Double proxyFee;//代理费用
    private String status;//状态  是否缴款

    private Timestamp createTime;//创建时间
    private Integer createBy;//创建者

    private Timestamp updateTime;//更新时间
    private Integer updateBy;//更新者
    private String remark;//备注

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getPayDate() {
        return payDate;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public Double getProxyFee() {
        return proxyFee;
    }

    public String getStatus() {
        return status;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public void setProxyFee(Double proxyFee) {
        this.proxyFee = proxyFee;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
