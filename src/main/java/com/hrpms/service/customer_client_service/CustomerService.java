package com.hrpms.service.customer_client_service;

import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSystemDict;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.customer_service > CustomerService
 * @description TODO
 * @create 2019/11/22  16:54
 * @versiion 1.0
 * @Description:
 */
public interface CustomerService {
    /**
     * 根据字典名称查询数据
     * @param
     * @return
     **/
    List<TbSystemDict> getDataDictByName(String name);


    //查询所有客户名称
    public List<TbCustomer> selectAllCustomerName();
}
