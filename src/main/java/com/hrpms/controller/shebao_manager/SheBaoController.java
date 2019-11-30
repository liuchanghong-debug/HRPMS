package com.hrpms.controller.shebao_manager;

import com.hrpms.pojo.TbSocialInsurance;
import com.hrpms.pojo.TbSocialInsuranceRecord;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.SheBaoCountOperation;
import com.hrpms.pojo.operaton_select.TbSocialInsuranceOperation;
import com.hrpms.service.shebao_manager_service.SheBaoService;
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
        TbSocialInsurance sheBaoByIdCard = sheBaoService.getSheBaoById(id);
        model.addAttribute("socialInsurance", sheBaoByIdCard);
        model.addAttribute("companys", sheBaoService.getCompanys());
        model.addAttribute("customer", sheBaoService.getCustomerByIdCard(sheBaoByIdCard.getIdCard()));
        return "business-menu/shebao-manager/sheBaoUpdateDisable";
    }
    /**
     * 社保信息修改页面
     * @param 
     * @return 
     **/
    @RequestMapping("/shebaoToUpdate")
    public String shebaoToUpdate(Integer id, TbSocialInsuranceOperation socialInsuranceOperation, Integer currentPage, Model model){
        //根据id 查询
        TbSocialInsurance sheBaoById = sheBaoService.getSheBaoById(id);
        model.addAttribute("socialInsurance", sheBaoById);
        model.addAttribute("companys", sheBaoService.getCompanys());
        model.addAttribute("customer", sheBaoService.getCustomerByIdCard(sheBaoById.getIdCard()));
        model.addAttribute("socialInsuranceOperation", socialInsuranceOperation);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sheBaoStatus", sheBaoService.getDictBySheBao());

        return  "business-menu/shebao-manager/sheBaoUpdate";
    }
    /**
     * 社保修改保存
     * @param
     * @return
     **/
    @RequestMapping("/shebaoUpdate")
    public String shebaoUpdate(Integer currentPage, TbSocialInsurance socialInsurance, TbSocialInsuranceOperation socialInsuranceOperation, HttpSession session, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        sheBaoService.shebaoUpdate(socialInsurance, tbSystemUser.getId());
        return shebaoList(currentPage, socialInsuranceOperation, model);
    }
    /**
     * 社保置删除状态
     * @param
     * @return
     **/
    @RequestMapping("/shebaoDelete")
    public String shebaoDelete(Integer id, Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation, HttpSession session, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        sheBaoService.shebaoDelete(id, tbSystemUser.getId());
        return shebaoList(currentPage, socialInsuranceOperation, model);
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
     * 社保缴费列表  页面
     * @param 
     * @return 
     **/
    @RequestMapping("/shebaoRecordList")
    public String shebaoRecordList(@RequestParam(defaultValue = "1") Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation, Model model){
        model.addAttribute("socialInsuranceOperation", socialInsuranceOperation);
        model.addAttribute("payStatus", sheBaoService.getDictBySheBaoPay());
        model.addAttribute("page", sheBaoService.socialInsuranceRecordQueryByOperation(currentPage, socialInsuranceOperation));

        return "business-menu/shebao-manager/sheBaoRecordList";
    }

    /**
     * 社保缴费添加页面
     * @param
     * @return
     **/
    @RequestMapping("/shebaoRecordToAdd")
    public String shebaoRecordToAdd(Integer id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("socialInsuranceStatuss", sheBaoService.getDictBySheBaoPay());
        model.addAttribute("socialInsuranceRecord", sheBaoService.getNotPayOfTbSocialInsurance());
        return "business-menu/shebao-manager/sheBaoRecordAdd";
    }
    /**
     * 社保缴费添加
     * @param
     * @return
     **/
    @RequestMapping("/shebaoRecordAdd")
    public String shebaoRecordAdd(TbSocialInsuranceRecord socialInsuranceRecord, HttpSession session, Model model){
        //创建者
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        sheBaoService.shebaoRecordAdd(socialInsuranceRecord, tbSystemUser.getId());
        return shebaoRecordList(1, new TbSocialInsuranceOperation(), model);
    }
    /**
     * 社保详细信息展示
     * @param
     * @return
     **/
    @RequestMapping("/shebaoRecordDetailMess")
    public String shebaoRecordDetailMess(Integer id, Model model){
        model.addAttribute("map", sheBaoService.shebaoRecordDetailMess(id));
        model.addAttribute("statuss", sheBaoService.getSheBaoPayStatus());
        return "business-menu/shebao-manager/sheBaoRecordUpdateDisable";
    }

    /**
     * 根据sdCard查询详细信息
     * @param
     * @return
     **/
    @RequestMapping("/getDetailMessBySocialInsuranceId")
    @ResponseBody
    public Object getDetailMessBySocialInsuranceId(Integer id){
        return sheBaoService.getDetailMessBySocialInsuranceId(id);
    }
    /**
     * 社保缴费修改
     * @param
     * @return
     **/
    @RequestMapping("/shebaoPayToUpdate")
    public String shebaoPayToUpdate(Integer id, Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation, Model model){
        model.addAttribute("socialIncuranctOperation", socialInsuranceOperation);
        model.addAttribute("payStatuss", sheBaoService.getSheBaoPayStatus());
        model.addAttribute("map", sheBaoService.shebaoRecordDetailMess(id));
        model.addAttribute("currentPage", currentPage);
        return "business-menu/shebao-manager/sheBaoRecordUpdate";
    }
    /**
     * 社保信息确定修改
     * @param
     * @return
     **/
    @RequestMapping("/shebaoRecordUpdate")
    public String shebaoRecordUpdate(Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation, TbSocialInsuranceRecord socialInsuranceRecord, Model model, HttpSession session){
        //修改者
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        sheBaoService.shebaoRecordUpdate(socialInsuranceRecord, tbSystemUser.getId());

        return shebaoRecordList(currentPage, socialInsuranceOperation, model);
    }
    /**
     * 社保缴费删除
     * @param
     * @return
     **/
    @RequestMapping("/shebaoRecordDelete")
    public String shebaoRecordDelete(Integer id, Integer currentPage, HttpSession session, TbSocialInsuranceOperation socialInsuranceOperation, Model model){
        //修改者
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        sheBaoService.shebaoRecordDelete(id, tbSystemUser.getId());
        return shebaoRecordList(currentPage, socialInsuranceOperation, model);
    }

    /**
     * 社保催缴
     * @param 
     * @return 
     **/


    /**
     * 模板下载
     * @param
     * @return
     **/
    @RequestMapping("/shebaoTemplateDownload")
    public void shebaoTemplateDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        sheBaoService.shebaoTemplateDownload(name, request, response);
    }
    /**
     * 社保信息数据导出
     * @param
     * @return
     **/
    @RequestMapping("/dataOutOfExcel")
    public void dataOutOfExcel(TbSocialInsuranceOperation socialInsuranceOperation, HttpServletRequest request, HttpServletResponse response) throws Exception {
        sheBaoService.dataOutOfExcel(socialInsuranceOperation, request, response);
    }
    /**
     * 社保信息导入
     * @param 
     * @return 
     **/
    @RequestMapping("/shebaoInOfExcel")
    public String shebaoInOdfExcel(MultipartFile file, HttpSession session,  Model model) throws IOException, InvalidFormatException, ParseException {
        //创建者
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        sheBaoService.shebaoInOfExcel(file.getInputStream(), tbSystemUser.getId());
        return shebaoList(1, new TbSocialInsuranceOperation(), model);
    }

    /**
     * 社保添加异步查询  社保卡是否唯一
     * @param
     * @return
     **/
    @RequestMapping("/shebaoSbCardIsOnly")
    @ResponseBody
    public Object shebaoSbCardIdOnly(String sbCard){
        return sheBaoService.sbCardIsOnly(sbCard);
    }

    /**
     * 社保添加异步查询  社保卡是否唯一  修改  不查自己
     * @param
     * @return
     **/
    @RequestMapping("/shebaoSbCardIsOnlyUpdate")
    @ResponseBody
    public Object shebaoSbCardIsOnlyUpdate(Integer id, String sbCard){
        return sheBaoService.shebaoSbCardIsOnlyUpdate(id, sbCard);
    }



    /**
     * 社保统计
     * @param
     * @return
     **/
    @RequestMapping("/shebaoCount")
    public String shebaoCount(SheBaoCountOperation sheBaoCountOperation, Model model){
        //公司信息
        model.addAttribute("companys", sheBaoService.getCompanys());
        model.addAttribute("shebaoCountOperation", sheBaoCountOperation);
        model.addAttribute("data", sheBaoService.getSheBaoCount(sheBaoCountOperation));

        return "business-menu/report-statistics/sheBaoCount";
    }
    /**
     * 社保统计导出
     * @param 
     * @return 
     **/
    @RequestMapping("/shebaoCountOut")
    public void shebaoCount(SheBaoCountOperation sheBaoCountOperation, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //公司信息
        sheBaoService.getSheBaoCountOut(sheBaoCountOperation, request, response);
    }
}
