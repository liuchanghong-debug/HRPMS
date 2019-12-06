package com.hrpms.service.business_menu_service.talen_service.laowu_service;

import com.hrpms.pojo.*;
import com.hrpms.pojo.operaton_select.TbPersonJobOperation;
import com.hrpms.utils.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.talen_service.laowu > LaoWuService
 * @description TODO
 * @create 2019/11/25  17:38
 * @versiion 1.0
 * @Description:
 */
public interface LaoWuService {
    /**
     * 获取分页查询
     * @param
     * @return
     **/
    Page<TbPersonJob> getPersonJobByOperation(Integer currentPage, TbPersonJobOperation personJobOperation);
    /**
     * 根据id查看详细信息
     * @param
     * @return
     **/
    TbPersonJob laowuDetailById(Integer id);
    /**
     * 合同预览
     * @param 
     * @return 
     **/
    void contractUrlPreview(String personResumeUrl, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 修改
     * @param
     * @return
     **/
    void personJobUpdate(MultipartFile file, TbPersonJob personJob, HttpServletRequest request, HttpServletResponse response, Integer updateBy) throws IOException;
    /**
     * 根据name获取字典值
     * @param 
     * @return 
     **/
    List<TbSystemDict> getDictsByName(String name);

    /**
     * 获取所有正常的客户名称和id
     * @param
     * @return
     **/
    List<Object[]> getAllPersonIdAndName();
    /**
     * 根据招聘表中的公司id查询公司信息
     * @param 
     * @return 
     **/
    List<TbCompany> getCompanysByZhaoPin();
    /**
     * 根据客户id 查询idCard
     * @param
     * @return
     **/
    Map getPersonAndCompanyById(Integer id);
    /**
     * 获取所有招聘信息公司id
     * @param
     * @return 
     **/
    Set<Integer> getAllCompanyIdAndName();
    /**
     * 获取所有公司信息
     * @param
     * @return
     **/
    List<Object[]> getAllCompanys();
    /**
     * 获取所有招聘信息
     * @param
     * @return
     **/
    List<TbNeedJob> getAllNeedJobs();
    /**
     * 根据价格查询招聘公司id
     * @param
     * @return
     **/
    List<TbCompany> getNeedJobsByJobType(Double price);
    /**
     * 根据公司得到所有职位信息
     * @param
     * @return
     **/
    List<TbNeedJob> getAllJobByCompanyId(Integer id, Integer personId);
    /**
     * 通过招聘价格范围查询persons
     * @param
     * @return
     **/
    List<TbPerson> getPersonsByNeedJobPrice(Double price);

    /**
     *getPersonByCompanyIdForPrice通过公司得到期望薪资差不多的求职者信息和公司职位信息
     * @param
     * @return
     **/
    Map getPersonByCompanyIdForPrice(Integer companyId);
    /**
     * 添加派遣信息
     * @param
     * @return
     **/
    void personJobAdd(MultipartFile file, TbPersonJob personJob, Integer createBy, HttpServletRequest request) throws IOException;
    /**
     * 删除
     * @param
     * @return
     **/
    void personJobDelete(Integer id, Integer updateBy);
    /**
     * 根据id查询Person详细信息
     * @param
     * @return
     **/
    TbPerson getDetailPersonById(Integer id);
    /**
     * 通过公司id和个人信息中的需求工作得到其公司下的招聘信息
     * @param
     * @return
     **/
    List<TbNeedJob> getNeedJobByCompanyIdAndPersonPrice(Integer companyId, Double price);

    /**
     * 根据needJob的id查询needJob的详细信息
     * @param
     * @return
     **/
    TbNeedJob getDetailNeedJobById(Integer id);
    /**
     * 根据客户id查询公司下的有招聘信息
     * @param
     * @return 
     **/
    List<TbNeedJob> getNeedJobsByPersonJobId(Integer id);
    /**
     * 根据companyId, price 得到公司下差不多的价格信息
     * @param
     * @return
     **/
    List<TbNeedJob> getNeedJobByCompanyIdAndPrice(Integer companyId, Double price);
    /**
     * 通过idCard得到
     * @param
     * @return 
     **/
    TbPersonJob personJobByIdCard(String idCard);
    
    /**
     * 劳务报表
     * @param 
     * @return 
     **/

}
