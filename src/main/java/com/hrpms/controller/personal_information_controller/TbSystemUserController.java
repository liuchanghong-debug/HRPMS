package com.hrpms.controller.personal_information_controller;

import com.hrpms.service.personal_information_service.TbSystemUserService;
import com.hrpms.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personal-information")
public class TbSystemUserController {
    @Autowired
    TbSystemUserService tbSystemUserService;

    //跳转到个人信息页面
    @RequestMapping("/userPersonalInformation")
    public String userPersonalInformation(){
        return "business-menu/personal-information/saved_resource_message";
    }

    //跳转到修改密码页面
    @RequestMapping("/updateUserPassword")
    public String updateUserPassword(){
        return "business-menu/personal-information/saved_resource_password";
    }

    //修改密码
    @RequestMapping("/updatePersonalPassword")
    public String updatePersonalPassword(int id,String confirmNewPassword){
        try {
            confirmNewPassword=Md5Util.encodeByMd5(confirmNewPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tbSystemUserService.updatePersonalPassword(id,confirmNewPassword);
        return "redirect:userPersonalInformation";
    }


}
