package com.hrpms.controller.business_menu_controller.talent_controller.zhaopin_controller;

import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbNeedJobOperation;
import com.hrpms.service.business_menu_service.talen_service.zhaopin_service.ZhaoPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.controller.talent_controller.zhaopin > TalentZhaoPonController
 * @description TODO
 * @create 2019/11/25  17:42
 * @versiion 1.0
 * @Description:
 */
@Controller
@RequestMapping("/talent")
public class TalentZhaoPinController {
    @Autowired
    private ZhaoPinService zhaoPinService;

    /**
     * 分页查询招聘信息
     * @param 
     * @return 
     **/
    @RequestMapping("/zhaopinList")
    public String zhaopinList(@RequestParam(defaultValue = "1") Integer currentPage, TbNeedJobOperation needJobOperation, Model model){
        model.addAttribute("industrys", zhaoPinService.getDictsByName("招聘行业"));
        model.addAttribute("payTypes", zhaoPinService.getDictsByName("招聘结算方式"));
        model.addAttribute("infoTypes", zhaoPinService.getDictsByName("招聘信息类型"));
        model.addAttribute("statuss", zhaoPinService.getDictsByName("招聘信息状态"));
        model.addAttribute("companys", zhaoPinService.getAllCompanyOfIdAndName());

        model.addAttribute("page", zhaoPinService.zhaopinList(currentPage, needJobOperation));
        model.addAttribute("needJobOperation", needJobOperation);

        return "business-menu/talent-service/zhaoPinList";
    }

    /**
     * 招聘信息添加页面
     * @param
     * @return 添加页面
     **/
    @RequestMapping("/zhaopinToAdd")
    public String addNeedJobJsp(Model model){
        model.addAttribute("industrys", zhaoPinService.getDictsByName("招聘行业"));
        model.addAttribute("payTypes", zhaoPinService.getDictsByName("招聘结算方式"));
        model.addAttribute("infoTypes", zhaoPinService.getDictsByName("招聘信息类型"));
        model.addAttribute("statuss", zhaoPinService.getDictsByName("招聘信息状态"));
        model.addAttribute("companys", zhaoPinService.getAllCompanyOfIdAndName());

        return "business-menu/talent-service/zhaoPinAdd";
    }
    /**
     * 招聘信息保存
     * @param 
     * @return 招聘列表
     **/
    @RequestMapping("/zhaopinAdd")
    public String zhaopinAdd(TbNeedJob tbNeedJob, HttpSession session, Model model){
        TbSystemUser systemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        zhaoPinService.zhaopinAdd(tbNeedJob, systemUser.getId());

        return zhaopinList(1, new TbNeedJobOperation(), model);
    }

    /**
     * 根据id查看招聘信息详情
     * @param 
     * @return 详情页面
     **/
    @RequestMapping("/zhaopinDetailById")
    public String selectNeedJobById(int id, Model model){
        model.addAttribute("industrys", zhaoPinService.getDictsByName("招聘行业"));
        model.addAttribute("payTypes", zhaoPinService.getDictsByName("招聘结算方式"));
        model.addAttribute("infoTypes", zhaoPinService.getDictsByName("招聘信息类型"));
        model.addAttribute("statuss", zhaoPinService.getDictsByName("招聘信息状态"));
        model.addAttribute("companys", zhaoPinService.getAllCompanyOfIdAndName());
        model.addAttribute("needJob", zhaoPinService.selectNeedJobById(id));

        return "business-menu/talent-service/zhaoPinSelect";
    }
    
    /**
     * 修改页面
     * @param 
     * @return 
     **/
    @RequestMapping("/zhaopinToUpdate")
    public String zhaopinToUpdate(Integer id, Integer currentPage, TbNeedJobOperation needJobOperation, Model model){
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("needJobOperation", needJobOperation);
        model.addAttribute("industrys", zhaoPinService.getDictsByName("招聘行业"));
        model.addAttribute("payTypes", zhaoPinService.getDictsByName("招聘结算方式"));
        model.addAttribute("infoTypes", zhaoPinService.getDictsByName("招聘信息类型"));
        model.addAttribute("statuss", zhaoPinService.getDictsByName("招聘信息状态"));
        model.addAttribute("companys", zhaoPinService.getAllCompanyOfIdAndName());
        model.addAttribute("needJob", zhaoPinService.selectNeedJobById(id));

        return "business-menu/talent-service/zhaoPinUpdate";
    }
    /**
     * 根据id修改招聘信息
     * @param 
     * @return 
     **/
    @RequestMapping("/zhaopinUpdate")
    public String updateNeedJob(TbNeedJob tbNeedJob, HttpSession session, Integer currentPage, TbNeedJobOperation needJobOperation, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        zhaoPinService.zhaopinUpdate(tbNeedJob, tbSystemUser.getId());

        return zhaopinList(currentPage, needJobOperation, model);
    }

    /**
     * id删除招聘信息(实则修改招聘状态)
     * @param 
     * @return 
     **/
    @RequestMapping("/zhaopinDelete")
    public String zhaopinDelete(Integer id, HttpSession session, Integer currentPage, TbNeedJobOperation needJobOperation, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        zhaoPinService.zhaopinDelete(id, tbSystemUser.getId());
        return zhaopinList(currentPage, needJobOperation, model);
    }
}
