package com.hrpms.dao.business_menu_dao.marketing_manager.impl;

import com.hrpms.dao.business_menu_dao.marketing_manager.TbSmsRecordDao;
import com.hrpms.pojo.TbSmsRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class TbSmsRecordDaoImpl implements TbSmsRecordDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbSmsRecord> selectSmsRecoredByDuo(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        List<TbSmsRecord> list = session.createQuery(hql)
                .setProperties(map)
                .setFirstResult((int) map.get("startIndex"))
                .setMaxResults((int) map.get("pageSize"))
                .list();
        return list;
    }

    @Override
    public Long selectSmsRecoredCount(String hql, Map map) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long)session.createQuery(hql)
                .setProperties(map)
                .uniqueResult();
        return count;
    }

    @Override
    public void addSmsRecored(TbSmsRecord tbSmsRecord) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tbSmsRecord);
    }

    @Override
    public TbSmsRecord selectSmsRecoredById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSmsRecord tbSmsRecord = (TbSmsRecord)session.get(TbSmsRecord.class, id);
        return tbSmsRecord;
    }

    @Override
    public void deleteSmsRecoredById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TbSmsRecord tbSmsRecord = (TbSmsRecord)session.get(TbSmsRecord.class, id);
        session.delete(tbSmsRecord);

    }
}
