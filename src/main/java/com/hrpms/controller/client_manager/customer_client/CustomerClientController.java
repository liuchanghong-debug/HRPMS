package com.hrpms.controller.client_manager.customer_client;

import com.hrpms.pojo.TbCustomer;
import com.hrpms.service.customer_client_service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.controller.client_manager.person_client > PersonalClient
 * @description TODO
 * @create 2019/11/22  16:09
 * @versiion 1.0
 * @Description:
 */
@Controller
@RequestMapping("/customerClient")
public class CustomerClientController {
    @Autowired
    private CustomerService customerService;
    /**
     * 分页查询页面
     * @param 
     * @return 
     **/
    @RequestMapping("/customerList")
    public String customerList(){

        return "business-menu/client-manager/individual-client/customerList";
    }

    /**
     * 添加页面展示
     * @param
     * @return
     **/
    @RequestMapping("/customerAdd")
    public String customerAdd(Model model){
        //需要客户类别，客户状态，所属公司
        model.addAttribute("customerTypes", customerService.getDataDictByName("客户类别"));
        model.addAttribute("customerStatuss", customerService.getDataDictByName("客户状态"));

        return "business-menu/client-manager/individual-client/customerAdd";
    }

    @RequestMapping("/selectAllCustomerName")
    @ResponseBody
    public List<TbCustomer> selectAllCustomerName(){
        List<TbCustomer> list = customerService.selectAllCustomerName();
        return list;
    }
}
