package com.hrpms.dao.user_manager_dao.impl;

import com.hrpms.dao.user_manager_dao.SystemUserDao;
import com.hrpms.pojo.TbSystemUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class SystemUserDaoImpl implements SystemUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbSystemUser> selectSystemUserByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbSystemUser> list = session.createQuery(hql)
                                    .setProperties(map)
                                    .setFirstResult((int) map.get("startIndex"))
                                    .setMaxResults((int) map.get("pageSize"))
                                    .list();
        return list;
    }

    @Override
    public Long selectSystemUserCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public void addSystemUser(TbSystemUser tbSystemUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbSystemUser);
    }

    @Override
    public TbSystemUser selectSystemUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemUser tbSystemUser = (TbSystemUser)session.get(TbSystemUser.class,id);
        return tbSystemUser;
    }

    @Override
    public void updateSystemUserById(TbSystemUser tbSystemUser) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(tbSystemUser);

    }

    @Override
    public void deleteSystemUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemUser tbSystemUser = (TbSystemUser)session.get(TbSystemUser.class, id);
        tbSystemUser.setStatus("1");
        session.merge(tbSystemUser);
    }

    @Override
    public TbSystemUser isOneUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemUser tbSystemUser = (TbSystemUser)session.createQuery("from TbSystemUser where username=?")
                .setParameter(0, username)
                .uniqueResult();
        return tbSystemUser;
    }

    @Override
    public List<TbSystemUser> selectAllUserName() {
        Session session = sessionFactory.getCurrentSession();
        List<TbSystemUser> list = session.createQuery("from TbSystemUser").list();
        return list;
    }

    @Override
    public TbSystemUser isOneUserPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemUser tbSystemUser = (TbSystemUser)session.createQuery("from TbSystemUser where phone=?")
                .setParameter(0, phone)
                .uniqueResult();
        return tbSystemUser;
    }

    @Override
    public TbSystemUser isOneUserEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemUser tbSystemUser = (TbSystemUser)session.createQuery("from TbSystemUser where email=?")
                .setParameter(0,email)
                .uniqueResult();
        return tbSystemUser;
    }
}
