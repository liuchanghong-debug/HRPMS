package com.hrpms.service.personal_information_service;

import com.hrpms.pojo.TbSystemUser;

public interface TbSystemUserService {

    //登录
    public TbSystemUser login(String username, String password);
}
