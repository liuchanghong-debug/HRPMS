package com.hrpms.dao.business_menu_dao.company_client_dao.impl;

import com.hrpms.dao.business_menu_dao.company_client_dao.CompanyClientDao;
import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.operaton_select.TbCompanyOperation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.company_client_dao.impl > CompanyClientDaoImpl
 * @description TODO
 * @create 2019/11/21  19:57
 * @versiion 1.0
 * @Description:
 */
@Repository
public class CompanyClientDaoImpl implements CompanyClientDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbCompany> getCompanyByOperation(String hql, TbCompanyOperation companyOperation) {
        return sessionFactory.getCurrentSession().createQuery(hql).setProperties(companyOperation).setFirstResult(companyOperation.getStartIndex()).setMaxResults(companyOperation.getPageSize()).list();
    }

    @Override
    public Long getCountCompanyByOperation(String hql, TbCompanyOperation companyOperation) {
        return (Long)sessionFactory.getCurrentSession().createQuery(hql).setProperties(companyOperation).uniqueResult();
    }

    @Override
    public TbCompany getCompanyById(Integer id) {
        return (TbCompany) sessionFactory.getCurrentSession().get(TbCompany.class, id);
    }

    @Override
    public void companyToAdd(TbCompany tbCompany) {
        sessionFactory.getCurrentSession().save(tbCompany);
    }

    @Override
    public void companyToUpdate(TbCompany company) {
        sessionFactory.getCurrentSession().update(company);
    }

    @Override
    public List<TbCompany> getCompanyByOperationNoPaging(String hql, TbCompanyOperation companyOperation) {
        return sessionFactory.getCurrentSession().createQuery(hql).setProperties(companyOperation).list();
    }

    @Override
    public List<Object[]> getAllCompanyOfIdAndName() {
        return sessionFactory.getCurrentSession().createQuery("select id, name from TbCompany").list();
    }

    @Override
    public Object companyNoIsOnly(String hql, String companyNo) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyNo).uniqueResult();

    }

    @Override
    public Object companyNoIsOnlyOnUpdate(String hql, Integer companyId, String companyNo) {
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId).setParameter(1, companyNo).uniqueResult();
    }
}
