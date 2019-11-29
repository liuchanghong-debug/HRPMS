package com.hrpms.dao.shebao_manager_dao.impl;

import com.hrpms.dao.shebao_manager_dao.SheBaoDao;
import com.hrpms.pojo.TbSocialInsurance;
import com.hrpms.pojo.TbSocialInsuranceRecord;
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
    public List socialInsuranceQueryByOperationPaging(String hql, TbSocialInsuranceOperation socialInsuranceOperation) {
        return sessionFactory.getCurrentSession().createQuery(hql).setProperties(socialInsuranceOperation).setFirstResult(socialInsuranceOperation.getStartIndex()).setMaxResults(socialInsuranceOperation.getPageSize()).list();
    }

    @Override
    public Long socialInsuranceQueryCountByOperation(String hql, TbSocialInsuranceOperation socialInsuranceOperation) {
        return (Long) sessionFactory.getCurrentSession().createQuery(hql).setProperties(socialInsuranceOperation).uniqueResult();
    }


    @Override
    public List<String> getUserableInSheBaoOfIdCard(String hql, String dictValue) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, dictValue).list();
    }

    @Override
    public void shebaoAdd(TbSocialInsurance socialInsurance) {
        sessionFactory.getCurrentSession().save(socialInsurance);
    }

    @Override
    public void shebaoRecordAdd(TbSocialInsuranceRecord socialInsuranceRecord) {
        sessionFactory.getCurrentSession().save(socialInsuranceRecord);
    }

    @Override
    public TbSocialInsurance getSheBaoById(Integer id) {
        return (TbSocialInsurance) sessionFactory.getCurrentSession().get(TbSocialInsurance.class, id);
    }

    @Override
    public void shebaoUpdate(TbSocialInsurance socialInsurance) {
        sessionFactory.getCurrentSession().update(socialInsurance);
    }

    @Override
    public List<String> getSbCardBySocialInsuranceRecord(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<TbSocialInsurance> getSocialInsurances(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<TbSocialInsurance> getSocialInsurancesByList(String hql, List<String> sbCards) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameterList("sbCards", sbCards).list();
    }

    @Override
    public TbSocialInsuranceRecord getSheBaoRecordById(Integer id) {
        return (TbSocialInsuranceRecord) sessionFactory.getCurrentSession().get(TbSocialInsuranceRecord.class, id);
    }

    @Override
    public void shebaoRecordUpdate(TbSocialInsuranceRecord socialInsuranceRecord) {
        sessionFactory.getCurrentSession().update(socialInsuranceRecord);
    }

    @Override
    public Object shebaoRecordByIdCard(String idCard) {
        return sessionFactory.getCurrentSession().createQuery("select id from TbSocialInsuranceRecord where idCard = " + idCard).uniqueResult();
    }

    @Override
    public Object sbCardIsOnly(String hql, String sbCard) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, sbCard).uniqueResult();
    }

    @Override
    public Object shebaoSbCardIsOnlyUpdate(String hql, Integer id, String sbCard) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).setParameter(1, sbCard).uniqueResult();
    }
}
