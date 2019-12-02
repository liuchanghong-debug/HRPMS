package com.hrpms.dao.talen_dao.laowu_dao;

import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbPersonJob;
import com.hrpms.pojo.operaton_select.TbPersonJobOperation;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.talen_dao.laowu_dao > LaoWuDao
 * @description TODO
 * @create 2019/11/25  17:46
 * @versiion 1.0
 * @Description:
 */
public interface LaoWuDao {
    /**
     * 添加
     * @param
     * @return
     **/
    void personJobAdd(TbPersonJob personJob);
    /**
     * 多条件分页查询
     * @param
     * @return
     **/
    List<TbPersonJob> personList(String hql, TbPersonJobOperation personJobOperation);
    /**
     * 多条件查询总条数
     * @param
     * @return
     **/
    Long personJobCount(String hql, TbPersonJobOperation personJonOperation);
    /**
     * 修改删除
     * @param
     * @return
     **/
    void personOperation(TbPersonJob personJob);
    /**
     * 根据id查询
     * @param
     * @return
     **/
    TbPersonJob getPersonJobById(Integer id);
    /**
     * 获取所有
     * @param
     * @return
     **/
    List<TbNeedJob> getAllNeedJobs(String hql);
    /**
     * 通过idCard得到
     * @param
     * @return
     **/
    TbPersonJob personJobByIdCard(String idCard);
}
