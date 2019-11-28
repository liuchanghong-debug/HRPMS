package com.hrpms.dao.sms_template_dao.impl;

import com.hrpms.dao.sms_template_dao.TbSmsTemplateDao;
import com.hrpms.pojo.TbSmsTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class TbSmsTemplateDaoImpl implements TbSmsTemplateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbSmsTemplate> selectSmsTemplateByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbSmsTemplate> list = session.createQuery(hql)
                .setProperties(map)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .list();
        return list;
    }

    @Override
    public Long selectSmsTemplateCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public void addSmsTemplate(TbSmsTemplate tbSmsTemplate) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbSmsTemplate);

    }

    @Override
    public TbSmsTemplate selectSmsTemplateById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSmsTemplate tbSmsTemplate = (TbSmsTemplate)session.get(TbSmsTemplate.class, id);
        return tbSmsTemplate;
    }

    @Override
    public void updateSmsTemplate(TbSmsTemplate tbSmsTemplate) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(tbSmsTemplate);

    }

    @Override
    public void deleteSmsTemplate(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSmsTemplate tbSmsTemplate = (TbSmsTemplate)session.get(TbSmsTemplate.class, id);
        session.delete(tbSmsTemplate);
    }

    @Override
    public TbSmsTemplate TemplateCodeIsOne(String template_code) {
        Session session = sessionFactory.getCurrentSession();
        TbSmsTemplate tbSmsTemplate = (TbSmsTemplate)session.createQuery("from TbSmsTemplate where template_code=?")
                .setParameter(0, template_code)
                .uniqueResult();
        return tbSmsTemplate;
    }
}
