package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbNeedJob
 * @description TODO
 * @create 2019/11/19  21:43
 * @versiion 1.0
 * @Description:劳务需求信息表
 */
public class TbNeedJob {
    private Integer id;//编号  主键
    private String jobName;//需求名称  非空
    private String jobContent;//需求内容
    private String jobType;//职位  工程师，会计等
    private String industry;//所属行业  0 软件互联网  1 建筑房地产
    //2 商业服务业  3 金融业  4 贸易批发零售  5 问题教育传媒  6 加工制造  7 农林牧副渔  8 其他
    private Integer needPerson;//需求人数
    private String payType;//结算方式  0 月结  1 日结 2 其他
    private Double price;//单价
    private Integer companyId;//发布公司  关联公司表
    private String address;//工作地点
    private Date startTime;//开始日期
    private Date endTime;//截至日期
    private String infoType;//信息类型  0 本公司招聘  1 合作公司招聘
    private String status;//状态  0 有效  1 无效
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

    public String getJobName() {
        return jobName;
    }

    public String getJobContent() {
        return jobContent;
    }

    public String getJobType() {
        return jobType;
    }

    public String getIndustry() {
        return industry;
    }

    public Integer getNeedPerson() {
        return needPerson;
    }

    public String getPayType() {
        return payType;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getAddress() {
        return address;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getInfoType() {
        return infoType;
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

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setNeedPerson(Integer needPerson) {
        this.needPerson = needPerson;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
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
