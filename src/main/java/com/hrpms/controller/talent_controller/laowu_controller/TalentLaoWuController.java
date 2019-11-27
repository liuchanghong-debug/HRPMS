package com.hrpms.controller.talent_controller.laowu_controller;

import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbPersonJob;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbPersonJobOperation;
import com.hrpms.service.talen_service.laowu_service.LaoWuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

    /**
     * 分页查询
     * @param 
     * @return 
     **/
    @RequestMapping("/laowuList")
    public String laowuList(@RequestParam(defaultValue = "1") Integer currentPage, TbPersonJobOperation personJobOperation, Model model){
        model.addAttribute("jobTypes", laoWuService.getDictsByName("工作类型"));
        model.addAttribute("personJobOperation", personJobOperation);
        model.addAttribute("companys", laoWuService.getCompanysByZhaoPin());
        model.addAttribute("page", laoWuService.getPersonJobByOperation(currentPage, personJobOperation));

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
        model.addAttribute("statuss", laoWuService.getDictsByName("合作状态"));

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
     * getPersonByCompanyIdForPrice通过公司得到期望薪资差不多的求职者信息和公司职位信息
     * @param
     * @return
     **/
    @RequestMapping("/getPersonByCompanyIdForPrice")
    @ResponseBody
    public Object getPersonByCompanyIdForPrice(Integer id){
        return laoWuService.getPersonByCompanyIdForPrice(id);
    }
    /**
     * 添加数据库
     * @param 
     * @return 
     **/
    @RequestMapping("/laowuAdd")
    public String laowuAdd(MultipartFile contractFile, TbPersonJob personJob, HttpSession session, HttpServletRequest request, Model model) throws IOException {
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        laoWuService.personJobAdd(contractFile, personJob, tbSystemUser.getId(), request);

        return laowuList(1, new TbPersonJobOperation(), model);
    }
    /**
     * 查看详细信息
     * @param 
     * @return 
     **/
    @RequestMapping("/laowuDetailById")
    public String laowuDetailById(Integer id, Model model){
        model.addAttribute("personJob", laoWuService.laowuDetailById(id));
        model.addAttribute("companyIdAndNames", laoWuService.getAllCompanys());
        model.addAttribute("jobTypes", laoWuService.getDictsByName("工作类型"));
        model.addAttribute("statuss", laoWuService.getDictsByName("合作状态"));

        return "business-menu/talent-service/laoWuUpdateDisable";
    }
    /**
     * 合同预览
     * @param
     * @return
     **/
    @RequestMapping("/contractUrlPreview")
    public void contractUrlPreview(String personResumeUrl, HttpServletRequest request, HttpServletResponse response) throws Exception {
        laoWuService.contractUrlPreview(personResumeUrl, request, response);
    }
    /**
     * 修改页面
     * @param
     * @return
     **/
    @RequestMapping("/laowuToUpdate")
    public String laowuToUpdate(Integer id, Integer currentPage, TbPersonJobOperation personJobOperation, Model model){
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("personJobOperation", personJobOperation);
        model.addAttribute("personJob", laoWuService.laowuDetailById(id));
        model.addAttribute("companys", laoWuService.getCompanysByZhaoPin());
        model.addAttribute("jobTypes", laoWuService.getDictsByName("工作类型"));
        model.addAttribute("statuss", laoWuService.getDictsByName("合作状态"));

        return "business-menu/talent-service/laoWuUpdate";
    }
    /**
     * 接收修改后的信息
     * @param
     * @return
     **/
    @RequestMapping("/laowuUpdate")
    public String laowuUpdate(HttpSession session, MultipartFile contractFile, TbPersonJob personJob, Integer currentPage, TbPersonJobOperation personJobOperation, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        laoWuService.personJobUpdate(contractFile, personJob, request, response, tbSystemUser.getId());
        return laowuList(currentPage, personJobOperation, model);
    }

    /**
     * 删除
     * @param
     * @return
     **/
    @RequestMapping("/laowuDelete")
    public String laowuDelete(Integer currentPage, TbPersonJobOperation personJobOperation, Model model, Integer id, HttpSession session){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        laoWuService.personJobDelete(id, tbSystemUser.getId());
        return laowuList(currentPage, personJobOperation, model);
    }
}
