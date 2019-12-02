package com.hrpms.controller.report_statistics_controller;

import com.hrpms.pojo.operaton_select.SheBaoCountOperation;
import com.hrpms.service.shebao_manager_service.SheBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.controller.report_statistics_controller > SheBaoCountController
 * @description TODO
 * @create 2019/11/30  11:26
 * @versiion 1.0
 * @Description:
 */
@Controller
@RequestMapping("/statement")
public class SheBaoCountController {
    @Autowired
    private SheBaoService sheBaoService;
    /**
     * 社保统计
     * @param
     * @return
     **/
    @RequestMapping("/shebaoCount")
    public String shebaoCount(SheBaoCountOperation sheBaoCountOperation, Model model){
        //公司信息
        model.addAttribute("companys", sheBaoService.getCompanys());
        model.addAttribute("shebaoCountOperation", sheBaoCountOperation);
        model.addAttribute("data", sheBaoService.getSheBaoCount(sheBaoCountOperation));

        return "business-menu/report-statistics/sheBaoCount";
    }
    /**
     * 社保统计导出
     * @param
     * @return
     **/
    @RequestMapping("/shebaoCountOut")
    public void shebaoCount(SheBaoCountOperation sheBaoCountOperation, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //公司信息
        sheBaoService.getSheBaoCountOut(sheBaoCountOperation, request, response);
    }
}
