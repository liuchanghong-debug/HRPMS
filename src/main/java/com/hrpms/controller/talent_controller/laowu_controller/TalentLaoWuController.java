package com.hrpms.controller.talent_controller.laowu_controller;

import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbPersonJob;
import com.hrpms.service.talen_service.laowu_service.LaoWuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.controller.talent_controller.laowu > TalentLaoWuController
 * @description TODO
 * @create 2019/11/25  17:41
 * @versiion 1.0
 * @Description:
 */
@Controller
@RequestMapping("/laowu")
public class TalentLaoWuController {
    @Autowired
    private LaoWuService laoWuService;

    @RequestMapping("/laowuList")
    public String laowuList(){
        return "business-menu/talent-service/laoWuList";
    }


    /**
     * 添加页面
     * @param
     * @return
     **/
    @RequestMapping("/laowuToAdd")
    public String laowuToAdd(Model model){
        model.addAttribute("persons", laoWuService.getAllPersonIdAndName());
        model.addAttribute("companyIds", laoWuService.getAllCompanyIdAndName());
        model.addAttribute("companyIdAndNames", laoWuService.getAllCompanys());
        model.addAttribute("jobTypes", laoWuService.getDictsByName("工作类型"));

        return "business-menu/talent-service/laoWuAdd";
    }
    /**
     * 根据person id查询个人和公司信息
     * @param
     * @return
     **/
    @RequestMapping("/getPersonAndCompanyById")
    @ResponseBody
    public Object getPersonAndCompanyById(Integer id){
        return laoWuService.getPersonAndCompanyById(id);
    }
    /**
     * 根据公司id得到其所有的职位信息
     * @param 
     * @return 
     **/
    @RequestMapping("/getAllJobByCompanyId")
    @ResponseBody
    public Object getAllJobByCompanyId(Integer id){
        return laoWuService.getAllJobByCompanyId(id);
    }
    /**
     * 根据需求id得到详细信息
     * @param
     * @return
     **/
    @RequestMapping("/getNeedJobById")
    @ResponseBody
    public TbNeedJob getNeedJobById(Integer id){
        return laoWuService.getDetailById(id);
    }
    /**
     * 添加数据库
     * @param 
     * @return 
     **/
    @RequestMapping("/laowuAdd")
    public String laowuAdd(MultipartFile contractFile, TbPersonJob personJob){

        return laowuList();
    }
}
