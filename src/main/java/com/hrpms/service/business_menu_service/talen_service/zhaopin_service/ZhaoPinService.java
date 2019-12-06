package com.hrpms.service.business_menu_service.talen_service.zhaopin_service;

import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbNeedJobOperation;
import com.hrpms.utils.Page;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.talen_service.zhaopin > ZhaoPinService
 * @description TODO
 * @create 2019/11/25  17:39
 * @versiion 1.0
 * @Description:
 */
public interface ZhaoPinService {
    /**
     * 动态模糊分页查询招聘信息
     * @param
     * @return 
     **/
    Page<TbNeedJob> zhaopinList(Integer currentPage, TbNeedJobOperation needJobOperation);
    /**
     *添加招聘信息
     * @param
     * @return
     **/
    void zhaopinAdd(TbNeedJob tbNeedJob, Integer createBy);
    /**
     *根据id查看招聘信息
     * @param
     * @return
     **/
    TbNeedJob selectNeedJobById(Integer id);
    /**
     *根据id修改招聘信息
     * @param
     * @return
     **/
    void zhaopinUpdate(TbNeedJob tbNeedJob, Integer updateBy);
    /**
     *根据id删除招聘信息(实则修改招聘状态)
     * @param
     * @return
     **/
    void zhaopinDelete(Integer id, Integer updateBy);
    
    
    
    /**
     * 从数据字典中查询 集合
     * @param 
     * @return 
     **/
    List<TbSystemDict> getDictsByName(String name);
    /**
     * 根据名称和显示值查询
     * @param
     * @return
     **/
    String getDictByNameAndLabel(String name, String label);
    /**
     * 获取公司信息中所有的id和name
     * @param 
     * @return 
     **/
    List<Object[]> getAllCompanyOfIdAndName();
    /**
     * 获取所有有效的招聘公司的信息
     * @param
     * @return
     **/
    List<Integer> getNormalZhaoPinCompanyId();
    /**
     * 根据工作单价查询公司
     * @param
     * @return
     **/
    List<Integer> getNeedJobsByJobType(Double price);
    /**
     * 根据公司得到所有职位信息
     * @param
     * @return
     **/
    List<TbNeedJob> getAllJobByCompanyId(Integer id);
    /**
     * 根据公司和价格得到职位信息
     * @param 
     * @return 
     **/
    List<TbNeedJob> getAllJobByCompanyIdAndPrice(Integer companyId, Double price);
}
