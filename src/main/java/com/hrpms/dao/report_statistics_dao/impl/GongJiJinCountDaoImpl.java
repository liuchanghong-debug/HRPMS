package com.hrpms.dao.report_statistics_dao.impl;

import com.hrpms.dao.report_statistics_dao.GongJiJinCountDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class GongJiJinCountDaoImpl implements GongJiJinCountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Object[]> selectGongJiJInCountByDuo(String sql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<Object[]> list = session.createSQLQuery(sql)
                .setProperties(map)
                .list();
        return list;
    }
}
