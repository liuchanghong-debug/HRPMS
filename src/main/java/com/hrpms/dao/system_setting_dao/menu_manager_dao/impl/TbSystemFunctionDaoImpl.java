package com.hrpms.dao.system_setting_dao.menu_manager_dao.impl;

import com.hrpms.dao.system_setting_dao.menu_manager_dao.TbSystemFunctionDao;
import com.hrpms.pojo.TbSystemFunction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TbSystemFunctionDaoImpl implements TbSystemFunctionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbSystemFunction> selectSystemFunctionByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbSystemFunction> list = session.createQuery(hql)
                .setProperties(map)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .list();
        return list;
    }

    @Override
    public Long selectSystemFunctionCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public TbSystemFunction selectSystemFunctionById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemFunction tbSystemFunction = (TbSystemFunction)session.get(TbSystemFunction.class, id);
        return tbSystemFunction;
    }

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

    @Override
    public List<TbSystemFunction> selectAllSystemFunctionName() {
        Session session = sessionFactory.getCurrentSession();
        List<TbSystemFunction> tbSystemFunctions = session.createQuery("from TbSystemFunction").list();
        return tbSystemFunctions;
    }

    @Override
    public void deleteSystemFunctionById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemFunction tbSystemFunction = (TbSystemFunction)session.get(TbSystemFunction.class, id);
        tbSystemFunction.setStatus("1");
        session.merge(tbSystemFunction);
    }
}
