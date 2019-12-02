package com.hrpms.dao.menu_manager_dao;

import com.hrpms.pojo.TbSystemFunction;

public interface TbSystemFunctionDao {

    //添加系统权限
    public void addSystemFunction(TbSystemFunction tbSystemFunction);

    //修改系统权限
    public void updateSystemFunction(TbSystemFunction tbSystemFunction);
}
