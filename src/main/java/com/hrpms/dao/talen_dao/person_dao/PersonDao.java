package com.hrpms.dao.talen_dao.person_dao;

import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.operaton_select.TbPersonOperation;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.talen_dao.person_dao > PersonDao
 * @description TODO
 * @create 2019/11/25  17:47
 * @versiion 1.0
 * @Description:
 */
public interface PersonDao {
    /**
     * 多条件分页查询
     * @param 
     * @return 
     **/
    List<TbPerson> personList(String hql, TbPersonOperation personOperation);
    /**
     * 多条件查询总条数
     * @param 
     * @return 
     **/
    Long personCount(String hql, TbPersonOperation personOperation);

    /**
     * 添加人才信息
     * @param 
     * @return 
     **/
    void personAdd(TbPerson person);
}
