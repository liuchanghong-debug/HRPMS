package com.hrpms.dao.system_setting_dao.menu_manager_dao;

import com.hrpms.pojo.TbSystemFunction;

import java.util.List;
import java.util.Map;

public interface TbSystemFunctionDao {

    //动态模糊分页查询菜单列表
    public List<TbSystemFunction> selectSystemFunctionByDuo(String hql, Map map);

    //动态模糊分页查询总条数
    public Long selectSystemFunctionCount(String hql,Map map);

    //根据id查询系统权限
    public TbSystemFunction selectSystemFunctionById(int id);

    //添加系统权限
    public void addSystemFunction(TbSystemFunction tbSystemFunction);

    //修改系统权限
    public void updateSystemFunction(TbSystemFunction tbSystemFunction);

    //查询所有的权限名称
    public List<TbSystemFunction> selectAllSystemFunctionName();

    //根据id删除权限名称（更改权限状态）
    public void deleteSystemFunctionById(int id);
}
