package com.hrpms.service.company_client_service;

import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbCompanyOperation;
import com.hrpms.utils.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.company_client_service > CompanyClientService
 * @description TODO
 * @create 2019/11/21  19:55
 * @versiion 1.0
 * @Description:
 */
public interface CompanyClientService {
    /**
     * 多条件分页查询公司客户信息
     * @param 
     * @return 
     **/
    Page<TbCompany> getCompanyByOperation(Integer currentPage, TbCompanyOperation companyOperation);
    /**
     * 根据id查询
     * @param 
     * @return 
     **/
    TbCompany getCompanyById(Integer id);

    /**
     * 根据  字典名称获取字典数据集合
     * @param 
     * @return 
     **/
    List<TbSystemDict> getDataDictByName(String name);
    
    /**
     * 添加公司客户
     * @param 
     * @return 
     **/
    void companyToAdd(TbCompany tbCompany);
    /**
     * 修改公司客户
     * @param 
     * @return 
     **/
    void companyToUpdate(TbCompany company);
    /**
     * 删除公司客户  更改状态
     * @param 
     * @return 
     **/
    void companyDelete(Integer id, Integer updateById);
    /**
     * 模板下载
     * @param 
     * @return 
     **/
    void templateDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * 数据条件导出
     * @param 
     * @return 
     **/
    void getCompanyByOperationNoPaging(TbCompanyOperation companyOperation, HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * 上传到数据库
     * @param 
     * @return 
     **/
    void fileUpload(InputStream file, Integer createBy) throws IOException, InvalidFormatException;
}
