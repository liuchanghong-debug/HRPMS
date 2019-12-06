package com.hrpms.service.system_setting_service.role_manager_service;

import com.hrpms.pojo.TbSystemRole;
import com.hrpms.utils.Page;

import java.util.List;
import java.util.Map;

public interface TbSystemRoleService {

    //动态模糊查询系统角色
    public Page<TbSystemRole> selectSystemRoleByDuo(Integer currentPage, Map map);

    //添加角色信息(同时分配权限)
    public void addSystemRole(TbSystemRole tbSystemRole,String menuIds);

    //根据id查询角色信息
    public TbSystemRole selectSystemRoleById(int id);

    //根据id修改角色信息(同时修改权限)
    public void updateSystemRoleById(TbSystemRole tbSystemRole,String menuIds);

    //根据id删除角色信息（改变状态）
    public void deleteSystemRoleById(int id);

    //查询所有的用户角色名
    public List<TbSystemRole> selectAllRoleName();

    //角色名称唯一验证
    public boolean roleNameIsOne(String roleName);
}
