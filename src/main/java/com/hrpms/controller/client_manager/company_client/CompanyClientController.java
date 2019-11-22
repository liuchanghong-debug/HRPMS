package com.hrpms.controller.client_manager.company_client;

import com.hrpms.pojo.TbCompany;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbCompanyOperation;
import com.hrpms.service.company_client_service.CompanyClientService;
import com.hrpms.utils.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.controller.client_manager.company_client > CompanyClientController
 * @description TODO
 * @create 2019/11/21  19:15
 * @versiion 1.0
 * @Description:
 */
@Controller
@RequestMapping("/companyClient")
public class CompanyClientController {
    @Autowired
    private CompanyClientService companyClientService;
    /**
     * 多条件分页查询公司客户
     * @param
     * @return
     **/
    @RequestMapping("/companyClientList")
    public String companyClientList(@RequestParam(defaultValue = "1") Integer currentPage, TbCompanyOperation companyOperation, Model model){
        Page<TbCompany> page = companyClientService.getCompanyByOperation(currentPage, companyOperation);

        model.addAttribute("page", page);
        model.addAttribute("companyOperation", companyOperation);

        return "business-menu/client-manager/company-client/companyList";
    }

    /**
     * 公司客户添加页面
     * @param 
     * @return 返回添加页面
     **/
    @RequestMapping("/companyAdd")
    public String companyAdd(Model model){
        //需要公司客户状态  和  公司类别  从字典表中获取
        model.addAttribute("companyStatuss", companyClientService.getDataDictByName("公司客户状态"));
        model.addAttribute("companyTypes", companyClientService.getDataDictByName("公司类别"));

        return "business-menu/client-manager/company-client/companyAdd";
    }
    /**
     * 确认添加
     * @param 查询参数
     * @return 返回添加前页面
     **/
    @RequestMapping("/companyToAdd")
    public String companyToAdd(TbCompany tbCompany, HttpSession session, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        tbCompany.setCreateBy(tbSystemUser.getId());
        tbCompany.setCreateTime(new Timestamp(System.currentTimeMillis()));
        companyClientService.companyToAdd(tbCompany);
        return companyClientList(1, new TbCompanyOperation(), model);
    }
    /**
     * 查看详细信息
     * @param
     * @return
     **/
    @RequestMapping("/getCompanyById")
    public String getCompanyById(Integer id, Model model){
        TbCompany company = companyClientService.getCompanyById(id);

        List<TbSystemDict> dictByCompanyType = companyClientService.getDataDictByName("公司类别");
        List<TbSystemDict> dictByCompanyStatus = companyClientService.getDataDictByName("公司客户状态");
        for (TbSystemDict tbSystemDict : dictByCompanyType){
            if(tbSystemDict.getValue().equals(company.getCompanyType())){
                company.setCompanyType(tbSystemDict.getLabel());
            }
        }
        for (TbSystemDict tbSystemDict : dictByCompanyStatus){
            if(tbSystemDict.getValue().equals(company.getStatus())){
                company.setStatus(tbSystemDict.getLabel());
            }
        }
        model.addAttribute("tbCompany", company);
        return "business-menu/client-manager/company-client/companyUpdateDisable";
    }
    /**
     * 修改公司客户信息
     * @param 
     * @return 修改页面
     **/
    @RequestMapping("/companyUpdate")
    public String companyUpdate(Integer id, Integer currentPage, TbCompanyOperation companyOperation, Model model){
        TbCompany company = companyClientService.getCompanyById(id);
        List<TbSystemDict> companyTypes = companyClientService.getDataDictByName("公司类别");
        List<TbSystemDict> companyStatus = companyClientService.getDataDictByName("公司客户状态");

        model.addAttribute("company", company);
        model.addAttribute("companyTypes", companyTypes);
        model.addAttribute("companyStatus", companyStatus);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("companyOperation", companyOperation);

        return "business-menu/client-manager/company-client/companyUpdate";
    }
    /**
     * 确认修改
     * @param 
     * @return
     *
     **/
    @RequestMapping("/companyUpdateToUpdate")
    public String companyToUpdate(Integer currentPage, TbCompanyOperation companyOperation, TbCompany company, HttpSession session, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        company.setUpdateBy(tbSystemUser.getId());
        company.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        companyClientService.companyToUpdate(company);
        return companyClientList(currentPage, companyOperation, model);
    }
    /**
     * 删除
     * @param 
     * @return 
     **/
    @RequestMapping("/companyDelete")
    public String companyDelete(Integer currentPage, TbCompanyOperation companyOperation, Integer id, HttpSession session, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        companyClientService.companyDelete(id, tbSystemUser.getId());
        return companyClientList(currentPage, companyOperation, model);
    }
    /**
     * 模板下载
     * @param
     * @return
     **/
    @RequestMapping("/templateDownload")
    public void templateDownload(String name, HttpServletRequest request, HttpServletResponse response){
        try {
            companyClientService.templateDownload(name, request, response);
        } catch (Exception e) {
            System.out.println("模板下载错误");
            e.printStackTrace();
        }
    }
    /**
     * 数据导出
     * @param 
     * @return 
     **/
    @RequestMapping("/getCompanyByOperationNoPagingOutOfExcel")
    public void getCompanyByOperationNoPagingOutOfExcel(TbCompanyOperation operation, HttpServletRequest request, HttpServletResponse response) throws Exception {
        companyClientService.getCompanyByOperationNoPaging(operation, request, response);
    }
    /**
     * 上传文件
     * @param
     * @return
     **/
    @RequestMapping("/fileUpload")
    public String fileUpload(MultipartFile file, HttpSession session, Model model) throws IOException, InvalidFormatException {
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        companyClientService.fileUpload(file.getInputStream(), tbSystemUser.getId());
        return companyClientList(1, new TbCompanyOperation(), model);
    }
}
