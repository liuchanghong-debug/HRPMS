package com.hrpms.dao.talen_dao.zhaopin_dao;

import com.hrpms.pojo.TbNeedJob;

import java.util.List;
import java.util.Map;

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
    List<TbNeedJob> selectNeedJobByDuo(String hql, Map map);
    /**
     * 模糊分页查询总条数
     * @param 
     * @return 
     **/
    Long selectNeedJobCount(String hql, Map map);
    /**
     * 添加招聘信息
     * @param 
     * @return 
     **/
    void addNeedJob(TbNeedJob tbNeedJob);
    /**
     * 根据id查看详细招聘信息
     * @param 
     * @return 
     **/
    TbNeedJob selectNeedJobById(int id);
}
