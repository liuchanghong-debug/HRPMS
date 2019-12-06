package com.hrpms.pojo;

import javax.persistence.Entity;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbRoleFunction
 * @description TODO
 * @create 2019/11/19  21:14
 * @versiion 1.0
 * @Description:角色权限表
 */
public class TbRoleFunction {
    private Integer roleId;//角色id
    private Integer funcId;//权限id

    public Integer getRoleId() {
        return roleId;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setRoleId(Integer roleId) {

        this.roleId = roleId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }
}
