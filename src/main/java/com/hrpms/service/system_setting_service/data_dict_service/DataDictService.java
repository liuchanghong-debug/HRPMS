package com.hrpms.service.system_setting_service.data_dict_service;

import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbSystemDictOperation;
import com.hrpms.utils.Page;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.system_setting_service.data_dict_service > DataDictService
 * @description TODO
 * @create 2019/11/21  15:05
 * @versiion 1.0
 * @Description:
 */
public interface DataDictService {
    /**
     * 添加数据字典
     * @param 
     * @return 
     **/
    void dataDictAdd(TbSystemDict tbSystemDict, TbSystemUser tbSystemUser);
    /**
     * 根据数据字典中的name 字典数据
     * @param 
     * @return 
     **/
    List<TbSystemDict> getDataDictByName(String name);
    /**
     * 创建多条件查询语句
     * @param 
     * @return 
     **/
    Page<TbSystemDict> getDataDictByOperation(Integer currentPage, TbSystemDictOperation tbSystemDictOperation);
    /**
     * 根据id 查询
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
    void dataDictDelete(Integer id);
    /**
     * 根据name 和 label查询value
     * @param 
     * @return 
     **/
    String getDataDictValueByNameAndLabel(String name, String label);

    //根据字典名查询状态
    public List<TbSystemDict> selectByName(String name);
}
