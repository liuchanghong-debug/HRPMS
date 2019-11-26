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
     * 根据id修改招聘信息
     * @param
     * @return
     **/
    void updateNeedJob(TbNeedJob tbNeedJob);
    /**
     * 根据id删除招聘信息  置状态
     * @param 
     * @return 
     **/
    void deleteNeedJob(int id);
}
