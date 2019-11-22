package com.hrpms.service.company_client_service;

import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbCompanyOperation;
import com.hrpms.utils.Page;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.company_client_service > CompanyClientService
 * @description TODO
 * @create 2019/11/21  19:55
 * @versiion 1.0
 * @Description:
 */
public interface CompanyClientService {
    /**
     * 多条件分页查询公司客户信息
     * @param 
     * @return 
     **/
    Page<TbCompany> getCompanyByOperation(Integer currentPage, TbCompanyOperation companyOperation);
    /**
     * 根据id查询
     * @param 
     * @return 
     **/
    TbCompany getCompanyById(Integer id);

    /**
     * 根据  字典名称获取字典数据集合
     * @param 
     * @return 
     **/
    List<TbSystemDict> getDataDictByName(String name);
    
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
     * 删除公司客户  更改状态
     * @param 
     * @return 
     **/
    void companyDelete(Integer id, Integer updateById);
}
