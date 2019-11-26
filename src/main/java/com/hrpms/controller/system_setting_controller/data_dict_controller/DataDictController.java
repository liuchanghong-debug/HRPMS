package com.hrpms.controller.system_setting_controller.data_dict_controller;

import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.pojo.operaton_select.TbSystemDictOperation;
import com.hrpms.service.system_setting_service.data_dict_service.DataDictService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.controller.system_setting_controller.data_dict_controller > DataDictController
 * @description TODO
 * @create 2019/11/21  14:22
 * @versiion 1.0
 * @Description:
 */
@Controller
@RequestMapping("/datadict")
public class DataDictController {
    @Autowired
    private DataDictService dataDictService;
    /**
     * 跳转到字典多条件分页展示页
     * @param 
     * @return 
     **/
    @RequestMapping("/datadictList")
    public String datadictList(@RequestParam(defaultValue = "1") Integer currentPage, TbSystemDictOperation tbSystemDictOperation, Model model){
        //默认查询所有非删除的字典数据
        Page<TbSystemDict> tbSystemDicts = dataDictService.getDataDictByOperation(currentPage, tbSystemDictOperation);
        //将数据字典中的字典状态返回给前端
        model.addAttribute("dataStatus", dataDictService.getDataDictByName("字典状态"));
        //查询数据
        model.addAttribute("dataDictOperation", tbSystemDictOperation);
        //结果数据
        model.addAttribute("dataDicts", tbSystemDicts);

        return "system-setting/data-dict/dictList";
    }
    
    /**
     * 跳转到字典添加页面
     * @param 
     * @return 
     **/
    @RequestMapping("/datadictAdd")
    public String datadictAdd(){
        return "system-setting/data-dict/dictAdd";
    }
    /**
     * 添加字典数据
     * @param 
     * @return 
     **/
    @RequestMapping("/datadictAddToDB")
    public String datadictAddDB(TbSystemDict tbSystemDict, HttpSession session, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) session.getAttribute("tbSystemUser");
        dataDictService.dataDictAdd(tbSystemDict, tbSystemUser);

        return datadictList(1, new TbSystemDictOperation(), model);
    }
    /**
     * 根据id查询
     * @param 
     * @return 
     **/
    @RequestMapping("/getTbSystemDictById")
    public String getSystemDictById(Integer id, Model model){
        TbSystemDict dataDictById = dataDictService.getDataDictById(id);
        List<TbSystemDict> dataDictStatus = dataDictService.getDataDictByName("字典状态");
        for (TbSystemDict dataDict : dataDictStatus){
            if(dataDict.getValue().equals(dataDictById.getStatus())){
                dataDictById.setStatus(dataDict.getLabel());
            }
        }
        model.addAttribute("dataDict", dataDictById);
        return "system-setting/data-dict/dictUpdateDisable";
    }
    /**
     * 修改页面
     * @param 
     * @return 
     **/
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Integer currentPage, TbSystemDictOperation tbSystemDictOperation, Model model){
        TbSystemDict dataDictById = dataDictService.getDataDictById(id);
        List<TbSystemDict> dataDictStatus = dataDictService.getDataDictByName("字典状态");

        model.addAttribute("dataDictById", dataDictById);
        model.addAttribute("dataDictStatus", dataDictStatus);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("dataDictOperation", tbSystemDictOperation);

        return "system-setting/data-dict/dictUpdate";
    }
    /**
     * 确定修改
     * @param
     * @return
     **/
    @RequestMapping("/dictUpdate")
    public String dictUpdate(TbSystemDict tbSystemDict, TbSystemDictOperation dataDictOperation, Integer currentPage, HttpSession httpSession, Model model){
        TbSystemUser tbSystemUser = (TbSystemUser) httpSession.getAttribute("tbSystemUser");
        tbSystemDict.setUpdateBy(tbSystemUser.getId());
        tbSystemDict.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        dataDictService.dataDictUpdate(tbSystemDict);
        return datadictList(currentPage, dataDictOperation, model);
    }
    /**
     * 删除
     * @param 
     * @return 
     **/
    @RequestMapping("/dictDelete")
    public String dictDelete(Integer id, Integer currentPage, TbSystemDictOperation dataDictOperation, Model model){
        dataDictService.dataDictDelete(id);
        return datadictList(currentPage, dataDictOperation, model);
    }
}
