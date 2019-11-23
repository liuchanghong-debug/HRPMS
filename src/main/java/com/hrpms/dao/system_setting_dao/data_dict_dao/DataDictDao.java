package com.hrpms.dao.system_setting_dao.data_dict_dao;

import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbSystemDictOperation;

import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.system_setting_dao.data_dict_dao > DataDictDao
 * @description TODO
 * @create 2019/11/21  15:00
 * @versiion 1.0
 * @Description:
 */
public interface DataDictDao {
    /**
     * 添加数据字典
     * @param
     * @return
     **/
    void dataDictAdd(TbSystemDict tbSystemDict);
    /**
     * 根据数据字典中的name 来得到字典集合
     * @param 
     * @return 
     **/
    List<TbSystemDict> getDataDictByName(String name);
    /**
     * 多条件查询
     * @param 
     * @return 
     **/
    List<TbSystemDict> getDataDIctByOperation(String hql, TbSystemDictOperation tbSystemDictOperation);
    /**
     * 多条件查询总条数
     * @param 
     * @return 
     **/
    Long getCountDataDictByOperation(String hql, TbSystemDictOperation tbSystemDictOperation);
    /**
     * 根据id查询
     * @param
     * @return
     **/
    TbSystemDict getDataDictById(Integer id);
    /**
     * 根据对象修改
     * @param
     * @return
     **/
    void dataDictUpdate(TbSystemDict tbSystemDict);
    /**
     * 删除
     * @param 
     * @return 
     **/
    void dataDictDelete(TbSystemDict tbSystemDict);
    /**
     * 根据名称， 显示值查询存储值
     * @param
     * @return
     **/
    String getDataDictValueByNameAndLabel(Map map);

    //根据字典名查询状态
    public List<TbSystemDict> selectByName(String name);
}
