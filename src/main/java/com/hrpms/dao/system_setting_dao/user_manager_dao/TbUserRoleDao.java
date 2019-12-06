package com.hrpms.dao.system_setting_dao.user_manager_dao;

public interface TbUserRoleDao {

    //添加用户的同时添加用户角色
    public void addUserRole(int userId,int roleId);

    //修改用户的同时修改用户角色
    public void updateUserRole(int userId,int roleId );
}
