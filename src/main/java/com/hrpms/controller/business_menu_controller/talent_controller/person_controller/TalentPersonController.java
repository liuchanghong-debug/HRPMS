package com.hrpms.controller.business_menu_controller.talent_controller.person_controller;

import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbPersonOperation;
import com.hrpms.service.business_menu_service.talen_service.person_service.PersonService;
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
 * @package HRPMS > com.hrpms.controller.talent_controller.person > TalentPersonController
 * @description TODO
 * @create 2019/11/25  17:42
 * @versiion 1.0
 * @Description:
 */
@Controller
@RequestMapping("/talent")
public class TalentPersonController {
    @Autowired
    private PersonService personService;
    
    /**
     * 分页条件查询
     * @param 
     * @return 
     **/
    @RequestMapping("/personList")
    public String personList(@RequestParam(defaultValue = "1") Integer currentPage, TbPersonOperation personOperation, Model model){
        model.addAttribute("jobTypes", personService.getDictsByName("工作类型"));
        model.addAttribute("statuss", personService.getDictsByName("人才状态"));
        model.addAttribute("personOperation", personOperation);
        model.addAttribute("page", personService.personList(currentPage, personOperation));

        return "business-menu/talent-service/personList";
    }

    /**
     * 详细信息查看
     * @param 
     * @return 
     **/
    @RequestMapping("/personDetailById")
    public String personDetailById(Integer id, Model model){
        model.addAttribute("person", personService.personDetailById(id));
        model.addAttribute("jobTypes", personService.getDictsByName("工作类型"));
        model.addAttribute("statuss", personService.getDictsByName("人才状态"));
        return "business-menu/talent-service/personUpdateDisable";
    }


    /**
     * 添加页面
     * @param 
     * @return 
     **/
    @RequestMapping("/personToAdd")
    public String personToAdd(Model model){
        model.addAttribute("jobTypes", personService.getDictsByName("工作类型"));
        model.addAttribute("statuss", personService.getDictsByName("人才状态"));
        //正常状态的客户信息添加到页面中进行选择  不需要已经存在的人才信息不显示
        model.addAttribute("customerList", personService.normalCustomerOfIdAndName());

        return "business-menu/talent-service/personAdd";
    }

    /**
     * 添加
     * @param
     * @return
     **/
    @RequestMapping("/personAdd")
    public String personAdd(MultipartFile resumeFile, TbPerson person, HttpSession session, HttpServletRequest request, Model model) throws IOException {
        TbSystemUser systemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        personService.personAdd(resumeFile, person, systemUser.getId(), request);

        return personList(1, new TbPersonOperation(), model);
    }
    
    /**
     * 修改页面
     * @param 
     * @return 
     **/
    @RequestMapping("/personToUpdate")
    public String personUpdate(Integer id, Integer currentPage, TbPersonOperation personOperation, Model model){
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("personOperation", personOperation);
        model.addAttribute("person", personService.personDetailById(id));
        model.addAttribute("jobTypes", personService.getDictsByName("工作类型"));
        model.addAttribute("statuss", personService.getDictsByName("人才状态"));

        return "business-menu/talent-service/personUpdate";
    }
    /**
     * 确认修改
     * @param
     * @return
     **/
    @RequestMapping("/personUpdate")
    public String personUpdate(Integer currentPage, TbPerson person, HttpSession session, MultipartFile resumeFile, TbPersonOperation personOperation, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        TbSystemUser systemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        personService.personUpdate(person, resumeFile, systemUser.getId(), request, response);

        return personList(currentPage, personOperation, model);
    }
    /**
     * 删除
     * @param 
     * @return 
     **/
    @RequestMapping("/personDelete")
    public String personDelete(Integer id, Integer currentPage, HttpSession session, TbPersonOperation personOperation, Model model){
        TbSystemUser systemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        personService.personDelete(id, systemUser.getId());
        return personList(currentPage, personOperation, model);
    }

    /**
     * 简历预览
     * @param
     * @return
     **/
    @RequestMapping("/resumeUrlPreview")
    public void resumeUrlPreview(String personResumeUrl, HttpServletRequest request, HttpServletResponse response) throws Exception {
        personService.resumeUrlPreview(personResumeUrl, request, response);
    }

    /**
     * 添加判断idCard是否唯一
     * @param
     * @return
     **/
    @RequestMapping("/personIdCardIsOnly")
    @ResponseBody
    public Object personIdCardIsOnly(String idCard){
        return personService.personIdCardIsOnly(idCard);
    }

    /**
     * 添加判断idCard是否唯一  修改的时候不查自己
     * @param
     * @return
     **/
    @RequestMapping("/personIdCardIsOnlyUpdate")
    @ResponseBody
    public Object personIdCardIsOnlyUpdate(Integer id, String idCard){
        return personService.personIdCardIsOnlyUpdate(id, idCard);
    }
    /**
     * 添加用  选中姓名返回idCard  通过customer的id查询
     * @param 
     * @return 
     **/
    @RequestMapping("/personIdCardById")
    @ResponseBody
    public Object personIdCardById(Integer customerId){
        return personService.customerIdCardById(customerId);
    }
}
