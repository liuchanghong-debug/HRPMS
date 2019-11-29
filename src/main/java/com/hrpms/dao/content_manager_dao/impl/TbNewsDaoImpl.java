package com.hrpms.dao.content_manager_dao.impl;

import com.hrpms.dao.content_manager_dao.TbNewsDao;
import com.hrpms.pojo.TbNews;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class TbNewsDaoImpl implements TbNewsDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<TbNews> selectTbNews(String hql, Map map) {
       return  sessionFactory.getCurrentSession().createQuery(hql)
                        .setFirstResult((int)map.get("startIndex"))
                        .setMaxResults((int)map.get("pageSize"))
                        .setProperties(map)
                        .list();
    }
    @Override
    public long selectTbNewsCount(String hql, Map map) {
        return (long)sessionFactory.getCurrentSession().createQuery(hql)
                        .setProperties(map).uniqueResult();
    }

    @Override
    public int saveTbNews(TbNews news) {
        return (int)sessionFactory.getCurrentSession().save(news);
    }


}
