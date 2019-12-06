package com.hrpms.dao.business_menu_dao.personal_information_dao;

import com.hrpms.pojo.TbSystemUser;

public interface TbSystemUserDao {

    //登录
    public TbSystemUser login(String username,String password);

    //修改密码
    public void updatePersonalPassword(int id,String password);
}
