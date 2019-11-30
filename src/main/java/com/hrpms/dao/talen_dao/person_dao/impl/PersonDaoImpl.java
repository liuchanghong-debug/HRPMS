package com.hrpms.dao.talen_dao.person_dao.impl;

import com.hrpms.dao.talen_dao.person_dao.PersonDao;
import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.operaton_select.TbPersonOperation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.talen_dao.person_dao.impl > PersonDaoImpl
 * @description TODO
 * @create 2019/11/25  17:47
 * @versiion 1.0
 * @Description:
 */
@Repository
public class PersonDaoImpl implements PersonDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbPerson> personList(String hql, TbPersonOperation personOperation) {
        return sessionFactory.getCurrentSession().createQuery(hql).setProperties(personOperation).setFirstResult(personOperation.getStartIndex()).setMaxResults(personOperation.getPageSize()).list();
    }

    @Override
    public Long personCount(String hql, TbPersonOperation personOperation) {
        return (Long) sessionFactory.getCurrentSession().createQuery(hql).setProperties(personOperation).uniqueResult();
    }

    @Override
    public void personAdd(TbPerson person) {
        sessionFactory.getCurrentSession().save(person);
    }

    @Override
    public TbPerson personDetailById(Integer id) {
        return (TbPerson) sessionFactory.getCurrentSession().get(TbPerson.class, id);
    }

    @Override
    public void personUpdate(TbPerson person) {
        sessionFactory.getCurrentSession().update(person);
    }

    @Override
    public List<Object[]> getAllIdAndName(List normalStatus) {
        return sessionFactory.getCurrentSession().createQuery("select id, name from TbPerson where status in :status").setParameterList("status", normalStatus).list();
    }

    @Override
    public List<TbPerson> getPersonsByPrice(String hql, Map map) {
        return sessionFactory
                .getCurrentSession()
                .createQuery(hql)
                .setProperties(map)
                .setParameterList("status", (List) map.get("list"))
                .list();
    }

    @Override
    public Object personIdCardIsOnly(String hql, String idCard) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, idCard).uniqueResult();
    }

    @Override
    public Object personIdCardIsOnlyUpdate(String hql, Integer id, String idCard) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).setParameter(1, idCard).uniqueResult();
    }

    @Override
    public List<String> normalPersonOfIdCard(String hql, List<String> status) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameterList("status", status).list();
    }
}
