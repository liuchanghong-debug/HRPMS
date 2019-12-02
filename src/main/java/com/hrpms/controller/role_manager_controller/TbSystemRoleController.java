package com.hrpms.controller.role_manager_controller;

import com.hrpms.pojo.TbSystemFunction;
import com.hrpms.pojo.TbSystemRole;
import com.hrpms.service.role_manager_service.TbSystemRoleService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

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
    public String addSystemRole(TbSystemRole tbSystemRole, TbSystemFunction tbSystemFunction){
        tbSystemRoleService.addSystemRole(tbSystemRole,tbSystemFunction);
        return "redirect:selectSystemRoleByDuo";
    }
}
