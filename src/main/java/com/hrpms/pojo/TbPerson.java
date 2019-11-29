package com.hrpms.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbPerson
 * @description TODO
 * @create 2019/11/19  21:47
 * @versiion 1.0
 * @Description:人才信息表
 */
@Entity
@DynamicUpdate
@DynamicInsert
public class TbPerson {
    private Integer id;//编号  主键
    private String name;
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
    @Column(length = 256)
    public String getJobIntentsion() {
        return jobIntentsion;
    }
    @Column(length = 2)
    public String getJobType() {
        return jobType;
    }

    public Double getForPrice() {
        return forPrice;
    }
    @Column(length = 20)
    public String getForAddress() {
        return forAddress;
    }
    @Column(length = 256)
    public String getWorked() {
        return worked;
    }
    @Column(length = 256)
    public String getPersonInfo() {
        return personInfo;
    }
    @Column(length = 256)
    public String getResumeUrl() {
        return resumeUrl;
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
