package com.hrpms.dao.business_menu_dao.gongjijin_manger_dao.impl;

import com.hrpms.dao.business_menu_dao.gongjijin_manger_dao.TbAccumulationFundDao;
import com.hrpms.pojo.TbAccumulationFund;
import com.hrpms.pojo.TbSystemFunction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TbAccumulationDaoImpl implements TbAccumulationFundDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbAccumulationFund> selectAccumulationByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbAccumulationFund> list = session.createQuery(hql)
                .setProperties(map)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .list();
        return list;
    }

    @Override
    public Long selectAccumulationCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public void addAccumulation(TbAccumulationFund tbAccumulationFund) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbAccumulationFund);

    }

    @Override
    public TbAccumulationFund selectAccumulationById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbAccumulationFund tbAccumulationFund = (TbAccumulationFund)session.get(TbAccumulationFund.class, id);
        return tbAccumulationFund;
    }

    @Override
    public void updateAccumulationById(TbAccumulationFund tbAccumulationFund) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(tbAccumulationFund);
    }

    @Override
    public void deleteAccumulationById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbAccumulationFund tbAccumulationFund = (TbAccumulationFund)session.get(TbAccumulationFund.class, id);
        session.delete(tbAccumulationFund);
    }

    @Override
    public List<TbAccumulationFund> selectAccumulationByNoFen(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbAccumulationFund> list = session.createQuery(hql)
                .setProperties(map)
                .list();
        return list;
    }

    @Override
    public TbAccumulationFund accountNoIsOne(String accountNo) {
        Session session = sessionFactory.getCurrentSession();
        TbAccumulationFund tbAccumulationFund = (TbAccumulationFund)session.createQuery("from TbAccumulationFund where accountNo = ? and name is null")
                .setParameter(0, accountNo)
                .uniqueResult();
        return tbAccumulationFund;
    }

    @Override
    public TbAccumulationFund getAccumulationFundByIdCard(String hql, String idCard) {
        return (TbAccumulationFund)sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, idCard).uniqueResult();
    }

    @Override
    public TbAccumulationFund selectAccumulationByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        TbAccumulationFund tbAccumulationFund = (TbAccumulationFund)session.createQuery("from TbAccumulationFund where name = ?")
                    .setParameter(0, name)
                    .setMaxResults(1)
                    .uniqueResult();
        return tbAccumulationFund;
    }

    @Override
    public List<TbAccumulationFund> gongJiJinByStatusForPhone() {
        Session session = sessionFactory.getCurrentSession();
        List<TbAccumulationFund> list = session
                    .createQuery("from TbAccumulationFund where status =0")
                    .list();
        return list;
    }
}
