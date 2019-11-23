package com.hrpms.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbCustomer
 * @description TODO
 * @create 2019/11/19  21:23
 * @versiion 1.0
 * @Description:客户表
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class TbCustomer implements Serializable{
    private Integer id;//客户编号  主键
    private String name;//客户名称  非空
    private String idCard;//身份证号
    private String sex;//性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;//出生年月日
    private String phone;//电话
    private String email;//电子邮件
    private String address;//客户联系地址
    private String zipCode;//邮编
    private String school;//毕业学校
    private String specialty;//专业
    private String graduation;//毕业时间
    private Integer companyId;//所属公司
    private String customerType;//客户类型  0 本公司员工  1 代理公司   2 个人客户   3 外包客户
    private String isSalary;//代发工资  0 是  1 否
    private String isSheBao;//代缴社保  0 是  1 否
    private String isGongJiJin;//代缴公积金  0 是  1 否
    private String status;//状态  0 正常  1 封存
    private String delFlag;//删除标志  0 正常  1 删除
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
    @Column(length = 2)
    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }
    @Column(length = 13)
    public String getPhone() {
        return phone;
    }
    @Column(length = 50)
    public String getEmail() {
        return email;
    }
    @Column(length = 100)
    public String getAddress() {
        return address;
    }
    @Column(length = 20)
    public String getZipCode() {
        return zipCode;
    }
    @Column(length = 20)
    public String getSchool() {
        return school;
    }
    @Column(length = 20)
    public String getSpecialty() {
        return specialty;
    }
    @Column(length = 20)
    public String getGraduation() {
        return graduation;
    }
    @Column(length = 11)
    public Integer getCompanyId() {
        return companyId;
    }
    @Column(length = 2)
    public String getCustomerType() {
        return customerType;
    }
    @Column(length = 2)
    public String getIsSalary() {
        return isSalary;
    }
    @Column(length = 2)
    public String getIsSheBao() {
        return isSheBao;
    }
    @Column(length = 2)
    public String getIsGongJiJin() {
        return isGongJiJin;
    }
    @Column(length = 2)
    public String getStatus() {
        return status;
    }
    @Column(length = 2)
    public String getDelFlag() {
        return delFlag;
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

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setIsSalary(String isSalary) {
        this.isSalary = isSalary;
    }

    public void setIsSheBao(String isSheBao) {
        this.isSheBao = isSheBao;
    }

    public void setIsGongJiJin(String isGongJiJin) {
        this.isGongJiJin = isGongJiJin;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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
