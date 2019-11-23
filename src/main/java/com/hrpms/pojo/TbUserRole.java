package com.hrpms.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbUserRole
 * @description TODO
 * @create 2019/11/19  21:10
 * @versiion 1.0
 * @Description:用户角色表
 */
@Entity
public class TbUserRole {
    private Integer userId;//用户id  主键
    private Integer roleId;//角色id  主键

    //一个用户id对应一个用户
    private TbSystemUser tbSystemUser;

    //多个用户id对应一个系统角色
    private TbSystemRole tbSystemRole;

    @Id
    @GeneratedValue
    public Integer getUserId() {
        return userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    @OneToOne
    @JoinColumn(name="userId",insertable = false,updatable = false)
    public TbSystemUser getTbSystemUser() {
        return tbSystemUser;
    }

    public void setTbSystemUser(TbSystemUser tbSystemUser) {
        this.tbSystemUser = tbSystemUser;
    }

    @ManyToOne
    @JoinColumn(name="roleId",insertable = false,updatable = false)
    public TbSystemRole getTbSystemRole() {
        return tbSystemRole;
    }

    public void setTbSystemRole(TbSystemRole tbSystemRole) {
        this.tbSystemRole = tbSystemRole;
    }
}
