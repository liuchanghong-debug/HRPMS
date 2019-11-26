package com.hrpms.dao.email_template_dao.impl;

import com.hrpms.dao.email_template_dao.TbEmailTemplateDao;
import com.hrpms.pojo.TbEmailTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TbEmailTemplateDaoImpl implements TbEmailTemplateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbEmailTemplate> selectEmailTemplateByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbEmailTemplate> list = session.createQuery(hql)
                .setProperties(map)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .list();
        return list;
    }

    @Override
    public Long selectEmailTemplateCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public void addEmailTemplate(TbEmailTemplate tbEmailTemplate) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbEmailTemplate);
    }

    @Override
    public TbEmailTemplate selectEmailTemplateById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbEmailTemplate tbEmailTemplate = (TbEmailTemplate)session.get(TbEmailTemplate.class, id);
        return tbEmailTemplate;
    }

    @Override
    public void updateEmailTemplate(TbEmailTemplate tbEmailTemplate) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(tbEmailTemplate);
    }

    @Override
    public void deleteEmailTemplate(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbEmailTemplate tbEmailTemplate = (TbEmailTemplate)session.get(TbEmailTemplate.class, id);
        session.delete(tbEmailTemplate);
    }
}
