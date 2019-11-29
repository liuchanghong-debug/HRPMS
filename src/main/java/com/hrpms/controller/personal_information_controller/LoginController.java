package com.hrpms.controller.personal_information_controller;

import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.personal_information_service.TbSystemUserService;
import com.hrpms.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private TbSystemUserService tbSystemUserService;

    //登录
    @RequestMapping("/login")
    public String login(String username, String password,
                        HttpSession session, Model model){
        try {
            password=Md5Util.encodeByMd5(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbSystemUser tbSystemUser = tbSystemUserService.login(username, password);
        if(tbSystemUser!=null && tbSystemUser.getId()!=0){
            session.setAttribute("tbSystemUser",tbSystemUser);
            return "index";
        }else {
            return "../../login";
        }
    }

    //退出登录
    @RequestMapping("/loginout")
    public String loginout(HttpSession session){
        session.invalidate();
        return "redirect:login.jsp";
    }
}
