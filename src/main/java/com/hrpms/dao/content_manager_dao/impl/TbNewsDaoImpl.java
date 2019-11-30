package com.hrpms.dao.content_manager_dao.impl;

import com.hrpms.dao.content_manager_dao.TbNewsDao;
import com.hrpms.pojo.TbNews;
import org.hibernate.Session;
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
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .setProperties(map)
                .list();
    }

    @Override
    public long selectTbNewsCount(String hql, Map map) {
        return (long) sessionFactory.getCurrentSession().createQuery(hql)
                .setProperties(map).uniqueResult();
    }

    @Override
    public int saveTbNews(TbNews news) {
        return (int) sessionFactory.getCurrentSession().save(news);
    }

    @Override
    public void deleteTbNews(int id) {
        Session session = sessionFactory.getCurrentSession();
        /*TbNews tbnews = (TbNews) session.get(TbNews.class, id);
        session.delete(tbnews);*/
        session.createSQLQuery("delete from tbnews where id=?")
                .setParameter(0,id)
                .executeUpdate();
    }

    @Override
    public TbNews selectTbNewsById(int id) {
        return (TbNews) sessionFactory.getCurrentSession().get(TbNews.class,id);
    }

    @Override
    public void updateTbNews(TbNews news) {
        sessionFactory.getCurrentSession().
                createSQLQuery("update tbnews set newsTitle=:newsTitle," +
                        "content=:content,status=:status,updateTime=:updateTime," +
                        "updateBy=:updateBy,remark=:remark where id=:id")
                .setProperties(news)
                .executeUpdate();
    }


}
