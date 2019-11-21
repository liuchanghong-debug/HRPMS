package com.hrpms.dao.personal_information_dao;

import com.hrpms.pojo.TbSystemUser;

public interface TbSystemUserDao {

    //登录
    public TbSystemUser login(String username,String password);
}
