package com.hrpms.controller.user_manager;

import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.user_manager_service.SystemUserService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user-manager")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    //动态模糊分页查询系统用户
    @RequestMapping("/selectSystemUserByDuo")
    public String selectSystemUserByDuo(@RequestParam(defaultValue = "1") Integer currentPage,
                                        String username, String phone, String status, Model model){
        Map map = new HashMap();
        map.put("username",username);
        map.put("phone",phone);
        map.put("status",status);
        Page<TbSystemUser> page = systemUserService.selectSystemUserByDuo(currentPage, map);
        model.addAttribute("page",page);
        map.put("username",username);
        map.put("phone",phone);
        model.addAttribute("map",map);
        return "system-setting/user-manager/userList";
    }

    //进入用户添加页面
    @RequestMapping("/userAddJsp")
    public String userAddJsp(){
        return "system-setting/user-manager/userAdd";
    }

    //添加系统用户
    @RequestMapping("/addSystemUser")
    public String addSystemUser(TbSystemUser tbSystemUser){
        tbSystemUser.setCreateTime(new Date());
        systemUserService.addSystemUser(tbSystemUser);
        return "redirect:selectSystemUserByDuo";
    }

    //根据id查询系统用户
    @RequestMapping("/selectSystemUserById")
    public String selectSystemUserById(int flag,int id,Model model){
        TbSystemUser systemUser = systemUserService.selectSystemUserById(id);
        model.addAttribute("systemUser",systemUser);
        if(flag==1){    //flag为1时进入查看页面
            return "system-setting/user-manager/userSelect";
        }else {         //flag为2时进入修改页面
            return "system-setting/user-manager/userUpdate";
        }
    }

    //根据id修改系统用户
    @RequestMapping("/updateSystemUserById")
    public String updateSystemUserById(TbSystemUser tbSystemUser){
        tbSystemUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        systemUserService.updateSystemUserById(tbSystemUser);
        return "redirect:selectSystemUserByDuo";
    }

    //根据id删除系统用户
    @RequestMapping("/deleteSystemUserById")
    public String deleteSystemUserById(int id){
        systemUserService.deleteSystemUserById(id);
        return "redirect:selectSystemUserByDuo";
    }
}
