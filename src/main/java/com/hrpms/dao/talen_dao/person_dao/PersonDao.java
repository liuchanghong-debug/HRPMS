package com.hrpms.dao.talen_dao.person_dao;

import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.operaton_select.TbPersonOperation;

import java.util.List;
import java.util.Map;

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
    /**
     * 根据id查询
     * @param
     * @return
     **/
    TbPerson personDetailById(Integer id);
    /**
     * 修改
     * @param
     * @return
     **/
    void personUpdate(TbPerson person);
    /**
     * 获取所有客户名称和id
     * @param
     * @return
     **/
    List<Object[]> getAllIdAndName(List normalStatus);
    /**
     * 通过工资范围得到求职信息
     * @param
     * @return
     **/
    List<TbPerson> getPersonsByPrice(String hql, Map map);

    /**
     * 异步验证idCard是否唯一
     * @param
     * @return
     **/
    Object personIdCardIsOnly(String hql, String idCard);

    /**
     * 异步验证idCard是否唯一
     * @param
     * @return
     **/
    Object personIdCardIsOnlyUpdate(String hql, Integer id, String idCard);

    /**
     * 获取人才表中所有状态正常的idCard
     * @param
     * @return
     **/
    List<String> normalPersonOfIdCard(String hql, List<String> status);
}
