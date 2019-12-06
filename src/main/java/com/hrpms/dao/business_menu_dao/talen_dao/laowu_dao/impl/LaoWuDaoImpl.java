package com.hrpms.dao.business_menu_dao.talen_dao.laowu_dao.impl;

import com.hrpms.dao.business_menu_dao.talen_dao.laowu_dao.LaoWuDao;
import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbPersonJob;
import com.hrpms.pojo.operaton_select.TbPersonJobOperation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.talen_dao.laowu_dao.impl > LaoWuDaoImpl
 * @description TODO
 * @create 2019/11/25  17:47
 * @versiion 1.0
 * @Description:
 */
@Repository
public class LaoWuDaoImpl implements LaoWuDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void personJobAdd(TbPersonJob personJob) {
        sessionFactory.getCurrentSession().save(personJob);
    }

    @Override
    public List<TbPersonJob> personList(String hql, TbPersonJobOperation personJobOperation) {
        return sessionFactory
                .getCurrentSession()
                .createQuery(hql)
                .setProperties(personJobOperation)
                .setFirstResult(personJobOperation.getStartIndex())
                .setMaxResults(personJobOperation.getPageSize())
                .list();
    }

    @Override
    public Long personJobCount(String hql, TbPersonJobOperation personJonOperation) {
        return (Long) sessionFactory
                .getCurrentSession()
                .createQuery(hql)
                .setProperties(personJonOperation)
                .uniqueResult();
    }

    @Override
    public void personOperation(TbPersonJob personJob) {
        sessionFactory
                .getCurrentSession()
                .update(personJob);
    }

    @Override
    public TbPersonJob getPersonJobById(Integer id) {
        return (TbPersonJob) sessionFactory.getCurrentSession().get(TbPersonJob.class, id);
    }

    @Override
    public List<TbNeedJob> getAllNeedJobs(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public TbPersonJob personJobByIdCard(String idCard) {
        return (TbPersonJob) sessionFactory.getCurrentSession().createQuery("from TbPersonJob where idCard = ?").setParameter(0, idCard).uniqueResult();
    }
}
