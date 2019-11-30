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

    /**
     *  删除新闻信息
     */
    void deleteTbNews(int id);


    /**
     * 根据id来查询信息
     * @param id
     * @return
     */
    TbNews selectTbNewsById(int id);

    /**
     * 修改新闻信息
     * @param news
     */
    void updateTbNews(TbNews news);


}
