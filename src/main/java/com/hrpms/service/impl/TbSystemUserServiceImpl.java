package com.hrpms.service.impl;

import com.hrpms.dao.TbSystemUserDao;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.TbSystemUserService;
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
