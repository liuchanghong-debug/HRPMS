package com.hrpms.controller.salary_manager_controller;

import com.hrpms.pojo.TbSalary;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.salary_manager_service.TbSalaryService;
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
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/salary-manager")
public class TbSalaryController {

    @Autowired
    private TbSalaryService tbSalaryService;

    //动态模糊分页查询工资信息
    @RequestMapping("/selectSalaryByDuo")
    public String selectSalaryByDuo(@RequestParam(defaultValue = "1") Integer currentPage,
                                    String name, String idCard, Model model,int flag) {

        Map map = new HashMap();
        map.put("name", name);
        map.put("idCard", idCard);
        Page<TbSalary> page = tbSalaryService.selectSalaryByDuo(currentPage, map);
        model.addAttribute("page", page);
        map.put("name", name);
        map.put("idCard", idCard);
        model.addAttribute("map", map);
        if (flag == 1) {    //进入工资查询列表页面
            return "business-menu/salary-manager/salaryList";
        }else {             //进入工资条页面（flag=2）
            return "business-menu/salary-manager/salaryPer";
        }

    }

    //进入代发工资页面
    @RequestMapping("/addSalaryJsp")
    public String addSalaryJsp(){
        return "business-menu/salary-manager/salaryAdd";
    }

    //添加工资信息
    @RequestMapping("/addSalary")
    public String assSalary(TbSalary tbSalary){
        tbSalary.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tbSalaryService.addSalary(tbSalary);
        return "redirect:selectSalaryByDuo?flag=1";
    }

    //根据id查询进入查询页面或修改页面
    @RequestMapping("/selectSalaryById")
    public String selectSalaryById(int id,int flag,Model model){
        TbSalary tbSalary = tbSalaryService.selectSalaryById(id);
        model.addAttribute("tbSalary",tbSalary);
        if(flag==1){    // 跳到查看页面
            return "business-menu/salary-manager/salarySelect";
        }else {         //跳到修改页面（flag=2）
            return "business-menu/salary-manager/salaryUpdate";
        }
    }

    //根据id修改页面
    @RequestMapping("/updateSalaryById")
    public String updateSalaryById(TbSalary tbSalary){
        tbSalary.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        tbSalaryService.updateSalaryById(tbSalary);
        return "redirect:selectSalaryByDuo?flag=1";
    }


    //根据id删除工资信息
    @RequestMapping("/deleteSalaryById")
    public String deleteSalaryById(int id){
        tbSalaryService.deleteSalaryById(id);
        return "redirect:selectSalaryByDuo?flag=1";
    }

    //工资模板下载
    @RequestMapping("/salaryModleDownload")
    public void salaryModleDownload(String name, HttpServletRequest request, HttpServletResponse response){
        try {
             tbSalaryService.salaryModleDownload(name, request, response);
        } catch (Exception e) {
            System.out.println("模板下载错误");
            e.printStackTrace();
        }
    }

    //客户工资数据导入
    @RequestMapping("/salaryUpload")
    public String salaryUpload(MultipartFile file, HttpSession session)throws IOException,InvalidFormatException {
        TbSystemUser tbSystemUser = (TbSystemUser)session.getAttribute("tbSystemUser");
        tbSalaryService.salaryUpload(file.getInputStream(),tbSystemUser.getId());
        return "redirect:selectSalaryByDuo?flag=1";
    }

    //客户工资数据导出
    @RequestMapping("/salaryDownload")
    public void salaryDownload(String name, String idCard,HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map map = new HashMap();
        map.put("name", name);
        map.put("idCard", idCard);
        tbSalaryService.salaryDownload(map,response,request);
    }

    //查询所有客户需缴公积金
    @RequestMapping("/selectAllGongjijinByIdCard")
    @ResponseBody
    public List<TbSalary> selectAllGongjijinByIdCard(){
        List<TbSalary> tbSalaries = tbSalaryService.selectAllGongjijin();
        return tbSalaries;
    }

}