package com.hrpms.dao.customer_client_dao;

import com.hrpms.pojo.TbCustomer;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.customer_client_dao > CustomerDao
 * @description TODO
 * @create 2019/11/22  16:56
 * @versiion 1.0
 * @Description:
 */
public interface CustomerDao {
    //查询所有客户名称
    public List<TbCustomer> selectAllCustomerName();
}
