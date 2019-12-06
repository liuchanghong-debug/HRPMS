package com.hrpms.dao.business_menu_dao.salary_manager_dao.impl;

import com.hrpms.dao.business_menu_dao.salary_manager_dao.TbSalaryDao;
import com.hrpms.pojo.TbSalary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class TbSalaryDaoImpl implements TbSalaryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbSalary> selectSalaryByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbSalary> list = session.createQuery(hql)
                .setProperties(map)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .list();
        return list;
    }

    @Override
    public Long selectSalaryCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public void addSalary(TbSalary tbSalary) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbSalary);
    }

    @Override
    public TbSalary selectSalaryById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSalary tbSalary = (TbSalary)session.get(TbSalary.class, id);
        return tbSalary;
    }

    @Override
    public void updateSalaryById(TbSalary tbSalary) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(tbSalary);
    }

    @Override
    public void deleteSalaryById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSalary tbSalary = (TbSalary)session.get(TbSalary.class, id);
        session.delete(tbSalary);
    }

    @Override
    public List<TbSalary> selectSalaryByNoFen(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbSalary> list = session.createQuery(hql)
                .setProperties(map)
                .list();
        return list;
    }

    @Override
    public Double getSalaryByIdCard(String hql) {
        return (Double) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
    }


    @Override
    public TbSalary getTbSalaryByIdCard(String hql, String idCard) {
        return (TbSalary) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, idCard).uniqueResult();
    }

    @Override
    public TbSalary payCardIsOne(String payCard) {
        Session session = sessionFactory.getCurrentSession();
        TbSalary tbSalary = (TbSalary)session.createQuery("from TbSalary where payCard = ? and name is null")
                                                .setParameter(0, payCard)
                                                 .uniqueResult();
        return tbSalary;
    }

    @Override
    public TbSalary selectSalaryByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        TbSalary tbSalary = (TbSalary)session.createQuery("from TbSalary where name = ?")
                .setParameter(0, name)
                .setMaxResults(1)
                .uniqueResult();
        return tbSalary;
    }
}
