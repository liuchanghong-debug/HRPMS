package com.hrpms.service.business_menu_service.salary_manager_service;

import com.hrpms.pojo.TbSalary;
import com.hrpms.utils.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface TbSalaryService {

    //动态模糊分页查询招聘信息
    public Page<TbSalary> selectSalaryByDuo(Integer currentPage, Map map);


    //添加工资信息
    public void addSalary(TbSalary tbSalary);

    //根据id查看工资信息
    public TbSalary selectSalaryById(int id);

    //根据id修改工资信息
    public void updateSalaryById(TbSalary tbSalary);

    //根据id删除工资信息
    public void deleteSalaryById(int id);

    //工资模板下载
    public void salaryModleDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception;

    //客户工资数据导入
    public void salaryUpload(InputStream fin,Integer createBy)throws IOException,InvalidFormatException;

    //客户工资数据导出
    public void salaryDownload(Map map,HttpServletResponse response,HttpServletRequest request) throws Exception;
    
    /**
     * 根据idCard查询工资信息
     * @param 
     * @return 
     **/
    Double getSalaryByIdCard(String idCard);

    //查询所有工资信息
    public List<TbSalary> selectAllGongjijin();

    /**
     * 根据idcard 查询所有
     * @param
     * @return
     **/
    TbSalary getTbSalaryByIdCard(String idCard);

    //银行卡号唯一验证
    public TbSalary payCardIsOne(String payCard);

    //根据name查询所有
    public TbSalary selectSalaryByName(String name);
}
