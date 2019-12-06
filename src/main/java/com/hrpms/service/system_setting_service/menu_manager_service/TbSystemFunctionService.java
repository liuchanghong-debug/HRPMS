package com.hrpms.service.system_setting_service.menu_manager_service;

import com.hrpms.pojo.TbSystemFunction;
import com.hrpms.utils.Page;

import java.util.List;
import java.util.Map;


public interface TbSystemFunctionService {

    //查询所有的权限名称
    public List selectAllSystemFunctionName();

    //根据id查询所有
    public TbSystemFunction selectFunctionById(int id);

    //动态模糊分页查询菜单列表
    public Page<TbSystemFunction> selectSystemFunctionByDuo(Integer currentPage, Map map);

    //根据id删除权限名称（更改权限状态）
    public void deleteSystemFunctionById(int id);
}
