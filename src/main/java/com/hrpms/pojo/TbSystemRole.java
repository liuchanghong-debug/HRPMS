package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSystemRole
 * @description TODO
 * @create 2019/11/19  21:11
 * @versiion 1.0
 * @Description:系统角色表
 */
@Entity
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

    //多个角色对应多个用户
    private Set<TbSystemUser> tbSystemUsers  = new LinkedHashSet<>();

    //角色与权限有多对多关系
    private Set<TbSystemFunction> tbSystemFunctions = new LinkedHashSet<>();

    @Id
    @GeneratedValue
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

    @ManyToMany(mappedBy = "tbSystemRoles")
    public Set<TbSystemUser> getTbSystemUsers() {
        return tbSystemUsers;
    }

    public void setTbSystemUsers(Set<TbSystemUser> tbSystemUsers) {
        this.tbSystemUsers = tbSystemUsers;
    }

    @ManyToMany
    @JoinTable(
            name = "tbRoleFunction",
            joinColumns = @JoinColumn(name ="roleId"),
            inverseJoinColumns = @JoinColumn(name="funcId")
    )
    @OrderBy("sortNum")
    public Set<TbSystemFunction> getTbSystemFunctions() {
        return tbSystemFunctions;
    }

    public void setTbSystemFunctions(Set<TbSystemFunction> tbSystemFunctions) {
        this.tbSystemFunctions = tbSystemFunctions;
    }
}
