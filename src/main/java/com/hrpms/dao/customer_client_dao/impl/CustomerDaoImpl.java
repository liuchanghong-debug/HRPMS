package com.hrpms.dao.customer_client_dao.impl;

import com.hrpms.dao.customer_client_dao.CustomerDao;
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
    public List<TbCustomer> selectAllCustomerName() {
        Session session = sessionFactory.getCurrentSession();
        List<TbCustomer> list = session.createQuery("from TbCustomer where isSalary = 0 ").list();
        return list;
    }
}
