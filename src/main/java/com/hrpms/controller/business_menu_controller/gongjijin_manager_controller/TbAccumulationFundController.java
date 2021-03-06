package com.hrpms.controller.business_menu_controller.gongjijin_manager_controller;

import com.hrpms.pojo.TbAccumulationFund;
import com.hrpms.pojo.TbSalary;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.business_menu_service.gongjijin_manager_service.TbAccumulationFundService;
import com.hrpms.service.business_menu_service.salary_manager_service.TbSalaryService;
import com.hrpms.utils.Page;
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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gongjijin-manager")
public class TbAccumulationFundController {

    @Autowired
    private TbAccumulationFundService tbAccumulationFundService;

    @Autowired
    private TbSalaryService tbSalaryService;

    //模糊分页查询公积金信息
    @RequestMapping("/selectAccumulationByDuo")
    public String selectAccumulationByDuo(@RequestParam(defaultValue = "1")Integer currentPage,
                                          String idCard, String accountNo, Model model){
        Map map = new HashMap();
        map.put("idCard", idCard);
        map.put("accountNo", accountNo);
        Page<TbAccumulationFund> page = tbAccumulationFundService.selectAccumulationByDuo(currentPage, map);
        model.addAttribute("page", page);
        map.put("idCard", idCard);
        map.put("accountNo", accountNo);
        model.addAttribute("map", map);
        return "business-menu/gongjijin-manager/gongJiJinList";
    }

    //进入公积金缴费页面
    @RequestMapping("/gongJiJinAddJsp")
    public String gongJiJinAddJsp(){
        return "business-menu/gongjijin-manager/gongJiJinAdd";
    }

    //添加公积金信息
    @RequestMapping("/addAccumulation")
    public String addAccumulation(TbAccumulationFund tbAccumulationFund){
        tbAccumulationFund.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tbAccumulationFundService.addAccumulation(tbAccumulationFund);
        return "redirect:selectAccumulationByDuo";
    }

    //根据id查询公积金信息后进入查询页面或修改页面
    @RequestMapping("/selectAccumulationById")
    public String selectAccumulationById(int id,int flag,Model model){
        TbAccumulationFund tbAccumulationFund = tbAccumulationFundService.selectAccumulationById(id);
        model.addAttribute("tbAccumulationFund",tbAccumulationFund);
        if(flag==1){        //进入查看页面
            return "business-menu/gongjijin-manager/gongJiJinSelect";
        }else {             //进入修改页面（flag=2）
            return "business-menu/gongjijin-manager/gongJiJinUpdate";
        }
    }

    //修改公积金信息
    @RequestMapping("/updateAccumulationById")
    public String updateAccumulationById(TbAccumulationFund tbAccumulationFund){
        TbAccumulationFund tbAccumulationFund1 = tbAccumulationFundService.selectAccumulationById(tbAccumulationFund.getId());
        Timestamp time = tbAccumulationFund1.getCreateTime();
        tbAccumulationFund.setCreateTime(time);
        tbAccumulationFund.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        tbAccumulationFundService.updateAccumulationById(tbAccumulationFund);
        return "redirect:selectAccumulationByDuo";
    }

    //根据id删除公积金信息
    @RequestMapping("/deleteAccumulationById")
    public String deleteAccumulationById(int id){
        tbAccumulationFundService.deleteAccumulationById(id);
        return "redirect:selectAccumulationByDuo";
    }

    //公积金模板下载
    @RequestMapping("/accumulationModleDownload")
    public void accumulationModleDownload(String name, HttpServletRequest request, HttpServletResponse response){
        try {
            tbAccumulationFundService.AccumulationModleDownload(name, request, response);
        } catch (Exception e) {
            System.out.println("模板下载错误");
            e.printStackTrace();
        }
    }

    //公积金数据导入
    @RequestMapping("/accumulationUpload")
    public String accumulationUpload(MultipartFile file, HttpSession session)throws IOException,InvalidFormatException {
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        tbAccumulationFundService.AccumulationUpload(file.getInputStream(),tbSystemUser.getId());
        return "redirect:selectAccumulationByDuo";
    }

    //公积金数据导出
    @RequestMapping("/accumulationDownload")
    public void accumulationDownload(String idCard,String accountNo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Map map = new HashMap();
        map.put("idCard", idCard);
        map.put("accountNo", accountNo);
        tbAccumulationFundService.AccumulationDownload(map,response,request);
    }

    //公积金账号唯一验证
    @RequestMapping("/accountNoIsOne")
    @ResponseBody
    public boolean accountNoIsOne(String accountNo){
        TbAccumulationFund tbAccumulationFund = tbAccumulationFundService.accountNoIsOne(accountNo);
        boolean bo = true;      //账号唯一
        if(tbAccumulationFund!=null && tbAccumulationFund.getId()!=0){
            bo=false;   //账号不唯一
        }else {
            bo=true;
        }
        return bo;
    }

    //根据name查询公积金信息
    @RequestMapping("/selectAccumulationByName")
    @ResponseBody
    public TbAccumulationFund selectAccumulationByName(String name){
        TbAccumulationFund tbAccumulationFund = tbAccumulationFundService.selectAccumulationByName(name);
        return tbAccumulationFund;
    }

    //进入公积金催缴页面
    @RequestMapping("/gongJiJinSendSms")
    public String gongJiJinSendSms(){
        return "business-menu/gongjijin-manager/gongJiJinSendSms";
    }


    //查询所有客户需缴公积金
    @RequestMapping("/selectAllGongjijinByIdCard")
    @ResponseBody
    public List<TbSalary> selectAllGongjijinByIdCard(){
        List<TbSalary> tbSalaries = tbSalaryService.selectAllGongjijin();
        return tbSalaries;
    }

    //查询没有缴纳公积金客户的姓名和电话号码
    @RequestMapping("/gongJiJinByStatusForPhone")
    @ResponseBody
    public List gongJiJinByStatusForPhone(){
        List list = tbAccumulationFundService.gongJiJinByStatusForPhone();
        return list;
    }


}
