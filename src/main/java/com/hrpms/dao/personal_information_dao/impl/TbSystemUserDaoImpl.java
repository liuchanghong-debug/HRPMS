package com.hrpms.dao.personal_information_dao.impl;

import com.hrpms.dao.personal_information_dao.TbSystemUserDao;
import com.hrpms.pojo.TbSystemUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TbSystemUserDaoImpl implements TbSystemUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TbSystemUser login(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemUser tbSystemUser = (TbSystemUser)session.createQuery("from TbSystemUser where username=? and password=? and status=0")
                .setParameter(0, username)
                .setParameter(1, password)
                .uniqueResult();
        return tbSystemUser;
    }
}
