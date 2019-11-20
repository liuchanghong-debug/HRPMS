package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSocialInsuranceRecord
 * @description TODO
 * @create 2019/11/19  21:31
 * @versiion 1.0
 * @Description:社保记录表
 */
public class TbSocialInsuranceRecord {
    private Integer id;//记录编号  主键
    private String sdCard;//社保卡号  非空
    private String payMonth;//缴费日期
    private Double payMoney;//缴费金额
    private Double yangLao;//养老保险
    private Double yiLiao;//医疗保险
    private Double shiYe;//失业保险
    private Double gonsShang;//工商保险
    private Double shengYu;//生育保险
    private String status;//状态  0 已交  1 未交
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp createTime;//创建时间
    private Integer createBy;//创建者
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp updateTime;//更新时间
    private Integer updateBy;//更新者
    private String remark;//备注

    public Integer getId() {
        return id;
    }

    public String getSdCard() {
        return sdCard;
    }

    public String getPayMonth() {
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

    public Double getGonsShang() {
        return gonsShang;
    }

    public Double getShengYu() {
        return shengYu;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

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

    public void setSdCard(String sdCard) {
        this.sdCard = sdCard;
    }

    public void setPayMonth(String payMonth) {
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

    public void setGonsShang(Double gonsShang) {
        this.gonsShang = gonsShang;
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
