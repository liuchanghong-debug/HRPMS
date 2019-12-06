package com.hrpms.controller.business_menu_controller.report_statistics_controller;

import com.hrpms.service.business_menu_service.report_statistics_service.SalaryCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report-statistics")
public class SalaryCountController {

    @Autowired
    private SalaryCountService salaryCountService;

    //动态模糊查询工资报表
    @RequestMapping("/selectSalaryCountByDuo")
    public String selectSalaryCountByDuo(String name, String idCard, String payCard, Integer companyId,
                                         Model model){
        Map map = new HashMap();
        map.put("name",name);
        map.put("idCard",idCard);
        map.put("payCard",payCard);
        map.put("companyId",companyId);
        List<Object[]> list = salaryCountService.selectSalaryCountByDuo(map);
        map.put("name",name);
        map.put("idCard",idCard);
        map.put("payCard",payCard);
        map.put("companyId",companyId);
        model.addAttribute("map",map);
        model.addAttribute("list",list);
        return "business-menu/report-statistics/salaryCount";
    }

    //工资报表导出
    @RequestMapping("/SalaryCountDownload")
    public void SalaryCountDownload(String name, String idCard, String payCard, Integer companyId,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map map = new HashMap();
        map.put("name",name);
        map.put("idCard",idCard);
        map.put("payCard",payCard);
        map.put("companyId",companyId);
        salaryCountService.salaryCountDownload(map,request,response);
    }
}