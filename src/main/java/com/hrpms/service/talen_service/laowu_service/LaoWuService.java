package com.hrpms.service.talen_service.laowu_service;

import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbSystemDict;

import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.talen_service.laowu > LaoWuService
 * @description TODO
 * @create 2019/11/25  17:38
 * @versiion 1.0
 * @Description:
 */
public interface LaoWuService {
    
    
    
    /**
     * 根据name获取字典值
     * @param 
     * @return 
     **/
    List<TbSystemDict> getDictsByName(String name);

    /**
     * 获取所有客户名称和id
     * @param
     * @return
     **/
    List<Object[]> getAllPersonIdAndName();
    /**
     * 根据客户id 查询idCard
     * @param
     * @return
     **/
    Map getPersonAndCompanyById(Integer id);
    /**
     * 获取所有招聘信息公司id
     * @param
     * @return 
     **/
    List<Integer> getAllCompanyIdAndName();
    /**
     * 获取所有公司信息
     * @param
     * @return
     **/
    List<Object[]> getAllCompanys();
    /**
     * 根据价格查询招聘公司id
     * @param
     * @return
     **/
    List<TbCompany> getNeedJobsByJobType(Double price);
    /**
     * 根据公司得到所有职位信息
     * @param
     * @return
     **/
    List<TbNeedJob> getAllJobByCompanyId(Integer id);
    /**
     * 根据id得到详细信息
     * @param
     * @return
     **/
    TbNeedJob getDetailById(Integer id);
}
