package com.hrpms.service.content_manager_service.impl;

import com.hrpms.dao.content_manager_dao.TbNewsDao;
import com.hrpms.pojo.TbNews;
import com.hrpms.service.content_manager_service.TbNewsService;
import com.hrpms.utils.Page;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class TbNewsServiceImpl implements TbNewsService {
    @Autowired
    private TbNewsDao tbNewsDao;
    @Override
    public Page<TbNews> selectTbNews(Integer currentPage, Map map) {
        // 1, 定义每页查询的条数
        int pageSize = 3;
        // 2,定义动态的hql来实现查询总条数
        StringBuffer sbhql = new StringBuffer("select count(1) from TbNews where 1=1 ");
        if(map.get("newsTitle")!=null &&  !"".equals(map.get("newsTitle"))){
            map.put("newsTitle","%"+map.get("newsTitle")+"%");
            sbhql.append(" and newsTitle like :newsTitle");
        }
        long count = tbNewsDao.selectTbNewsCount(sbhql.toString(), map);
        // 3,创建page对象，并为map集合赋值
        Page<TbNews> page = new Page<>(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",page.getPageSize());
        // 4,定义分页查询的hql语句
        StringBuffer selhql = new StringBuffer("from TbNews where 1=1 ");
        if(map.get("newsTitle")!=null &&  !"".equals(map.get("newsTitle"))){
            selhql.append(" and newsTitle like :newsTitle");
        }
        List<TbNews> tbNews = tbNewsDao.selectTbNews(selhql.toString(), map);
        page.setDataList(tbNews);
        return page;
    }

    @Override
    public int saveTbNews(TbNews news) {
        return tbNewsDao.saveTbNews(news);
    }

    @Override
    public void deleteTbNews(int id) {
        tbNewsDao.deleteTbNews(id);
    }

    @Override
    public TbNews selectTbNewsById(int id) {
        return tbNewsDao.selectTbNewsById(id);
    }

    @Override
    public void updateTbNews(TbNews news) {
        tbNewsDao.updateTbNews(news);
    }
}
