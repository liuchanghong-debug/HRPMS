package com.hrpms.controller.system_setting_controller.role_manager_controller;

import com.alibaba.fastjson.JSONArray;
import com.hrpms.pojo.TbSystemFunction;
import com.hrpms.pojo.TbSystemRole;
import com.hrpms.service.system_setting_service.role_manager_service.TbSystemRoleService;
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
@RequestMapping("/role-manager")
public class TbSystemRoleController {

    @Autowired
    private TbSystemRoleService tbSystemRoleService;

    //动态模糊查询角色表
    @RequestMapping("/selectSystemRoleByDuo")
    public String selectSystemRoleByDuo(@RequestParam(defaultValue = "1") Integer currentPage,
                                        String roleName, Model model){
        Map map = new HashMap();
        map.put("roleName",roleName);
        Page<TbSystemRole> page = tbSystemRoleService.selectSystemRoleByDuo(currentPage, map);
        model.addAttribute("page",page);
        map.put("roleName",roleName);
        model.addAttribute("map",map);
        return "system-setting/role-manager/roleList";
    }

    //进入添加角色页面
    @RequestMapping("/addSystemRoleJsp")
    public String addSystemRoleJsp(){
        return "system-setting/role-manager/roleAdd";
    }

    //添加角色信息
    @RequestMapping("/addSystemRole")
    public String addSystemRole(TbSystemRole tbSystemRole,String menuIds){
        tbSystemRole.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tbSystemRoleService.addSystemRole(tbSystemRole,menuIds);
        return "redirect:selectSystemRoleByDuo";
    }

    //根据id查询角色信息
    @RequestMapping("/selectSystemRoleById")
    public String selectSystemRoleById(int id,int flag,Model model){
        TbSystemRole tbSystemRole = tbSystemRoleService.selectSystemRoleById(id);
        Set<TbSystemFunction> systemFunctions = tbSystemRole.getTbSystemFunctions();
        String menuIds="";
        List list = new ArrayList();
        for(TbSystemFunction function:systemFunctions){
            menuIds += function.getId()+"," ;
            Map map = new HashMap();
            map.put("id",function.getId());
            map.put("pId",function.getParentId());
            map.put("name",function.getFuncName());
            map.put("nocheck",true);
            list.add(map);
        }
        model.addAttribute("list",JSONArray.toJSON(list));
        model.addAttribute("menuIds",menuIds);
        model.addAttribute("tbSystemRole",tbSystemRole);

        if(flag==1){        //跳到查看页面
            return "system-setting/role-manager/roleSelect";
        }else{              //跳到修改页面（flag=2）
            return "system-setting/role-manager/roleUpdate";
        }
    }

    //根据id修改角色信息
    @RequestMapping("/updateSystemById")
    public String updateSystemById(TbSystemRole tbSystemRole,String menuIds){
        TbSystemRole tbSystemRole1 = tbSystemRoleService.selectSystemRoleById(tbSystemRole.getId());
        tbSystemRole.setCreateBy(tbSystemRole1.getCreateBy());
        tbSystemRole.setCreateTime(tbSystemRole1.getCreateTime());
        tbSystemRole.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        tbSystemRoleService.updateSystemRoleById(tbSystemRole,menuIds);
        return "redirect:selectSystemRoleByDuo";
    }

    //查询所有的角色名称
    @RequestMapping("/selectAllRoleName")
    @ResponseBody
    public List selectAllRoleName(){
        List<TbSystemRole> tbSystemRoles = tbSystemRoleService.selectAllRoleName();
        List list = new ArrayList();
        for(TbSystemRole systemRole:tbSystemRoles){
            Map map = new HashMap();
            map.put("id",systemRole.getId());
            map.put("roleName",systemRole.getRoleName());
            list.add(map);
        }
        return list;
    }

    //根据id删除角色
    @RequestMapping("/deleteRoleById")
    public String deleteRoleById(int id){
        tbSystemRoleService.deleteSystemRoleById(id);
        return "redirect:selectSystemRoleByDuo";
    }

    //角色名称唯一验证
    @RequestMapping("/roleNameIsOne")
    @ResponseBody
    public boolean roleNameIsOne(String roleName){
        boolean bo = tbSystemRoleService.roleNameIsOne(roleName);
        return bo;
    }
}
