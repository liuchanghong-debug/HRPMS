package com.hrpms.controller.system_setting_controller.user_manager_controller;

import com.hrpms.pojo.TbSystemRole;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.system_setting_service.user_manager_service.SystemUserService;
import com.hrpms.utils.Md5Util;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;

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
    public String addSystemUser(TbSystemUser tbSystemUser,@RequestParam("userRoleList") String[] userRoleList){
        try {
            tbSystemUser.setPassword(Md5Util.encodeByMd5(tbSystemUser.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tbSystemUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
        systemUserService.addSystemUser(tbSystemUser,userRoleList);
        return "redirect:selectSystemUserByDuo";
    }

    //根据id查询系统用户
    @RequestMapping("/selectSystemUserById")
    public String selectSystemUserById(int flag,int id,Model model){
        TbSystemUser systemUser = systemUserService.selectSystemUserById(id);
        Set<TbSystemRole> tbSystemRoles = systemUser.getTbSystemRoles();
        model.addAttribute("systemUser",systemUser);
        String userRole = "";
        for(TbSystemRole role:tbSystemRoles){
            userRole += role.getId()+"," ;
        }
        model.addAttribute("userRole",userRole);
        if(flag==1){    //flag为1时进入查看页面
            return "system-setting/user-manager/userSelect";
        }else {         //flag为2时进入修改页面
            return "system-setting/user-manager/userUpdate";
        }
    }

    //根据id修改系统用户
    @RequestMapping("/updateSystemUserById")
    public String updateSystemUserById(TbSystemUser tbSystemUser,Integer userRoleId,@RequestParam("userRoleList")String[] userRoleList){
        TbSystemUser tbSystemUser1 = systemUserService.selectSystemUserById(tbSystemUser.getId());
        try {
            if(tbSystemUser1.getPassword().equals(tbSystemUser.getPassword())){

            }else{
                tbSystemUser.setPassword(Md5Util.encodeByMd5(tbSystemUser.getPassword()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        tbSystemUser.setCreateTime(tbSystemUser1.getCreateTime());
        tbSystemUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        systemUserService.updateSystemUserById(tbSystemUser,userRoleList);
        return "redirect:selectSystemUserByDuo";
    }

    //根据id删除系统用户
    @RequestMapping("/deleteSystemUserById")
    public String deleteSystemUserById(int id){
        systemUserService.deleteSystemUserById(id);
        return "redirect:selectSystemUserByDuo";
    }

    //用户名的唯一验证
    @RequestMapping("/isOneUsername")
    @ResponseBody
    public boolean isOneUsername(String username){
        TbSystemUser systemUser = systemUserService.isOneUsername(username);
        boolean bo = true;  //用户名唯一
        if(systemUser!=null && systemUser.getId()!=0){
            bo = false;     //用户名不唯一
        }
        return bo;
    }

    //查询所有用户名称
    @RequestMapping("/selectAllUserName")
    @ResponseBody
    public List selectAllUserName(){
        List<TbSystemUser> tbSystemUsers = systemUserService.selectAllUserName();
        List list = new ArrayList();
        for(TbSystemUser tb:tbSystemUsers){
            Map map = new HashMap();
            map.put("id",tb.getId());
            map.put("username",tb.getUsername());
            list.add(map);
        }
        return list;
    }

    //用户手机号码唯一验证
    @RequestMapping("/isOneUserPhone")
    @ResponseBody
    public boolean isOneUserPhone(String phone){
        TbSystemUser userPhone = systemUserService.isOneUserPhone(phone);
        boolean bo = true;  //手机号码唯一
        if(userPhone!=null && userPhone.getId()!=0){
            bo = false;     //手机号码不唯一
        }
        return bo;
    }

    //用户电子邮件唯一验证
    @RequestMapping("/isOneUserEmail")
    @ResponseBody
    public boolean isOneUserEmail(String email){
        TbSystemUser userEmail = systemUserService.isOneUserEmail(email);
        boolean bo = true;  //电子邮件唯一
        if(userEmail!=null && userEmail.getId()!=0){
            bo = false;     //电子邮件不唯一
        }
        return bo;
    }

}
