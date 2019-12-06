package com.hrpms.controller.system_setting_controller.email_template_controller;

import com.hrpms.pojo.TbEmailTemplate;
import com.hrpms.service.system_setting_service.email_template_service.TbEmailTemplateService;
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
@RequestMapping("/email-template")
public class TbEmailTemplateController {

    @Autowired
    private TbEmailTemplateService tbEmailTemplateService;

    //动态模糊分页查询邮件模板信息
    @RequestMapping("/selectEmailTemplateByDuo")
    public String selectEmailTemplateByDuo(@RequestParam(defaultValue = "1")Integer currentPage,
                                           String subject, Model model){
        Map map = new HashMap();
        map.put("subject",subject);
        Page<TbEmailTemplate> page = tbEmailTemplateService.selectEmailTemplateByDuo(currentPage, map);
        model.addAttribute("page", page);
        map.put("subject",subject);
        model.addAttribute("map", map);
        return "system-setting/email-template/emailTemplateList";
    }

    //进入添加邮件信息页面
    @RequestMapping("/addEmailTemplateJsp")
    public String addEmailTemplateJsp(){
        return "system-setting/email-template/emailTemplateAdd";
    }

    //添加邮件模板信息
    @RequestMapping("/addEmailTemplate")
    public String addEmailTemplate(TbEmailTemplate tbEmailTemplate){
        tbEmailTemplate.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tbEmailTemplateService.addEmailTemplate(tbEmailTemplate);
        return "redirect:selectEmailTemplateByDuo";
    }

    //根据id查找邮件模板新
    @RequestMapping("/selectEmailTemplateById")
    public String selectEmailTemplateById(int id,int flag,Model model){
        TbEmailTemplate tbEmailTemplate = tbEmailTemplateService.selectEmailTemplateById(id);
        model.addAttribute("tbEmailTemplate",tbEmailTemplate);
        if(flag==1){        //进入查看页面
            return "system-setting/email-template/emailTemplateSelect";
        }else {             //进入修改页面
            return "system-setting/email-template/emailTemplateUpdate";
        }
    }

    //根据id修改邮件模板
    @RequestMapping("/updateEmailTemplate")
    public String updateEmailTemplate(TbEmailTemplate tbEmailTemplate){
        TbEmailTemplate tbEmailTemplate1 = tbEmailTemplateService.selectEmailTemplateById(tbEmailTemplate.getId());
        tbEmailTemplate1.setCreateTime(tbEmailTemplate1.getCreateTime());
        tbEmailTemplate.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        tbEmailTemplateService.updateEmailTemplate(tbEmailTemplate);
        return "redirect:selectEmailTemplateByDuo";
    }

    //根据id删除邮件模板
    @RequestMapping("/deleteEmailTemplate")
    public String deleteEmailTemplate(int id){
        tbEmailTemplateService.deleteEmailTemplate(id);
        return "redirect:selectEmailTemplateByDuo";
    }
}
