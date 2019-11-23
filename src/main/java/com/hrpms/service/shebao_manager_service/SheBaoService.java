package com.hrpms.service.shebao_manager_service;

import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSocialInsurance;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbSocialInsuranceOperation;
import com.hrpms.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.shebao_manager_service > SheBaoService
 * @description TODO
 * @create 2019/11/23  16:32
 * @versiion 1.0
 * @Description:
 */
public interface SheBaoService {
    /**
     * 社保信息多条件查询
     * @param
     * @return
     **/
    Page<TbSocialInsurance> socialInsuranceQueryByOperation(Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation);
    /**
     * 社保详细信息展示
     * @param 
     * @return 
     **/
    TbSocialInsurance getSheBaoByIdCard(Integer id);
    /**
     * 根据身份正得到客户详细信息
     * @param
     * @return
     **/
    TbCustomer getCustomerByIdCard(String idCard);
    /**
     * 根据公司id 得到公司信息
     * @param 
     * @return 
     **/
    List<Object[]> getCompanys();
    

    /**
     * 社保信息添加
     * @param 
     * @return 
     **/
    void shebaoAdd(TbSocialInsurance socialInsurance, Integer createBy);
    /**
     * 获取社保信息中的所有可用的客户身份证  并查询到 客户信息中不包含可用id 的客户信息
     * @param 
     * @return 
     **/
    List<Map> getNotInSociaOfCustomer();
    /**
     * 得到详细的客户信息
     * @param 
     * @return 
     **/
    Map getDetailOfCustomerById(Integer id);
    /**
     * 获取字典中社保状态的值
     * @param
     * @return
     **/
    List<TbSystemDict> getDictBySheBao();

}
