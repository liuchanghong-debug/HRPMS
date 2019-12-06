package com.hrpms.controller.business_menu_controller.talent_controller.laowu_controller;

import com.hrpms.pojo.TbNeedJob;
import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.TbPersonJob;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbPersonJobOperation;
import com.hrpms.service.business_menu_service.talen_service.laowu_service.LaoWuService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.controller.talent_controller.laowu > TalentLaoWuController
 * @description TODO
 * @create 2019/11/25  17:41
 * @versiion 1.0
 * @Description:
 */
@Controller
@RequestMapping("/talent")
public class TalentLaoWuController {
    @Autowired
    private LaoWuService laoWuService;

    /**
     * 分页多条件查询
     * @param 
     * @return 
     **/
    @RequestMapping("/laowuList")
    public String laowuList(@RequestParam(defaultValue = "1") Integer currentPage, TbPersonJobOperation personJobOperation, Model model){
        model.addAttribute("jobTypes", laoWuService.getDictsByName("工作类型"));
        model.addAttribute("personJobOperation", personJobOperation);

        model.addAttribute("companys", laoWuService.getAllCompanys());
        model.addAttribute("needJobs", laoWuService.getAllNeedJobs());
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
        //人才信息
        model.addAttribute("persons", laoWuService.getAllPersonIdAndName());
        //有哪些公司有招聘信息  一个id, 一个name
        model.addAttribute("companyIds", laoWuService.getAllCompanyIdAndName());
        model.addAttribute("companyIdAndNames", laoWuService.getAllCompanys());
        model.addAttribute("jobTypes", laoWuService.getDictsByName("工作类型"));
        model.addAttribute("statuss", laoWuService.getDictsByName("合作状态"));

        return "business-menu/talent-service/laoWuAdd";
    }
    /**
     * 根据person id查询个人和公司信息    劳务添加
     * @param
     * @return
     **/
    @RequestMapping("/getPersonAndCompanyById")
    @ResponseBody
    public Object getPersonAndCompanyById(Integer personId){
        return laoWuService.getPersonAndCompanyById(personId);
    }
    /**
     * 根据公司id得到其所有的职位信息    劳务添加
     * @param 
     * @return 
     **/
    @RequestMapping("/getAllJobByCompanyId")
    @ResponseBody
    public Object getAllJobByCompanyId(Integer companyId, Integer personId){
        return laoWuService.getAllJobByCompanyId(companyId, personId);
    }
    /**
     * 根据招聘id得到合适的person  和 need job 详细信息
     * @param
     * @return
     **/
    @RequestMapping("/getPersonsAndNeedJobByNeedJobId")
    @ResponseBody
    public Object getPersonsAndNeedJobByNeedJobId(Integer needJobId){
        TbNeedJob needJob = laoWuService.getDetailNeedJobById(needJobId);
        List<TbPerson> personList = laoWuService.getPersonsByNeedJobPrice(needJob.getPrice());

        Map map = new HashMap(2);
        map.put("tbNeedJob", needJob);
        map.put("persons", personList);
        return map;
    }
    /**
     * 根据personId得到Person详细信息
     * @param
     * @return
     **/
    @RequestMapping("/getPersonDetatilById")
    @ResponseBody
    public Object getPersonDetatilById(Integer personId){
        return laoWuService.getDetailPersonById(personId);
    }

    /**
     * 根据personId得到详细信息， 同时根据personPrice, companyId得到其公司下的匹配的招聘信息
     * @param
     * @return
     **/
    @RequestMapping("/getPersonAndNeedJobsById")
    @ResponseBody
    public Object getPersonAndNeedJobsById(Integer personId, Integer companyId){
        Map map = new HashMap(2);
        TbPerson person = laoWuService.getDetailPersonById(personId);
        map.put("person", person);
        map.put("needJobs", laoWuService.getNeedJobByCompanyIdAndPrice(companyId, person.getForPrice()));
        return map;
    }


    /**
     * 根据需求id得到详细信息
     * @param
     * @return
     **/
    @RequestMapping("/getNeedJobDetailById")
    @ResponseBody
    public TbNeedJob getNeedJobById(Integer needJobId){
        return laoWuService.getDetailNeedJobById(needJobId);
    }

    /**
     * getPersonByCompanyIdForPrice通过公司得到期望薪资差不多的求职者信息和公司职位信息
     * @param
     * @return
     **/
    @RequestMapping("/getPersonsAndCompanyByCompanyId")
    @ResponseBody
    public Object getPersonByCompanyIdForPrice(Integer companyId){
        return laoWuService.getPersonByCompanyIdForPrice(companyId);
    }
    /**
     * 通过公司id和个人信息中的工资得到其公司下的招聘信息
     * @param 
     * @return 
     **/
    @RequestMapping("/getNeedJobByCompanyIdAndPersonPrice")
    @ResponseBody
    public Object getNeedJobByCompanyIdAndPersonPrice(Integer companyId, Double price){
        return laoWuService.getNeedJobByCompanyIdAndPersonPrice(companyId, price);
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
        TbPersonJob tbPersonJob = laoWuService.laowuDetailById(id);
        model.addAttribute("personJob", tbPersonJob);
        model.addAttribute("needJob", laoWuService.getDetailNeedJobById(tbPersonJob.getCompanyId()));
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
        //劳务信息
        TbPersonJob tbPersonJob = laoWuService.laowuDetailById(id);
        model.addAttribute("personJob", tbPersonJob);
        //通过价格查询所有合适的招聘公司信息
        model.addAttribute("companys", laoWuService.getNeedJobsByJobType(tbPersonJob.getPersonPrice()));
        model.addAttribute("needJobs", laoWuService.getAllNeedJobs());
        //将公司所有的劳务信息传过去  默认选中
        model.addAttribute("companyJobs", laoWuService.getNeedJobsByPersonJobId(id));

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
    /**
     * 根据id返回person详细信息
     * @param 
     * @return 
     **/
    @RequestMapping("/getDetailPersonByid")
    @ResponseBody
    public Object getDetailPersonById(Integer id){
        return laoWuService.getDetailPersonById(id);
    }
}
