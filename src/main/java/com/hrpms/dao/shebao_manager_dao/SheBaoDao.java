package com.hrpms.dao.shebao_manager_dao;

import com.hrpms.pojo.TbSocialInsurance;
import com.hrpms.pojo.operaton_select.TbSocialInsuranceOperation;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.shebao_manager_dao > SheBaoDao
 * @description TODO
 * @create 2019/11/23  16:31
 * @versiion 1.0
 * @Description:
 */
public interface SheBaoDao {
    /**
     * 社保信息多条件查询  idCards没有值
     * @param
     * @return
     **/
    List<TbSocialInsurance> socialInsuranceQueryByOperation(String hql, TbSocialInsuranceOperation socialInsuranceOperation);
    /**
     * 社保多条件查询总条数  idCards没有值
     * @param
     * @return
     **/
    Long socialInsuranceQueryCountByOperation(String hql, TbSocialInsuranceOperation socialInsuranceOperation);

    /**
     * 获取社保表中的所有包含的可用的客户的身份证号
     * @param 
     * @return 
     **/
    List<Object> getUserableInSheBaoOfIdCard(String hql, String dictValue);
    /**
     * 社保信息添加
     * @param
     * @return 
     **/
    void shebaoAdd(TbSocialInsurance socialInsurance);
    /**
     * 通过编号查询详细信息
     * @param 
     * @return 
     **/
    TbSocialInsurance getSheBaoByIdCard(Integer id);
}
