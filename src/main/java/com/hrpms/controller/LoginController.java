package com.hrpms.controller;

import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.TbSystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private TbSystemUserService tbSystemUserService;

    @RequestMapping("/login")
    public String login(String username, String password,
                        HttpSession session, Model model){
        TbSystemUser tbSystemUser = tbSystemUserService.login(username, password);
        if(tbSystemUser!=null && tbSystemUser.getId()!=0){
            session.setAttribute("tbSystemUser",tbSystemUser);
            return "index";
        }else {
            String mess="用户名或密码错误，请重试";
            model.addAttribute("mess",mess);
            return "../../login";
        }
    }
}
