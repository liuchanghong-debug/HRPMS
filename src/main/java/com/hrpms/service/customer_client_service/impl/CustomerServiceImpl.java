package com.hrpms.service.customer_client_service.impl;

import com.hrpms.dao.customer_client_dao.CustomerDao;
import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.service.customer_client_service.CustomerService;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.customer_service.impl > CustomerServiceImpl
 * @description TODO
 * @create 2019/11/22  16:55
 * @versiion 1.0
 * @Description:
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<TbSystemDict> getDataDictByName(String name) {
        return dataDictService.getDataDictByName(name);
    }

    @Override
    public List<TbCustomer> selectAllCustomerName() {
        return customerDao.selectAllCustomerName();
    }
}
