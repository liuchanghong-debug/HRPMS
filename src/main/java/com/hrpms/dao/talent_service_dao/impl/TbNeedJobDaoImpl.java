package com.hrpms.dao.talent_service_dao.impl;

import com.hrpms.dao.talent_service_dao.TbNeedJobDao;
import com.hrpms.pojo.TbNeedJob;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TbNeedJobDaoImpl implements TbNeedJobDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbNeedJob> selectNeedJobByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbNeedJob> list = session.createQuery(hql)
                .setProperties(map)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .list();
        return list;
    }

    @Override
    public Long selectNeedJobCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public void addNeedJob(TbNeedJob tbNeedJob) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbNeedJob);

    }

    @Override
    public TbNeedJob selectNeedJobById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbNeedJob tbNeedJob = (TbNeedJob)session.get(TbNeedJob.class, id);
        return tbNeedJob;
    }

    @Override
    public void updateNeedJob(TbNeedJob tbNeedJob) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(tbNeedJob);

    }

    @Override
    public void deleteNeedJob(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbNeedJob tbNeedJob = (TbNeedJob)session.get(TbNeedJob.class, id);
        tbNeedJob.setStatus("1");
        session.merge(tbNeedJob);
    }
}
