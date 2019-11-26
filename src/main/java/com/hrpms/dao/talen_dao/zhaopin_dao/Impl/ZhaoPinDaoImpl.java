package com.hrpms.dao.talen_dao.zhaopin_dao.Impl;

import com.hrpms.dao.talen_dao.zhaopin_dao.ZhaoPinDao;
import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.operaton_select.TbNeedJobOperation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.talen_dao.zhaopin_dao.Impl > ZhaoPinDaoImpl
 * @description TODO
 * @create 2019/11/25  17:48
 * @versiion 1.0
 * @Description:
 */
@Repository
public class ZhaoPinDaoImpl implements ZhaoPinDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbNeedJob> zhaopinList(String hql, TbNeedJobOperation needJobOperation) {
        return sessionFactory.getCurrentSession()
                .createQuery(hql).setProperties(needJobOperation)
                .setFirstResult(needJobOperation.getStartIndex())
                .setMaxResults(needJobOperation.getPageSize()).list();
    }

    @Override
    public Long zhaopinCount(String hql, TbNeedJobOperation needJobOperation) {
        return (Long)sessionFactory.getCurrentSession().createQuery(hql).setProperties(needJobOperation).uniqueResult();
    }

    @Override
    public void zhaopinAdd(TbNeedJob tbNeedJob) {
        sessionFactory.getCurrentSession().save(tbNeedJob);
    }

    @Override
    public TbNeedJob selectNeedJobById(int id) {
        return (TbNeedJob)sessionFactory.getCurrentSession().get(TbNeedJob.class, id);
    }

    @Override
    public void zhaopinUpdate(TbNeedJob tbNeedJob) {
        sessionFactory.getCurrentSession().merge(tbNeedJob);
    }
}
