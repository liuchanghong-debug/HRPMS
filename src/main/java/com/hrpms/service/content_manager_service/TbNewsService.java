package com.hrpms.service.content_manager_service;

import com.hrpms.pojo.TbNews;
import com.hrpms.utils.Page;

import java.util.Map;

public interface TbNewsService {
    /**
     *  多条件查询新闻信息
     * @param currentPage
     * @param map
     * @return
     */
    Page<TbNews> selectTbNews(Integer currentPage, Map map);

    /**
     *  添加新闻信息
     * @param news
     * @return
     */
    int saveTbNews(TbNews news);


}
