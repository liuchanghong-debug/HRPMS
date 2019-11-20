package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSystemRole
 * @description TODO
 * @create 2019/11/19  21:11
 * @versiion 1.0
 * @Description:系统角色表
 */
public class TbSystemRole {
    private Integer id;//角色编号  主键
    private String roleName;//角色名称  非空
    private Integer sortNum;//排序
    private String status;//状态
    private Integer createBy;//创建者
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp createTime;//创建时间
    private Integer updateBy;//更新者
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp updateTime;//更新时间
    private String roleNote;//角色备注


    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public String getRoleNote() {
        return roleNote;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setRoleNote(String roleNote) {
        this.roleNote = roleNote;
    }
}
