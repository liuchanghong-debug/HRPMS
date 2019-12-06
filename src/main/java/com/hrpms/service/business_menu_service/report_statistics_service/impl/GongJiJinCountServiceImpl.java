package com.hrpms.service.business_menu_service.report_statistics_service.impl;

import com.hrpms.dao.business_menu_dao.report_statistics_dao.GongJiJinCountDao;
import com.hrpms.service.business_menu_service.report_statistics_service.GongJiJinCountService;
import com.hrpms.utils.DataOutOfExcel;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
public class GongJiJinCountServiceImpl implements GongJiJinCountService {

    @Autowired
    private GongJiJinCountDao gongJiJinCountDao;

    @Override
    public List<Object[]> selectGongJiJInCountByDuo(Map map) {
        StringBuffer sql = new StringBuffer();
        sql.append("select tbaccumulationfund.name,tbaccumulationfund.idCard,tbaccumulationfund.accountNo,tbcompany.name companyName,tbaccumulationfund.payDate,tbaccumulationfund.payMoney,tbaccumulationfund.proxyFee,tbaccumulationfund.status from tbaccumulationfund,tbcustomer,tbcompany WHERE tbaccumulationfund.idCard=tbcustomer.idCard and tbcustomer.companyId=tbcompany.id ");
        if(map.get("name")!=null && !"".equals(map.get("name"))){
            map.put("name","%"+map.get("name")+"%");
            sql.append("and tbaccumulationfund.name like :name ");
        }
        if(map.get("idCard")!=null && !"".equals(map.get("idCard"))){
            map.put("idCard","%"+map.get("idCard")+"%");
            sql.append("and tbaccumulationfund.idCard like :idCard ");
        }
        if(map.get("accountNo")!=null && !"".equals(map.get("accountNo"))){
            map.put("accountNo","%"+map.get("accountNo")+"%");
            sql.append("and tbaccumulationfund.accountNo like :accountNo ");
        }
        if(map.get("companyId")!=null && !"".equals(map.get("companyId"))){
            sql.append("and tbcompany.id =:companyId ");
        }
        List<Object[]> list = gongJiJinCountDao.selectGongJiJInCountByDuo(sql.toString(), map);
        DateTimeFormatter df =DateTimeFormat.forPattern("yyyy-MM");
        DateTime end = new DateTime();
        List<Object[]> list1 = new ArrayList<>();
        for(Object[] li:list){
            DateTime start = df.parseDateTime((String)li[4]);
            int months = Months.monthsBetween(start, end).getMonths();
            li[4]=months;
            li[5]=months*(double)li[5];
            li[6]=months*(double)li[6];
            list1.add(li);
        }
        return list1;
    }

    @Override
    public void gongJiJInCountDownload(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Object[]> list = selectGongJiJInCountByDuo(map);
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
            if(salary[7].equals("0")){
                datelist.add("未缴");
            }else {
                datelist.add("已缴");
            }

            resultList.add(datelist);
        }

        Map map1 = new HashMap();
        map1.put("fileName","公积金报表模板");
        map1.put("dataList",resultList);
        DataOutOfExcel.dataOut(map1,request,response);
    }
}
