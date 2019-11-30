package com.hrpms.dao.talen_dao.zhaopin_dao;

import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.operaton_select.TbNeedJobOperation;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.talen_dao.zhaopin_dao > ZhaoPinDao
 * @description TODO
 * @create 2019/11/25  17:48
 * @versiion 1.0
 * @Description:
 */
public interface ZhaoPinDao {
    /**
     * 模糊分页查询
     * @param 
     * @return 
     **/
    List<TbNeedJob> zhaopinList(String hql, TbNeedJobOperation needJobOperation);
    /**
     * 模糊分页查询总条数
     * @param 
     * @return 
     **/
    Long zhaopinCount(String hql, TbNeedJobOperation needJobOperation);
    /**
     * 添加招聘信息
     * @param 
     * @return 
     **/
    void zhaopinAdd(TbNeedJob tbNeedJob);
    /**
     * 根据id查看详细招聘信息
     * @param 
     * @return 
     **/
    TbNeedJob selectNeedJobById(int id);
    /**
     * 根据id修改招聘信息  删除共用
     * @param
     * @return
     **/
    void zhaopinUpdate(TbNeedJob tbNeedJob);
    /**
     * 获取所有有效的招聘公司的信息
     * @param
     * @return
     **/
    List<Integer> getNormalZhaoPinCompanyId(List normalStatus);
    /**
     * 根据工作单价查询公司
     * @param
     * @return
     **/
    List<Integer> getNeedJobsByJobType(Double maxPrice, Double minPrice, List mornalStatus);
    /**
     * 根据公司得到所有职位信息
     * @param
     * @return
     **/
    List<TbNeedJob> getAllJobByCompanyId(Integer id, String normalType);
}
