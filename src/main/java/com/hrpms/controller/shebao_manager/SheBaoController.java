package com.hrpms.controller.shebao_manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.controller.shebao_manager > SheBaoService
 * @description TODO
 * @create 2019/11/21  18:37
 * @versiion 1.0
 * @Description:
 */
@Controller
@RequestMapping("/shebao")
public class SheBaoController {
    /**
     * 社保多条件查询
     * @param 
     * @return 
     **/
    @RequestMapping("/shebaoList")
    public String shebaoLidt(){

        return "business-menu/shebao-manager/sheBaoList";
    }





    /**
     * 社保信息添加页面
     * @param
     * @return
     **/
    @RequestMapping("/shebaoToAdd")
    public String shebaoToAdd(){

        return "business-menu/shebao-manager/sheBaoAdd";
    }
}
