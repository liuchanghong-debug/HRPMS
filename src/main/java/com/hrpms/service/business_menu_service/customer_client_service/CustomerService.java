package com.hrpms.service.business_menu_service.customer_client_service;

import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbCustomerOperation;
import com.hrpms.utils.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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
     * 条件分页查询数据
     * @param
     * @return
     **/
    Page<TbCustomer> customerAllByOperationAndPaging(Integer currentPage, TbCustomerOperation customerOperation);

    /**
     * 通过id查询详细信息
     * @param 
     * @return 
     **/
    TbCustomer customerById(Integer id);
    /**
     * 修改个人客户信息
     * @param 
     * @return 
     **/
    void customerUpdate(TbCustomer customer, Integer updateBy) throws IOException;

    /**
     * 根据字典名称查询数据
     * @param
     * @return
     **/
    List<TbSystemDict> getDataDictByName(String name);
    /**
     * 获取所有的公司id 和 name
     * @param 
     * @return 
     **/
    List<TbCompany> getAllCompanyOfIdAndName();
    /**
     * 添加个人客户
     * @param
     * @return
     **/
    void customerToAdd(TbCustomer customer);
    /**
     * 删除  改变删除标志的值
     * @param
     * @return
     **/
    void customerDelete(Integer id, Integer updateBy);
    /**
     * 模板下载
     * @param
     * @return
     **/
    void customerTemplateDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * 导出数据
     * @param 
     * @return 
     **/
    void customerDataOutOfExcel(TbCustomerOperation customerOperation, HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * 导入数据
     * @param
     * @return
     **/
    void customerUploadOfExcel(InputStream inputStream, Integer createBy) throws IOException, InvalidFormatException, ParseException;
    
    
    
    /**
     * 不在集合中的数据对象
     * @param 
     * @return 
     **/
    List<Object[]> getDataOfNotInList(List list);
    /**
     * 根据身份证得到用户的详细信息
     * @param
     * @return
     **/
    TbCustomer getCustomerByIdCard(String idCard);

    //查询所有的客户名称
    public List<TbCustomer> selectAllCustomerName(String name);

    /**
     * 异步验证idCard是否唯一
     * @param
     * @return
     **/
    boolean customerIdCardIsOnly(String idCard);

    /**
     * 异步验证idCard是否唯一  修改 不包括自己
     * @param
     * @return
     **/
    boolean customerIdCardIsOnlyUpdate(Integer id, String idCard);

    //根据邮箱查询所有
    public TbCustomer selectCustomerByEmail(String email);

    //根据电话号码查询所有
    public TbCustomer selectCustomerByPhone(String phone);
    
    
    
    /**
     * 根据状态 和 idCard查询  人才添加用
     * @param 
     * @return 
     **/
    List<TbCustomer> normalCustomerOfStatus(String hql, List<String> idCardList, List<String> statusList);

}
