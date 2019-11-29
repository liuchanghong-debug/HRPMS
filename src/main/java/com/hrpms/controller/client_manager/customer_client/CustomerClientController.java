package com.hrpms.controller.client_manager.customer_client;

import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbCustomerOperation;
import com.hrpms.service.customer_client_service.CustomerService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
    public String customerList(@RequestParam(defaultValue = "1") Integer currentPage, TbCustomerOperation customerOperation, Model model){
        //所属公司查询
        model.addAttribute("companys", customerService.getAllCompanyOfIdAndName());
        model.addAttribute("customerOperation", customerOperation);
        //根据条件查询
        model.addAttribute("page", customerService.customerAllByOperationAndPaging(currentPage, customerOperation));

        return "business-menu/client-manager/individual-client/customerList";
    }
    /**
     * 查看详细信息
     * @param
     * @return
     **/
    @RequestMapping("/customerMess")
    public String customerMess(Integer id, Model model){
        TbCustomer customer = customerService.customerById(id);
        //需要客户类别，客户状态，所属公司
        List<TbSystemDict> dictOfType = customerService.getDataDictByName("客户类别");
        List<TbSystemDict> dictOfStatus = customerService.getDataDictByName("客户状态");
        for (TbSystemDict dict : dictOfType){
            if(dict.getValue().equals(customer.getCustomerType())){
                customer.setCustomerType(dict.getLabel());
            }
        }
        for (TbSystemDict dict : dictOfStatus){
            if(dict.getValue().equals(customer.getStatus())){
                customer.setCustomerType(dict.getLabel());
            }
        }
        model.addAttribute("customer", customer);
        model.addAttribute("companys", customerService.getAllCompanyOfIdAndName());

        return "business-menu/client-manager/individual-client/customerUpdateDisable";
    }
    /**
     * 修改页面
     * @param
     * @return
     **/
    @RequestMapping("/customerUpdate")
    public String customerUpdate(Integer id, Integer currentPage, TbCustomerOperation customerOperation, Model model){
        //客户详细信息
        TbCustomer customer = customerService.customerById(id);
        model.addAttribute("customer", customerService.customerById(id));
        model.addAttribute("customerOperation", customerOperation);
        model.addAttribute("currentPage", currentPage);
        //需要客户类别，客户状态，所属公司
        model.addAttribute("customerTypes", customerService.getDataDictByName("客户类别"));
        model.addAttribute("customerStatuss", customerService.getDataDictByName("客户状态"));
        model.addAttribute("companys", customerService.getAllCompanyOfIdAndName());

        return "business-menu/client-manager/individual-client/customerUpdate";
    }
    /**
     * 确定修改
     * @param
     * @return
     **/
    @RequestMapping("/customerToUpdate")
    public String customerToUpdate(Integer currentPage, TbCustomer customer, HttpSession session, TbCustomerOperation customerOperation, Model model){
        //更新者
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        customer.setUpdateBy(tbSystemUser.getId());
        customerService.customerUpdate(customer);

        return customerList(currentPage, customerOperation, model);
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
        model.addAttribute("companys", customerService.getAllCompanyOfIdAndName());

        return "business-menu/client-manager/individual-client/customerAdd";
    }
    /**
     * 添加数据
     * @param
     * @return
     **/
    @RequestMapping("/customerToAdd")
    public String customerToAdd(TbCustomer tbCustomer, HttpSession session, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        tbCustomer.setCreateBy(tbSystemUser.getId());
        customerService.customerToAdd(tbCustomer);

        return customerList(1, new TbCustomerOperation(), model);
    }
    /**
     * 删除  改删除状态为1
     * @param
     * @return
     **/
    @RequestMapping("/customerDelete")
    public String customerDelete(Integer currentPage, HttpSession session, TbCustomerOperation customerOperation, Integer id, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        customerService.customerDelete(id, tbSystemUser.getId());
        return customerList(currentPage, customerOperation, model);
    }
    /**
     * 模板下载
     * @param
     * @return
     **/
    @RequestMapping("/customerTemplateDownload")
    public void customerTemplateDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        customerService.customerTemplateDownload(name, request, response);
    }
    /**
     * 数据导出
     * @param
     * @return
     **/
    @RequestMapping("/customerDataOutOfExcel")
    public void customerDataOutOfExcel(TbCustomerOperation customerOperation, HttpServletRequest request, HttpServletResponse response) throws Exception {
        customerService.customerDataOutOfExcel(customerOperation, request, response);
    }
    /**
     * 数据导入
     * @param
     * @return
     **/
    @RequestMapping("/customerUploadOfExcel")
    public String customerUploadOfExcel(MultipartFile file, HttpSession session, Model model) throws IOException, InvalidFormatException, ParseException {
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        customerService.customerUploadOfExcel(file.getInputStream(), tbSystemUser.getId());
        return customerList(1, new TbCustomerOperation(), model);
    }

    //查询所有客户的名称
    @RequestMapping("/selectAllCustomerName")
    @ResponseBody
    public List<TbCustomer> selectAllCustomerName(){
        List<TbCustomer> list = customerService.selectAllCustomerName();
        return list;
    }

    /**
     * 异步验证idCard是否唯一
     * @param
     * @return
     **/
    @RequestMapping("/customerIdCardIsOnly")
    @ResponseBody
    public Object customerIdCardIsOnly(String idCard){
        return customerService.customerIdCardIsOnly(idCard);
    }
    /**
     * 异步验证idCard是否唯一  排除自己
     * @param
     * @return
     **/
    @RequestMapping("/customerIdCardIsOnlyUpdate")
    @ResponseBody
    public Object customerIdCardIsOnlyUpdate(Integer id, String idCard){
        return customerService.customerIdCardIsOnlyUpdate(id, idCard);
    }
}
