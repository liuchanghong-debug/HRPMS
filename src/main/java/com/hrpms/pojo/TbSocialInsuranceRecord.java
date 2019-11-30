package com.hrpms.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSocialInsuranceRecord
 * @description TODO
 * @create 2019/11/19  21:31
 * @versiion 1.0
 * @Description:社保记录表
 */
@Entity
@DynamicUpdate
@DynamicInsert
public class TbSocialInsuranceRecord {
    private Integer id;//记录编号  主键
    private String name;//客户名称
    private String idCard;//身份证号  非空
    private String sbCard;//社保卡号  非空
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payMonth;//缴费日期
    private Double payMoney;//缴费金额
    private Double yangLao;//养老保险
    private Double yiLiao;//医疗保险
    private Double shiYe;//失业保险
    private Double gongShang;//工商保险
    private Double shengYu;//生育保险
    private String status;//状态  0 已交  1 未交
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
    @Column(length = 25)
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

    public Date getPayMonth() {
        return payMonth;
    }

    public Double getPayMoney() {
        return payMoney;
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

    public void setPayMonth(Date payMonth) {
        this.payMonth = payMonth;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
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
