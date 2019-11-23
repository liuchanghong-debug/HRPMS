package com.hrpms.dao.user_manager_dao.impl;

import com.hrpms.dao.user_manager_dao.TbUserRoleDao;
import com.hrpms.pojo.TbUserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TbUserRoleDaoImpl implements TbUserRoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUserRole(int roleId) {
        Session session = sessionFactory.getCurrentSession();
        TbUserRole tbUserRole = new TbUserRole();
        tbUserRole.setRoleId(roleId);
        session.save(tbUserRole);
    }

    @Override
    public void updateUserRole(int userId, int roleId) {
        Session session = sessionFactory.getCurrentSession();
        TbUserRole tbUserRole = (TbUserRole)session.get(TbUserRole.class, userId);
        tbUserRole.setRoleId(roleId);
        session.merge(tbUserRole);
    }
}
