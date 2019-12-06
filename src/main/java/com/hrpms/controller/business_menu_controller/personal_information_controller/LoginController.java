package com.hrpms.controller.business_menu_controller.personal_information_controller;

import com.hrpms.pojo.TbSystemFunction;
import com.hrpms.pojo.TbSystemRole;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.business_menu_service.personal_information_service.TbSystemUserService;
import com.hrpms.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
            Set<TbSystemRole> tbSystemRoles = tbSystemUser.getTbSystemRoles();
            String url = "";
            LinkedHashSet<TbSystemFunction> functions = new LinkedHashSet<>();
            for(TbSystemRole role:tbSystemRoles){
                if(role.getStatus().equals("0")){
                    Set<TbSystemFunction> tbSystemFunctions = role.getTbSystemFunctions();
                    for(TbSystemFunction systemFunction:tbSystemFunctions){
                        if(systemFunction.getStatus().equals("0")){
                            functions.add(systemFunction);
                        }
                    }
                }
            }
            session.setAttribute("functions",functions);
            session.setAttribute("tbSystemUser",tbSystemUser);
            return "index";
        }else {
            model.addAttribute("mess","密码或用户名错误！！");
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
