package com.hrpms.service.report_statistics_service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface SalaryCountService {
    //动态模糊查询工资费用
    public List<Object[]> selectSalaryCountByDuo(Map map);

    //工资报表导出
    public void salaryCountDownload(Map map,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
