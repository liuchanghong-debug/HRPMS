package com.hrpms.dao.content_manager_dao;

import com.hrpms.pojo.TbNews;

import java.util.List;
import java.util.Map;

public interface TbNewsDao {
    /**
     *  多条件分页查询新闻信息
     * @param hql
     * @param map
     * @return
     */
    List<TbNews>  selectTbNews(String hql, Map map);

    /**
     * 多条件查询新闻的总条数
     * @param hql
     * @param map
     * @return
     */
    long selectTbNewsCount(String hql,Map map);

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
