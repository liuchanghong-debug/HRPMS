package com.hrpms.dao.system_setting_dao.role_manager_dao;

import com.hrpms.pojo.TbSystemRole;

import java.util.List;
import java.util.Map;

public interface TbSystemRoleDao {

    //动态模糊查询系统角色
    public List<TbSystemRole> selectSystemRoleByDuo(String hql, Map map);

    //动态查询总条数
    public Long selectSystemRloeCount(String hql,Map map);

    //添加角色信息
    public void addSystemRole(TbSystemRole tbSystemRole);

    //根据id查询角色信息
    public TbSystemRole selectSystemRoleById(int id);

    //根据id修改角色信息
    public void updateSystemRoleById(TbSystemRole tbSystemRole);

    //根据id删除角色信息（改变状态）
    public void deleteSystemRoleById(int id);

    //查询所有的用户角色名
    public List<TbSystemRole> selectAllRoleName();

    //角色名称唯一验证
    public TbSystemRole roleNameIsOne(String roleName);
}
