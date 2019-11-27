package com.hrpms.service.user_manager_service;

import com.hrpms.pojo.TbSystemUser;
import com.hrpms.utils.Page;

import java.util.Map;

public interface SystemUserService {
    //动态模糊分页查询系统用户
    public Page<TbSystemUser> selectSystemUserByDuo(Integer currentPage, Map map);

    //添加系统用户
    public void addSystemUser(TbSystemUser tbSystemUser,int roleId);

    //根据id查询系统用户
    public TbSystemUser selectSystemUserById(int id);

    //根据id修改系统用户
    public void updateSystemUserById(TbSystemUser tbSystemUser,int roleId);

    //根据id删除系统用户
    public void deleteSystemUserById(int id);

    public TbSystemUser isOneUsername(String username);

}
