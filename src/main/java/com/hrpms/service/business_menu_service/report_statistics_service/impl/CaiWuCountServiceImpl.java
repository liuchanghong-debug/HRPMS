package com.hrpms.service.business_menu_service.report_statistics_service.impl;

import com.hrpms.dao.business_menu_dao.report_statistics_dao.CaiWuCountDao;
import com.hrpms.service.business_menu_service.report_statistics_service.CaiWuCountService;
import com.hrpms.utils.DataOutOfExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CaiWuCountServiceImpl implements CaiWuCountService {

    @Autowired
    private CaiWuCountDao caiWuCountDao;

    @Override
    public List<Object[]> selectCaiWuCountByDuo(Map map) {
        StringBuffer sql = new StringBuffer();
        sql.append("");
        if(map.get("startTime")!=null && !"".equals(map.get("startTime"))){
            map.put("","%"+map.get("")+"%");
            sql.append(" ");
        }
        if(map.get("endTime")!=null && !"".equals(map.get("endTime"))){
            map.put("","%"+map.get("")+"%");
            sql.append(" ");
        }

        List<Object[]> list = caiWuCountDao.selectCaiWuCountByDuo(sql.toString(), map);
        return list;
    }

    @Override
    public void CaiWuCountDownload(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Object[]> list = selectCaiWuCountByDuo(map);
        List resultList = new ArrayList();
        for(Object[] salary:list){
            List datelist = new ArrayList();
            datelist.add(salary[0]);
            datelist.add(salary[1]);
            datelist.add(salary[2]);
            datelist.add(salary[3]);
            datelist.add(salary[4]);
            datelist.add(salary[5]);
            datelist.add(salary[6]);
            resultList.add(datelist);
        }

        Map map1 = new HashMap();
        map1.put("fileName","财务报表模板");
        map1.put("dataList",resultList);
        DataOutOfExcel.dataOut(map1,request,response);
    }
}
