package com.hrpms.controller.content_manager;

import com.hrpms.pojo.TbNews;
import com.hrpms.pojo.TbSystemUser;
import com.hrpms.service.content_manager_service.TbNewsService;
import com.hrpms.utils.Page;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/newsManager")
public class TbNewsController {
    @Autowired
    private TbNewsService tbNewsService;
    @RequestMapping("/selectTbNews")
    public String selectTbNews(
            @RequestParam(defaultValue = "1") Integer currentPage,
            String newsTitle, Model model){
        Map map = new HashMap();
        map.put("newsTitle",newsTitle);

        Page<TbNews> page = tbNewsService.selectTbNews(currentPage, map);

        map.put("newsTile",newsTitle);
        model.addAttribute("map",map);
        model.addAttribute("page",page);
        return "business-menu/content-manager/newList";
    }
    @RequestMapping("/toNewAdd")
    public String toNewAdd(){
        return "business-menu/content-manager/newAdd";
    }
    @RequestMapping("/saveTbNews")
    public String saveTbNews(TbNews news, HttpSession session){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        news.setCreateTime(timestamp);

        TbSystemUser tbSystemUser = (TbSystemUser)session.getAttribute("tbSystemUser");
        news.setCreateBy(tbSystemUser.getId());

        tbNewsService.saveTbNews(news);

        return "redirect:selectTbNews";
    }





}
