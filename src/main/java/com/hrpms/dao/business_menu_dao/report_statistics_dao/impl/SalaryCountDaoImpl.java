package com.hrpms.dao.business_menu_dao.report_statistics_dao.impl;

import com.hrpms.dao.business_menu_dao.report_statistics_dao.SalaryCountDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class SalaryCountDaoImpl implements SalaryCountDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Object[]> selectSalaryCountByDuo(String sql,Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<Object[]> list = session.createSQLQuery(sql)
                                        .setProperties(map)
                                        .list();
        return list;
    }
}
