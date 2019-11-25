package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSalary
 * @description TODO
 * @create 2019/11/19  21:34
 * @versiion 1.0
 * @Description:工资表
 */
@Entity
public class TbSalary implements Serializable{
    private Integer id;//工资编号  主键
    private String name;//客户名称 非空
    private String idCard;//身份证号  非空
    private String payCard;//银行卡号
    private Date payDate;//发放月份
    private Double baseSalary;//基本工资
    private Double bonusPay;//奖金
    private Double overTimePay;//加班费
    private Double sheBaoPay;//社保扣费
    private Double gongJiJinPay;//公积金扣费
    private Double taxPay;//应交税款
    private Double totalPay;//应发金额
    private Double mustPay;//实发金额
    private Double proxyFee;//代理费用
    private String status;//状态  0 已发  1 未发

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

    public String getPayCard() {
        return payCard;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getPayDate() {
        return payDate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public Double getBonusPay() {
        return bonusPay;
    }

    public Double getOverTimePay() {
        return overTimePay;
    }

    public Double getSheBaoPay() {
        return sheBaoPay;
    }

    public Double getGongJiJinPay() {
        return gongJiJinPay;
    }

    public Double getTaxPay() {
        return taxPay;
    }

    public Double getTotalPay() {
        return totalPay;
    }

    public Double getMustPay() {
        return mustPay;
    }

    public Double getProxyFee() {
        return proxyFee;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public String getStatus() {
        return status;
    }

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

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setPayCard(String payCard) {
        this.payCard = payCard;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setBonusPay(Double bonusPay) {
        this.bonusPay = bonusPay;
    }

    public void setOverTimePay(Double overTimePay) {
        this.overTimePay = overTimePay;
    }

    public void setSheBaoPay(Double sheBaoPay) {
        this.sheBaoPay = sheBaoPay;
    }

    public void setGongJiJinPay(Double gongJiJinPay) {
        this.gongJiJinPay = gongJiJinPay;
    }

    public void setTaxPay(Double taxPay) {
        this.taxPay = taxPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    public void setMustPay(Double mustPay) {
        this.mustPay = mustPay;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
