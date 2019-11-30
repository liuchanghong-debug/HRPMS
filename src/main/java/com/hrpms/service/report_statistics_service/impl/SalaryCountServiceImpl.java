package com.hrpms.service.report_statistics_service.impl;

import com.hrpms.dao.report_statistics_dao.SalaryCountDao;
import com.hrpms.service.report_statistics_service.SalaryCountService;
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
public class SalaryCountServiceImpl implements SalaryCountService {

    @Autowired
    private SalaryCountDao salaryCountDao;

    @Override
    public List<Object[]> selectSalaryCountByDuo(Map map) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT tbsalary.name,tbsalary.idCard,tbsalary.payCard,tbcompany.name companyName,TIMESTAMPDIFF(MONTH,payDate,DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%S')) month,tbsalary.mustPay,tbsalary.taxPay,tbsalary.proxyFee,tbsalary.status from tbsalary,tbcustomer,tbcompany where tbcustomer.companyId=tbcompany.id and tbsalary.idCard=tbcustomer.idCard ");
        if(map.get("name")!=null && !"".equals(map.get("name"))){
            map.put("name","%"+map.get("name")+"%");
            sql.append("and tbsalary.name like :name ");
        }
        if(map.get("idCard")!=null && !"".equals(map.get("idCard"))){
            map.put("idCard","%"+map.get("idCard")+"%");
            sql.append("and tbsalary.idCard like :idCard ");
        }
        if(map.get("payCard")!=null && !"".equals(map.get("payCard"))){
            map.put("payCard","%"+map.get("payCard")+"%");
            sql.append("and tbsalary.payCard like :payCard ");
        }
        if(map.get("companyId")!=null && !"".equals(map.get("companyId"))){
            sql.append("and tbcompany.id =:companyId ");
        }
        List<Object[]> list = salaryCountDao.selectSalaryCountByDuo(sql.toString(), map);
        return list;
    }

    @Override
    public void salaryCountDownload(Map map,HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Object[]> list = selectSalaryCountByDuo(map);
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
            datelist.add(salary[7]);
            if(salary[8].equals("0")){
                datelist.add("已发");
            }else {
                datelist.add("未发");
            }

            resultList.add(datelist);
        }

        Map map1 = new HashMap();
        map1.put("fileName","工资报表模板");
        map1.put("dataList",resultList);
        DataOutOfExcel.dataOut(map1,request,response);
    }
}
