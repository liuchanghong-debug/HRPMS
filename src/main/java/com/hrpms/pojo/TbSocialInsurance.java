package com.hrpms.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSocialInsurance
 * @description TODO
 * @create 2019/11/19  21:27
 * @versiion 1.0
 * @Description:社保基础表
 */
@Entity
@DynamicUpdate
@DynamicInsert
public class TbSocialInsurance {
    private Integer id;//社保编号  主键
    private String name;//姓名
    private String idCard;//身份证号  非空
    private String sbCard;//社保卡号
    private Double basePay;//缴费基数
    private Double mustPay;//应交金额
    private String personRatio;//个人缴比率
    private String companyRatio;//单位缴比率
    private Double yangLao;//养老保险
    private Double yiLiao;//医疗保险
    private Double shiYe;//失业保险
    private Double gongShang;//工商保险
    private Double shengYu;//生育保险
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDate;//预交款日期
    private Double proxyFee;//代理费用
    private String status;//状态  0 正常  1 删除
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp createTime;//创建时间
    private Integer createBy;//创建者
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp updateTime;//更新时间
    private Integer updateBy;//更新者
    private String remark;//备注

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    public Integer getId() {
        return id;
    }

    @Column(length = 50)
    public String getName() {
        return name;
    }

    @Column(length = 20)
    public String getIdCard() {
        return idCard;
    }
    @Column(length = 20)
    public String getSbCard() {
        return sbCard;
    }

    public Double getBasePay() {
        return basePay;
    }

    public Double getMustPay() {
        return mustPay;
    }
    @Column(length = 5)
    public String getPersonRatio() {
        return personRatio;
    }
    @Column(length = 5)
    public String getCompanyRatio() {
        return companyRatio;
    }

    public Double getYangLao() {
        return yangLao;
    }

    public Double getYiLiao() {
        return yiLiao;
    }

    public Double getShiYe() {
        return shiYe;
    }

    public Double getGongShang() {
        return gongShang;
    }

    public Double getShengYu() {
        return shengYu;
    }

    public Date getPayDate() {
        return payDate;
    }

    public Double getProxyFee() {
        return proxyFee;
    }
    @Column(length = 2)
    public String getStatus() {
        return status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
    @Column(length = 11)
    public Integer getCreateBy() {
        return createBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }
    @Column(length = 11)
    public Integer getUpdateBy() {
        return updateBy;
    }
    @Column(length = 256)
    public String getRemark() {
        return remark;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setSbCard(String sbCard) {
        this.sbCard = sbCard;
    }

    public void setBasePay(Double basePay) {
        this.basePay = basePay;
    }

    public void setMustPay(Double mustPay) {
        this.mustPay = mustPay;
    }

    public void setPersonRatio(String personRatio) {
        this.personRatio = personRatio;
    }

    public void setCompanyRatio(String companyRatio) {
        this.companyRatio = companyRatio;
    }

    public void setYangLao(Double yangLao) {
        this.yangLao = yangLao;
    }

    public void setYiLiao(Double yiLiao) {
        this.yiLiao = yiLiao;
    }

    public void setShiYe(Double shiYe) {
        this.shiYe = shiYe;
    }

    public void setGongShang(Double gongShang) {
        this.gongShang = gongShang;
    }

    public void setShengYu(Double shengYu) {
        this.shengYu = shengYu;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
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
