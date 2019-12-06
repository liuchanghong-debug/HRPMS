package com.hrpms.service.business_menu_service.personal_information_service;

import com.hrpms.pojo.TbSystemUser;

public interface TbSystemUserService {

    //登录
    public TbSystemUser login(String username, String password);

    //修改密码
    public void updatePersonalPassword(int id,String password);
}
