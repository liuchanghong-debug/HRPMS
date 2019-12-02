package com.hrpms.dao.role_manager_dao.impl;

import com.hrpms.dao.role_manager_dao.TbSystemRoleDao;
import com.hrpms.pojo.TbSystemRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TbSystemRoleDaoImpl implements TbSystemRoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbSystemRole> selectSystemRoleByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbSystemRole> list = session.createQuery(hql)
                .setProperties(map)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .list();
        return list;
    }

    @Override
    public Long selectSystemRloeCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public void addSystemRole(TbSystemRole tbSystemRole) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbSystemRole);
    }

    @Override
    public TbSystemRole selectSystemRoleById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemRole tbSystemRole = (TbSystemRole)session.get(TbSystemRole.class, id);
        return tbSystemRole;
    }

    @Override
    public void updateSystemRoleById(TbSystemRole tbSystemRole) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(tbSystemRole);
    }

    @Override
    public void deleteSystemRoleById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSystemRole tbSystemRole = (TbSystemRole)session.get(TbSystemRole.class, id);
        tbSystemRole.setStatus("1");
        session.merge(tbSystemRole);

    }
}
