package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSystemUser
 * @description TODO
 * @create 2019/11/19  21:07
 * @versiion 1.0
 * @Description: 系统用户表
 */
@Entity
public class TbSystemUser {
    private Integer id;//用户编号  主键
    private String username;//用户名称  非空
    private String password;//用户密码  非空
    private String email;//电子邮件
    private String phone;//手机
    private Integer sortnum;//排序
    private String status;//状态 0 正常  1 停用
    private Integer createBy;//创建者
    private Date createTime;//创建时间
    private Integer updateBy;//更新者
    private Date updateTime;//更新时间
    private String userNote;//备注

    //一个用户对应一个用户角色
    private TbUserRole tbUserRole;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getSortnum() {
        return sortnum;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getCreateTime() {
        return createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getUpdateTime() {
        return updateTime;
    }

    public String getUserNote() {
        return userNote;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }


    @OneToOne
    @JoinColumn(name="userId")
    public TbUserRole getTbUserRole() {
        return tbUserRole;
    }

    public void setTbUserRole(TbUserRole tbUserRole) {
        this.tbUserRole = tbUserRole;
    }
}
