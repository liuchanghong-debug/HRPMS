package com.hrpms.dao.menu_manager_dao.impl;

import com.hrpms.dao.menu_manager_dao.TbSystemFunctionDao;
import com.hrpms.pojo.TbSystemFunction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TbSystemFunctionDaoImpl implements TbSystemFunctionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addSystemFunction(TbSystemFunction tbSystemFunction) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbSystemFunction);
    }

    @Override
    public void updateSystemFunction(TbSystemFunction tbSystemFunction) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(tbSystemFunction);
    }
}
