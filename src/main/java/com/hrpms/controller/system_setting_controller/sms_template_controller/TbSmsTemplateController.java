package com.hrpms.controller.system_setting_controller.sms_template_controller;

import com.hrpms.pojo.TbSmsTemplate;
import com.hrpms.service.system_setting_service.sms_template_service.TbSmsTemplateService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sms-template")
public class TbSmsTemplateController {
    @Autowired
    private TbSmsTemplateService tbSmsTemplateService;

    //动态模糊分页查询短信模板信息
    @RequestMapping("/selectSmsTemplateByDuo")
    public String selectSmsTemplateByDuo(@RequestParam(defaultValue = "1")Integer currentPage,
                                         String subject, String template_code, Model model){
        Map map = new HashMap();
        map.put("subject",subject);
        map.put("template_code",template_code);
        Page<TbSmsTemplate> page = tbSmsTemplateService.selectSmsTemplateByDuo(currentPage, map);
        model.addAttribute("page", page);
        map.put("subject",subject);
        map.put("template_code",template_code);
        model.addAttribute("map", map);
        return "system-setting/sms-template/smsTemplateList";

    }

    //进入添加页面
    @RequestMapping("/addSmsTemplateJsp")
    public String addSmsTemplateJsp(){
        return "system-setting/sms-template/smsTemplateAdd";
    }


    //添加短信模板信息
    @RequestMapping("/addSmsTemplate")
    public String addSmsTemplate(TbSmsTemplate tbSmsTemplate){
        tbSmsTemplate.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tbSmsTemplateService.addSmsTemplate(tbSmsTemplate);
        return "redirect:selectSmsTemplateByDuo";
    }

    //根据id查找短信模板信息
    @RequestMapping("/selectSmsTemplateById")
    public String selectSmsTemplateById(int id,Integer flag,Model model){
        TbSmsTemplate tbSmsTemplate = tbSmsTemplateService.selectSmsTemplateById(id);
        model.addAttribute("tbSmsTemplate",tbSmsTemplate);
        if(flag==1){        //进入查看页面
            return "system-setting/sms-template/smsTemplateSelect";
        }else {             //进入修改页面
            return "system-setting/sms-template/smsTemplateUpdate";
        }
    }

    //根据id修改短信模板
    @RequestMapping("/updateSmsTemplate")
    public String updateSmsTemplate(TbSmsTemplate tbSmsTemplate){
        TbSmsTemplate tbSmsTemplate1 = tbSmsTemplateService.selectSmsTemplateById(tbSmsTemplate.getId());
        tbSmsTemplate.setCreateTime(tbSmsTemplate1.getCreateTime());
        tbSmsTemplate.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        tbSmsTemplateService.updateSmsTemplate(tbSmsTemplate);
        return "redirect:selectSmsTemplateByDuo";
    }

    //根据id删除短信模板
    @RequestMapping("/deleteSmsTemplate")
    public String deleteSmsTemplate(int id){
        tbSmsTemplateService.deleteSmsTemplate(id);
        return "redirect:selectSmsTemplateByDuo";
    }

    //短信模板编码唯一验证
    @RequestMapping("/TemplateCodeIsOne")
    @ResponseBody
    public boolean TemplateCodeIsOne(String template_code){
        TbSmsTemplate tbSmsTemplate = tbSmsTemplateService.TemplateCodeIsOne(template_code);
        boolean bo = true;          //唯一
        if(tbSmsTemplate!=null && tbSmsTemplate.getId()!=0){    //不唯一
            bo = false ;
        }
        return bo;
    }
}
