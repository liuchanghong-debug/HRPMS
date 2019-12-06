package com.hrpms.controller.business_menu_controller.report_statistics_controller;

import com.hrpms.service.business_menu_service.report_statistics_service.CaiWuCountService;
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
public class CaiWuCountController {

    @Autowired
    private CaiWuCountService caiWuCountService;

    @RequestMapping("/selectCaiWuCountByDuo")
    public String selectCaiWuCountByDuo(String startTime, String endTime,Model model){
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<Object[]> list = caiWuCountService.selectCaiWuCountByDuo(map);
        model.addAttribute("list",list);
        return "business-menu/report-statistics/caiWuCount";
    }

    @RequestMapping("/CaiWuCountDownload")
    public void CaiWuCountDownload(String startTime, String endTime, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        caiWuCountService.CaiWuCountDownload(map,request,response);

    }
}
