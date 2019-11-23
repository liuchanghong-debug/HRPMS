package com.hrpms.controller.talent_service_controller;

import com.hrpms.pojo.TbNeedJob;
import com.hrpms.service.talent_service_service.TbNeedJobService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/talent-service")
public class TbNeedJobController {
    @Autowired
    private TbNeedJobService tbNeedJobService;

    //动态模糊分页查询招聘信息
    @RequestMapping("/selectNeedJobByDuo")
    public String selectNeedJobByDuo(@RequestParam(defaultValue = "1") Integer currentPage,
                                     String jobname, String jobType, String industry,
                                     Integer companyId, Model model){
        Map map = new HashMap();
        map.put("jobname",jobname);
        map.put("jobType",jobType);
        map.put("industry",industry);
        map.put("companyId",companyId);
        Page<TbNeedJob> page = tbNeedJobService.selectNeedJobByDuo(currentPage, map);
        model.addAttribute("page",page);
        map.put("jobname",jobname);
        map.put("jobType",jobType);
        model.addAttribute("map",map);
        return "business-menu/talent-service/zhaoPinList";
    }

    //进入添加招聘信息页面
    @RequestMapping("/addNeedJobJsp")
    public String addNeedJobJsp(){
        return "business-menu/talent-service/zhaoPinAdd";
    }


    //添加招聘信息
    @RequestMapping("/addNeedJob")
    public String addNeedJob(TbNeedJob tbNeedJob){
        tbNeedJobService.addNeedJob(tbNeedJob);
        return "redirect:talent-service/selectNeedJobByDuo";
    }

    //根据id查看招聘信息
    @RequestMapping("/selectNeedJobById")
    public String selectNeedJobById(int id,Integer flag,Model model){
        TbNeedJob tbNeedJob = tbNeedJobService.selectNeedJobById(id);
        model.addAttribute("tbNeedJob",tbNeedJob);
        if(flag==1){        //进入查看页面
            return "business-menu/talent-service/zhaoPinSelect";
        }else {             //进入修改页面flag=2
            return "business-menu/talent-service/zhaoPinUpdate";
        }
    }

    //根据id修改招聘信息
    @RequestMapping("/updateNeedJob")
    public String updateNeedJob(TbNeedJob tbNeedJob){
        tbNeedJobService.updateNeedJob(tbNeedJob);
        return "redirect:talent-service/selectNeedJobByDuo";
    }

    //根据id删除招聘信息(实则修改招聘状态)
    @RequestMapping("/deleteNeedJob")
    public String deleteNeedJob(int id){
        tbNeedJobService.deleteNeedJob(id);
        return "redirect:talent-service/selectNeedJobByDuo";
    }

}
