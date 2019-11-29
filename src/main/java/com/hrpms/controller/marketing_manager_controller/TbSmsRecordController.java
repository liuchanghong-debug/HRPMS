package com.hrpms.controller.marketing_manager_controller;

import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSmsRecord;
import com.hrpms.pojo.TbSmsTemplate;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.customer_client_service.CustomerService;
import com.hrpms.service.marketing_information_service.TbSmsRecoredService;
import com.hrpms.service.sms_template_service.TbSmsTemplateService;
import com.hrpms.service.user_manager_service.SystemUserService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/marketing-manager")
public class TbSmsRecordController {

    @Autowired
    private TbSmsRecoredService tbSmsRecoredService;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TbSmsTemplateService tbSmsTemplateService;



    //动态模糊分页查询短信发件箱
    @RequestMapping("/selectSmsRecoredByDuo")
    public String selectSmsRecoredByDuo(@RequestParam(defaultValue = "1") Integer currentPage,
                                                   Integer user_id,String telephone, Model model){
        Map map = new HashMap();
        map.put("telephone",telephone);
        map.put("user_id",user_id);
        Page<TbSmsRecord> page = tbSmsRecoredService.selectSmsRecoredByDuo(currentPage, map);
        model.addAttribute("page", page);
        model.addAttribute("map", map);
        return "business-menu/marketing-manager/smsList";
    }

    //进入添加短信信息页面
    @RequestMapping("/addSmsRecoredJsp")
    public String addSmsRecoredJsp(){
        return "business-menu/marketing-manager/smsSend";
    }

    //进入选择短信模板页面
    @RequestMapping("/selectSmsModleJsp")
    public String selectSmsModleJsp(@RequestParam(defaultValue = "1")Integer currentPage,
                                    String subject, String template_code, Model model){
        Map map = new HashMap();
        map.put("subject",subject);
        map.put("template_code",template_code);
        Page<TbSmsTemplate> page = tbSmsTemplateService.selectSmsTemplateByDuo(currentPage, map);
        model.addAttribute("page", page);
        map.put("subject",subject);
        map.put("template_code",template_code);
        model.addAttribute("map", map);
        model.addAttribute("page",page);
        return "business-menu/marketing-manager/smsTemplateSelect";
    }


    //添加短信发件箱信息
    @RequestMapping("/addSmsRecored")
    public String addSmsRecored(TbSmsRecord tbSmsRecord){
        tbSmsRecord.setSendtime(new Timestamp(System.currentTimeMillis()));
        tbSmsRecoredService.addSmsRecored(tbSmsRecord);
        return "redirect:selectSmsRecoredByDuo";
    }

    //根据id查找短信发件箱
    @RequestMapping("/selectSmsRecoredById")
    public String selectSmsRecoredById(int id,Model model){
        TbSmsRecord tbSmsRecord = tbSmsRecoredService.selectSmsRecoredById(id);
        TbSystemUser user = systemUserService.selectSystemUserById(tbSmsRecord.getUser_id());
        TbCustomer customer = customerService.selectCustomerByEmail(tbSmsRecord.getTelephone());
        model.addAttribute("user",user);
        model.addAttribute("customer",customer);
        model.addAttribute("tbSmsRecord",tbSmsRecord);
        return "business-menu/marketing-manager/smsSelect";
    }


    //根据id删除短信发件箱中信息
    @RequestMapping("/deleteSmsRecoredById")
    public String deleteSmsRecoredById(int id){
        tbSmsRecoredService.deleteSmsRecoredById(id);
        return "redirect:selectSmsRecoredByDuo";
    }
}
