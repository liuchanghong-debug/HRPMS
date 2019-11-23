package com.hrpms.dao.company_client_dao;

import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.operaton_select.TbCompanyOperation;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.company_client_dao > CompanyClientDao
 * @description TODO
 * @create 2019/11/21  19:56
 * @versiion 1.0
 * @Description:
 */
public interface CompanyClientDao {
    /**
     * 多条件分页查询公司客户信息
     * @param 
     * @return 
     **/
    List<TbCompany> getCompanyByOperation(String hql, TbCompanyOperation companyOperation);
    /**
     * 多条件查询公司客户总条数
     * @param 
     * @return 
     **/
    Long getCountCompanyByOperation(String hql, TbCompanyOperation companyOperation);
    /**
     * 根据id查询
     * @param
     * @return
     **/
    TbCompany getCompanyById(Integer id);
    
    /**
     * 添加公司客户
     * @param 
     * @return 
     **/
    void companyToAdd(TbCompany tbCompany);
    /**
     * 修改公司客户
     * @param 
     * @return 
     **/
    void companyToUpdate(TbCompany company);
    /**
     * 多条件不分页查询
     * @param 
     * @return 
     **/
    List<TbCompany> getCompanyByOperationNoPaging(String hql, TbCompanyOperation companyOperation);
    /**
     * 查询所有 id 和 name
     * @param 
     * @return 
     **/
    List<Object[]> getAllCompanyOfIdAndName();

}
