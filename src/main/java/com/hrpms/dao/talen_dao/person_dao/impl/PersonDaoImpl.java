package com.hrpms.dao.talen_dao.person_dao.impl;

import com.hrpms.dao.talen_dao.person_dao.PersonDao;
import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.operaton_select.TbPersonOperation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
