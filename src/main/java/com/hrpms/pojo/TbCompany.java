package com.hrpms.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbCompany
 * @description TODO
 * @create 2019/11/19  21:20
 * @versiion 1.0
 * @Description:公司表
 */
@Entity
@DynamicUpdate
@DynamicInsert
public class TbCompany {
    private Integer id;//公司编号  主键
    private String name;//公司名称  非空
    private String address;//公司地址
    private String zipCode;//邮编
    private String telPhone;//公司电话
    private String companyNo;//统一社会信用代码
    private String owner;//法人
    private String idCard;//身份证号
    private String phone;//法人电话
    private String sex;//法人性别
    private String email;//电子邮件
    private String ownerShip;//公司性质
    private String companyType;//公司类别  0 本公司  1 业务代理  2 外包合作
    private String status;//状态  0 正常  1 封存
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
    @Column(length = 100)
    public String getName() {
        return name;
    }
    @Column(length = 256)
    public String getAddress() {
        return address;
    }
    @Column(length = 20)
    public String getZipCode() {
        return zipCode;
    }
    @Column(length = 13)
    public String getTelPhone() {
        return telPhone;
    }
    @Column(length = 20)
    public String getCompanyNo() {
        return companyNo;
    }
    @Column(length = 30)
    public String getOwner() {
        return owner;
    }
    @Column(length = 20)
    public String getIdCard() {
        return idCard;
    }
    @Column(length = 13)
    public String getPhone() {
        return phone;
    }
    @Column(length = 2)
    public String getSex() {
        return sex;
    }
    @Column(length = 50)
    public String getEmail() {
        return email;
    }
    @Column(length = 150)
    public String getOwnerShip() {
        return ownerShip;
    }
    @Column(length = 2)
    public String getCompanyType() {
        return companyType;
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
    @Column(length = 512)
    public String getRemark() {
        return remark;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOwnerShip(String ownerShip) {
        this.ownerShip = ownerShip;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
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
