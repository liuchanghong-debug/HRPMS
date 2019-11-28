package com.hrpms.controller.marketing_manager_controller;

import com.hrpms.dao.customer_client_dao.CustomerDao;
import com.hrpms.dao.personal_information_dao.TbSystemUserDao;
import com.hrpms.dao.user_manager_dao.SystemUserDao;
import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbEmailRecord;
import com.hrpms.pojo.TbEmailTemplate;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.email_template_service.TbEmailTemplateService;
import com.hrpms.service.marketing_information_service.TbEmailRecoredService;
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
public class TbEmailRecordController {

    @Autowired
    private TbEmailRecoredService tbEmailRecoredService;

    @Autowired
    private SystemUserDao systemUserDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private TbEmailTemplateService tbEmailTemplateService;

    //动态模糊分页查询邮件发件箱
    @RequestMapping("/selectEmailRecoredByDuo")
    public String selectEmailRecoredByDuo(@RequestParam(defaultValue = "1") Integer currentPage,
                                          Integer user_id,String to_addr, Model model){
        Map map = new HashMap();
        map.put("to_addr",to_addr);
        map.put("user_id",user_id);
        Page<TbEmailRecord> page = tbEmailRecoredService.selectEmailRecoredByDuo(currentPage, map);
        model.addAttribute("page", page);
        model.addAttribute("map", map);
        return "business-menu/marketing-manager/emailList";
    }

    //进入添加邮件信息页面
    @RequestMapping("/addEmailRecoredJsp")
    public String addEmailRecoredJsp(){
        return "business-menu/marketing-manager/emailSend";
    }

    //进入选择邮件模板页面
    @RequestMapping("/selectModleJsp")
    public String selectModleJsp(@RequestParam(defaultValue = "1")Integer currentPage,
                                 String subject, Model model){
        Map map = new HashMap();
        map.put("subject",subject);
        Page<TbEmailTemplate> page = tbEmailTemplateService.selectEmailTemplateByDuo(currentPage, map);
        model.addAttribute("page", page);
        map.put("subject",subject);
        model.addAttribute("map", map);
        return "business-menu/marketing-manager/emaillTemplateSelect";
    }


    //添加邮件发件箱信息
    @RequestMapping("/addEmailRecored")
    public String addEmailRecored(TbEmailRecord tbEmailRecord){
        tbEmailRecord.setSendTime(new Timestamp(System.currentTimeMillis()));
        tbEmailRecoredService.addEmailRecored(tbEmailRecord);
        return "redirect:selectEmailRecoredByDuo";
    }

    //根据id查找邮件发件箱
    @RequestMapping("/selectEmailRecoredById")
    public String selectEmailRecoredById(int id,Model model){
        TbEmailRecord tbEmailRecord = tbEmailRecoredService.selectEmailRecoredById(id);
        TbSystemUser user = systemUserDao.selectSystemUserById(tbEmailRecord.getUser_id());
        TbCustomer customer = customerDao.selectCustomerByEmail(tbEmailRecord.getTo_addr());
        model.addAttribute("tbEmailRecord",tbEmailRecord);
        model.addAttribute("user",user);
        model.addAttribute("customer",customer);
        return "business-menu/marketing-manager/emailSelect";
    }


    //根据id删除邮件发件箱中信息
    @RequestMapping("/deleteEmailRecored")
    public String deleteEmailRecored(int id){
        tbEmailRecoredService.deleteEmailRecored(id);
        return "redirect:selectEmailRecoredByDuo";
    }
}
