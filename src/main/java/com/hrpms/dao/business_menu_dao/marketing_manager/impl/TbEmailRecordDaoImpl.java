package com.hrpms.dao.business_menu_dao.marketing_manager.impl;

import com.hrpms.dao.business_menu_dao.marketing_manager.TbEmailRecordDao;
import com.hrpms.pojo.TbEmailRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TbEmailRecordDaoImpl implements TbEmailRecordDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbEmailRecord> selectEmailRecoredByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbEmailRecord> list = session.createQuery(hql)
                .setProperties(map)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .list();
        return list;
    }

    @Override
    public Long selectEmailRecoredCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public void addEmailRecored(TbEmailRecord tbEmailRecord) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbEmailRecord);
    }

    @Override
    public TbEmailRecord selectEmailRecoredById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbEmailRecord tbEmailRecord = (TbEmailRecord)session.get(TbEmailRecord.class, id);
        return tbEmailRecord;
    }

    @Override
    public void deleteEmailRecored(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbEmailRecord tbEmailRecord = (TbEmailRecord)session.get(TbEmailRecord.class, id);
        session.delete(tbEmailRecord);

    }
}
