package com.hrpms.dao.customer_client_dao.impl;

import com.hrpms.dao.customer_client_dao.CustomerDao;
import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.operaton_select.TbCustomerOperation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.hrpms.pojo.TbCustomer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.customer_client_dao.impl > CustomerDaoImpl
 * @description TODO
 * @create 2019/11/22  16:56
 * @versiion 1.0
 * @Description:
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbCustomer> customerAllByOperationAndPaging(String hql, TbCustomerOperation customerOperation) {
        return sessionFactory.getCurrentSession().createQuery(hql).setProperties(customerOperation).setFirstResult(customerOperation.getStartIndex()).setMaxResults(customerOperation.getPageSize()).list();
    }

    @Override
    public Long customerCountByOperation(String hql, TbCustomerOperation customerOperation) {
        return (Long) sessionFactory.getCurrentSession().createQuery(hql).setProperties(customerOperation).uniqueResult();
    }

    @Override
    public TbCustomer customerById(Integer id) {
        return (TbCustomer) sessionFactory.getCurrentSession().load(TbCustomer.class, id);
    }

    @Override
    public void customerToAdd(TbCustomer tbCustomer) {
        sessionFactory.getCurrentSession().save(tbCustomer);
    }

    @Override
    public void customerUpdate(TbCustomer customer) {
        sessionFactory.getCurrentSession().merge(customer);
    }

    @Override
    public List<TbCustomer> costomerByOperationOutOfExcel(String hql, TbCustomerOperation customerOperation) {
        return sessionFactory.getCurrentSession().createQuery(hql).setProperties(customerOperation).list();
    }

    @Override
    public List<Object[]> getDataOfNotInList(String hql, List list) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameterList("idCard", list).list();
    }

    @Override
    public List<Object[]> getData(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public TbCustomer getCustomerByIdCard(String hql, String idCard) {
        return (TbCustomer) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, idCard).uniqueResult();
    }
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbCustomer> selectAllCustomerName() {
        Session session = sessionFactory.getCurrentSession();
        List<TbCustomer> list = session.createQuery("from TbCustomer where isSalary = 0 ").list();
        return list;
    }
}
