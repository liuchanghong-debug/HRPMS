package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbPerson
 * @description TODO
 * @create 2019/11/19  21:47
 * @versiion 1.0
 * @Description:人才信息表
 */
public class TbPerson {
    private Integer id;//编号  主键
    private String idCard;//身份证号
    private String jobIntentsion;//求职意向
    private String jobType;//工作类别  0 兼职  1 全职  2 外派
    private Double forPrice;//期望月薪
    private String forAddress;//期望工作地
    private String worked;//曾经工作经历
    private String personInfo;//自我介绍
    private String resumeUrl;//个人简历url
    private String status;//状态  0 离职  1 在职
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

    public String getJobIntentsion() {
        return jobIntentsion;
    }

    public String getJobType() {
        return jobType;
    }

    public Double getForPrice() {
        return forPrice;
    }

    public String getForAddress() {
        return forAddress;
    }

    public String getWorked() {
        return worked;
    }

    public String getPersonInfo() {
        return personInfo;
    }

    public String getResumeUrl() {
        return resumeUrl;
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

    public void setJobIntentsion(String jobIntentsion) {
        this.jobIntentsion = jobIntentsion;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setForPrice(Double forPrice) {
        this.forPrice = forPrice;
    }

    public void setForAddress(String forAddress) {
        this.forAddress = forAddress;
    }

    public void setWorked(String worked) {
        this.worked = worked;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
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
