package com.hrpms.dao.system_setting_dao.data_dict_dao.impl;

import com.hrpms.dao.system_setting_dao.data_dict_dao.DataDictDao;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbSystemDictOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.system_setting_dao.data_dict_dao.impl > DataDictDaoImpl
 * @description TODO
 * @create 2019/11/21  15:01
 * @versiion 1.0
 * @Description:
 */
@Repository
public class DataDictDaoImpl implements DataDictDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * 添加数据字典
     * @param 
     * @return 
     **/
    @Override
    public void dataDictAdd(TbSystemDict dataDict) {
        sessionFactory.getCurrentSession().save(dataDict);
    }

    @Override
    public List<TbSystemDict> getDataDictByName(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("from TbSystemDict where name = ?")
                .setParameter(0, name).list();
    }


    @Override
    public List<TbSystemDict> getDataDIctByOperation(String hql, TbSystemDictOperation dataDictOperation) {
        return sessionFactory.getCurrentSession().createQuery(hql).setProperties(dataDictOperation).setFirstResult(dataDictOperation.getStartIndexQuery()).setMaxResults(dataDictOperation.getPageSize()).list();
    }

    @Override
    public Long getCountDataDictByOperation(String hql, TbSystemDictOperation dataDictOperation) {
        return (long) sessionFactory.getCurrentSession().createQuery(hql).setProperties(dataDictOperation).uniqueResult();
    }

    @Override
    public TbSystemDict getDataDictById(Integer id) {
        return (TbSystemDict) sessionFactory.getCurrentSession().get(TbSystemDict.class, id);
    }

    @Override
    public void dataDictUpdate(TbSystemDict tbSystemDict) {
        sessionFactory.getCurrentSession().update(tbSystemDict);
    }

    @Override
    public void dataDictDelete(TbSystemDict tbSystemDict) {
        sessionFactory.getCurrentSession().update(tbSystemDict);
    }

    @Override
    public String getDataDictValueByNameAndLabel(Map map) {
        TbSystemDict tbSystemDict = (TbSystemDict) sessionFactory.getCurrentSession().createQuery((String) map.get("hql")).setProperties(map).uniqueResult();
        return tbSystemDict.getValue();
    }

}
