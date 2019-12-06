package com.hrpms.controller.business_menu_controller.report_statistics_controller;

import com.hrpms.service.business_menu_service.report_statistics_service.GongJiJinCountService;
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
public class GongJiJinCountController {

    @Autowired
    private GongJiJinCountService gongJiJinCountService;

    //动态模糊查询公积金报表
    @RequestMapping("/selectGongJiJinCountByDuo")
    public String selectSalaryCountByDuo(String name, String idCard, String accountNo, Integer companyId,
                                         Model model){
        Map map = new HashMap();
        map.put("name",name);
        map.put("idCard",idCard);
        map.put("accountNo",accountNo);
        map.put("companyId",companyId);
        List<Object[]> list = gongJiJinCountService.selectGongJiJInCountByDuo(map);
        map.put("name",name);
        map.put("idCard",idCard);
        map.put("accountNo",accountNo);
        map.put("companyId",companyId);
        model.addAttribute("list",list);
        model.addAttribute("map",map);
        return "business-menu/report-statistics/gongJiJinCount";
    }

    //公积金报表导出
    @RequestMapping("/gongJiJInCountDownload")
    public void gongJiJInCountDownload(String name, String idCard, String accountNo, Integer companyId,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map map = new HashMap();
        map.put("name",name);
        map.put("idCard",idCard);
        map.put("accountNo",accountNo);
        map.put("companyId",companyId);
        gongJiJinCountService.gongJiJInCountDownload(map,request,response);

    }
}
