package com.hrpms.controller.talent_controller.person_controller;

import com.hrpms.pojo.TbPerson;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbPersonOperation;
import com.hrpms.service.talen_service.person_service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/person")
public class TalentPersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping("/personList")
    public String personList(@RequestParam(defaultValue = "1") Integer currentPage, TbPersonOperation personOperation, Model model){
        model.addAttribute("jobType", personService.getDictsByName("工作类型"));
        model.addAttribute("statuss", personService.getDictsByName("人才状态"));
        model.addAttribute("personOperation", personOperation);
        model.addAttribute("page", personService.personList(currentPage, personOperation));

        return "business-menu/talent-service/personList";
    }

    @RequestMapping("/personToAdd")
    public String personToAdd(Model model){
        model.addAttribute("jobTypes", personService.getDictsByName("工作类型"));
        return "business-menu/talent-service/personAdd";
    }

    @RequestMapping("/personAdd")
    public String personAdd(MultipartFile resumeFile, TbPerson person, HttpSession session, HttpServletRequest request, Model model) throws IOException {
        TbSystemUser systemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        personService.personAdd(resumeFile, person, systemUser.getId(), request);

        return personList(1, new TbPersonOperation(), model);
    }
}
