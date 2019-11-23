package com.hrpms.dao.shebao_manager_dao.impl;

import com.hrpms.dao.shebao_manager_dao.SheBaoDao;
import com.hrpms.pojo.TbSocialInsurance;
import com.hrpms.pojo.operaton_select.TbSocialInsuranceOperation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.shebao_manager_dao.impl > SheBaoDaoImpl
 * @description TODO
 * @create 2019/11/23  16:31
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional
public class SheBaoDaoImpl implements SheBaoDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbSocialInsurance> socialInsuranceQueryByOperation(String hql, TbSocialInsuranceOperation socialInsuranceOperation) {
        return sessionFactory.getCurrentSession().createQuery(hql).setProperties(socialInsuranceOperation).list();
    }

    @Override
    public Long socialInsuranceQueryCountByOperation(String hql, TbSocialInsuranceOperation socialInsuranceOperation) {
        return (Long) sessionFactory.getCurrentSession().createQuery(hql).setProperties(socialInsuranceOperation).uniqueResult();
    }


    @Override
    public List<Object> getUserableInSheBaoOfIdCard(String hql, String dictValue) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, dictValue).list();
    }

    @Override
    public void shebaoAdd(TbSocialInsurance socialInsurance) {
        sessionFactory.getCurrentSession().save(socialInsurance);
    }

    @Override
    public TbSocialInsurance getSheBaoByIdCard(Integer id) {
        return (TbSocialInsurance) sessionFactory.getCurrentSession().load(TbSocialInsurance.class, id);
    }
}
