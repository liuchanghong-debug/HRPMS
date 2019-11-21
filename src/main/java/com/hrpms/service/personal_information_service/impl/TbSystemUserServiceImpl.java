package com.hrpms.service.personal_information_service.impl;

import com.hrpms.dao.personal_information_dao.TbSystemUserDao;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.personal_information_service.TbSystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TbSystemUserServiceImpl implements TbSystemUserService {
    @Autowired
    private TbSystemUserDao tbSystemUserDao;

    @Override
    public TbSystemUser login(String username, String password) {
        return tbSystemUserDao.login(username,password);
    }
}
