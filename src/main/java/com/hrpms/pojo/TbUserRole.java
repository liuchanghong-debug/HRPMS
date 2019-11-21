package com.hrpms.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
