package com.hrpms.dao.user_manager_dao;

public interface TbUserRoleDao {

    //添加用户的同时添加用户角色
    public void addUserRole(int roleId);

    //修改用户的同时修改用户角色
    public void updateUserRole(int userId,int roleId );
}
