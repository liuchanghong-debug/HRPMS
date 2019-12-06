package com.hrpms.service.system_setting_service.menu_manager_service.impl;

import com.hrpms.dao.system_setting_dao.menu_manager_dao.TbSystemFunctionDao;
import com.hrpms.pojo.TbSystemFunction;
import com.hrpms.service.system_setting_service.menu_manager_service.TbSystemFunctionService;
import com.hrpms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TbSystemFunctionServiceImpl implements TbSystemFunctionService{

    @Autowired
    private TbSystemFunctionDao tbSystemFunctionDao;

    @Override
    public List selectAllSystemFunctionName() {
        List<TbSystemFunction> tbSystemFunctions = tbSystemFunctionDao.selectAllSystemFunctionName();
        List list = new ArrayList();
        for(TbSystemFunction function:tbSystemFunctions){
            Map map = new HashMap();
            map.put("id",function.getId());
            map.put("pId",function.getParentId());
            map.put("name",function.getFuncName());
            list.add(map);
        }
        return list;
    }

    @Override
    public TbSystemFunction selectFunctionById(int id) {
        return tbSystemFunctionDao.selectSystemFunctionById(id);
    }

    @Override
    public Page<TbSystemFunction> selectSystemFunctionByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbSystemFunction where 1=1 ");
        hql2.append("from TbSystemFunction where 1=1 ");
        if(map.get("funcName")!=null && !"".equals(map.get("funcName"))){
            map.put("funcName","%"+map.get("funcName")+"%");
            hql1.append("and funcName like :funcName ");
            hql2.append("and funcName like :funcName ");
        }
        Long count = tbSystemFunctionDao.selectSystemFunctionCount(hql1.toString(),map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbSystemFunction> tbSystemFunctions =tbSystemFunctionDao.selectSystemFunctionByDuo(hql2.toString(), map);
        List list = new ArrayList();
        for(TbSystemFunction function:tbSystemFunctions){
            TbSystemFunction function1 = tbSystemFunctionDao.selectSystemFunctionById(function.getId());
            Map map1 = new HashMap();
            map1.put("function",function);
            map1.put("parentName",function1.getFuncName());
            list.add(map1);
        }
        page.setDataList(list);
        return page;
    }

    @Override
    public void deleteSystemFunctionById(int id) {
        tbSystemFunctionDao.deleteSystemFunctionById(id);
    }
}
