package com.hrpms.controller.system_setting_controller.menu_function_controller;

import com.hrpms.pojo.TbSystemFunction;
import com.hrpms.service.system_setting_service.menu_manager_service.TbSystemFunctionService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu-manager")
public class TbSystemFunctionController {
    @Autowired
    private TbSystemFunctionService tbSystemFunctionService;

    //查询所有的权限
    @RequestMapping("/selectAllSystemFunctionName")
    @ResponseBody
    private List selectAllSystemFunctionName(){
        List list = tbSystemFunctionService.selectAllSystemFunctionName();
        return list;
    }

    //动态模糊分页查询菜单列表
    @RequestMapping("/selectSystemFunctionByDuo")
    public String selectSystemFunctionByDuo(@RequestParam(defaultValue = "1")Integer currentPage,
                                            String funcName, Model model){
        Map map = new HashMap();
        map.put("funcName",funcName);
        Page<TbSystemFunction> page = tbSystemFunctionService.selectSystemFunctionByDuo(currentPage, map);
        model.addAttribute(page);
        map.put("funcName",funcName);
        model.addAttribute("map",map);
        return "system-setting/menu-manager/menuList";
    }

    //根据id删除权限名称（更改权限状态）
    @RequestMapping("/deleteSystemFunctionById")
    public String deleteSystemFunctionById(int id){
        tbSystemFunctionService.deleteSystemFunctionById(id);
        return "redirect:selectSystemFunctionByDuo";
    }

    //进入菜单添加页面
    @RequestMapping("/addSystemFunctionJsp")
    public String addSystemFunctionJsp(){
        return "system-setting/menu-manager/menuAdd";
    }
}
