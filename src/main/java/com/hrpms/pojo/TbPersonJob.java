package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbPersonJob
 * @description TODO
 * @create 2019/11/19  21:50
 * @versiion 1.0
 * @Description:劳务合作信息表
 */
public class TbPersonJob {
    private Integer id;//编号  主键
    private String idCard;//身份证号  非空
    private Integer companyId;//合作公司id  非空
    private String jobType;//工作类别
    private Double companyPrice;//公司单价
    private Double personPrice;//个人单价
    private String jobContent;//工作内容
    private Date startTime;//开始时间
    private Date endTime;//接收时间
    private String contractUrl;//合同url
    private String status;//状态  0 正常  1 停止
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

    public String getIdCard() {
        return idCard;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getJobType() {
        return jobType;
    }

    public Double getCompanyPrice() {
        return companyPrice;
    }

    public Double getPersonPrice() {
        return personPrice;
    }

    public String getJobContent() {
        return jobContent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getContractUrl() {
        return contractUrl;
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

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setCompanyPrice(Double companyPrice) {
        this.companyPrice = companyPrice;
    }

    public void setPersonPrice(Double personPrice) {
        this.personPrice = personPrice;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
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