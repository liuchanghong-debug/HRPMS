package com.hrpms.dao.user_manager_dao;

import com.hrpms.pojo.TbSystemUser;

import java.util.List;
import java.util.Map;

public interface SystemUserDao {

    //动态模糊分页查询系统用户
    public List<TbSystemUser> selectSystemUserByDuo(String hql, Map map);

    //动态查询系统用户总条数
    public Long selectSystemUserCount(String hql,Map map);

    //添加系统用户
    public void addSystemUser(TbSystemUser tbSystemUser);

    //根据id查询系统用户
    public TbSystemUser selectSystemUserById(int id);

    //根据id修改系统用户
    public void updateSystemUserById(TbSystemUser tbSystemUser);

    //根据id删除系统用户
    public void deleteSystemUserById(int id);

}
