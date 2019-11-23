package com.hrpms.controller.shebao_manager;

import com.hrpms.pojo.TbSocialInsurance;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbSocialInsuranceOperation;
import com.hrpms.service.shebao_manager_service.SheBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    @Autowired
    private SheBaoService sheBaoService;


    /**
     * 社保多条件查询
     * @param 
     * @return 
     **/
    @RequestMapping("/shebaoList")
    public String shebaoList(@RequestParam(defaultValue = "1") Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation, Model model){
        model.addAttribute("socialInsuranceOperation", socialInsuranceOperation);
        model.addAttribute("page", sheBaoService.socialInsuranceQueryByOperation(currentPage, socialInsuranceOperation));
        model.addAttribute("status", sheBaoService.getDictBySheBao());

        return "business-menu/shebao-manager/sheBaoList";
    }
    /**
     * 社保信息详细展示
     * @param 
     * @return 
     **/
    @RequestMapping("/shebaoShow")
    public String shebaoShow(Integer id, Model model){
        TbSocialInsurance sheBaoByIdCard = sheBaoService.getSheBaoByIdCard(id);
        model.addAttribute("socialInsurance", sheBaoByIdCard);
        model.addAttribute("companys", sheBaoService.getCompanys());
        model.addAttribute("customer", sheBaoService.getCustomerByIdCard(sheBaoByIdCard.getIdCard()));
        return "business-menu/shebao-manager/sheBaoUpdateDisable";
    }



    /**
     * 社保信息添加页面
     * @param
     * @return
     **/
    @RequestMapping("/shebaoToAdd")
    public String shebaoToAdd(Model model){
        //社保状态
        model.addAttribute("sheBaoStatus", sheBaoService.getDictBySheBao());
        return "business-menu/shebao-manager/sheBaoAdd";
    }
    /**
     * 返回个人客户信息  社保表中已经存在的不返回
     * @param 
     * @return 
     **/
    @RequestMapping("/getCustomerNotInSocia")
    @ResponseBody
    public Object getCustomerNotInSocia(){
        return sheBaoService.getNotInSociaOfCustomer();
    }
    /**
     * 通过id得到详细的客户信息
     * @param
     * @return
     **/
    @RequestMapping("/getDetailOfCustomerById")
    @ResponseBody
    public Object getDetailOfCustomerById(Integer id){
        return sheBaoService.getDetailOfCustomerById(id);
    }
    /**
     * 社保信息添加
     * @param 
     * @return 
     **/
    @RequestMapping("/shebaoAdd")
    public String shebaoAdd(TbSocialInsurance socialInsurance, HttpSession session, Model model){
        //创建者
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        sheBaoService.shebaoAdd(socialInsurance, tbSystemUser.getId());

        return shebaoList(1, new TbSocialInsuranceOperation(), model);
    }



    
    
    /**
     * 社保缴费多条件查询页面
     * @param 
     * @return 
     **/
    @RequestMapping("/shebaoRecord")
    public String shebaoRecord(){

        return "business-menu/shebao-manager/sheBaoRecordList";
    }

    /**
     * 社保缴费添加页面
     * @param
     * @return
     **/
    @RequestMapping("/shebaoRecordAdd")
    public String shebaoRecordAdd(){
        
        return "business-menu/shebao-manager/sheBaoRecordAdd";
    }
    
    
    
    
    /**
     * 社保催缴
     * @param 
     * @return 
     **/
}
