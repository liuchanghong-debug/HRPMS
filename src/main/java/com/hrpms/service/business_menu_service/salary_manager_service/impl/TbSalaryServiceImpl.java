package com.hrpms.service.business_menu_service.salary_manager_service.impl;

import com.hrpms.dao.business_menu_dao.salary_manager_dao.TbSalaryDao;
import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSalary;
import com.hrpms.service.business_menu_service.customer_client_service.CustomerService;
import com.hrpms.service.business_menu_service.salary_manager_service.TbSalaryService;
import com.hrpms.utils.DataOutOfExcel;
import com.hrpms.utils.Download;
import com.hrpms.utils.ExcelUpload;
import com.hrpms.utils.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class TbSalaryServiceImpl implements TbSalaryService {

    @Autowired
    private TbSalaryDao tbSalaryDao;

    @Autowired
    private CustomerService customerService;

    @Override
    public Page<TbSalary> selectSalaryByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbSalary where 1=1 ");
        hql2.append("from TbSalary where 1=1 ");
        if(map.get("name")!=null && !"".equals(map.get("name"))){
            map.put("name","%"+map.get("name")+"%");
            hql1.append("and name like :name ");
            hql2.append("and name like :name ");
        }
        if(map.get("idCard")!=null && !"".equals(map.get("idCard"))){
            map.put("idCard","%"+map.get("idCard")+"%");
            hql1.append("and idCard like :idCard ");
            hql2.append("and idCard like :idCard ");
        }

        Long count = tbSalaryDao.selectSalaryCount(hql1.toString(),map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbSalary> list =tbSalaryDao.selectSalaryByDuo(hql2.toString(), map);
        page.setDataList(list);
        return page;
    }

    @Override
    public void addSalary(TbSalary tbSalary) {
        tbSalaryDao.addSalary(tbSalary);
    }

    @Override
    public TbSalary selectSalaryById(int id) {
        TbSalary tbSalary = tbSalaryDao.selectSalaryById(id);
        return tbSalary;
    }

    @Override
    public void updateSalaryById(TbSalary tbSalary) {
        tbSalaryDao.updateSalaryById(tbSalary);
    }

    @Override
    public void deleteSalaryById(int id) {
        tbSalaryDao.deleteSalaryById(id);
    }

    @Override
    public void salaryModleDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Download.fileTemplateDownload(name, request, response);
    }

    @Override
    public void salaryUpload(InputStream fin,Integer createBy) throws IOException,InvalidFormatException {
        List<Map<Object,Object>> list = ExcelUpload.parseExcel(fin);
        for(int i=0;i<list.size();i++){
            TbSalary salary = new TbSalary();
            Map map = list.get(i);
            salary.setName((String)map.get("客户名称"));
            salary.setIdCard((String)map.get("身份证号"));
            salary.setPayCard((String)map.get("银行卡号"));
            String time = (String)map.get("发放月份");
            Format f = new SimpleDateFormat("yyyy-MM-dd");
            Date da = new Date();
            try {
                da = (Date)f.parseObject(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timestamp ts = new Timestamp(da.getTime());
            salary.setPayDate(ts);
            salary.setBaseSalary(Double.valueOf(map.get("基本工资").toString()));
            salary.setBonusPay(Double.valueOf(String.valueOf(map.get("奖金"))));
            salary.setOverTimePay(Double.valueOf(String.valueOf(map.get("加班费"))));
            salary.setSheBaoPay(Double.valueOf(String.valueOf(map.get("社保"))));
            salary.setGongJiJinPay(Double.valueOf(String.valueOf(map.get("公积金"))));
            salary.setTaxPay(Double.valueOf(String.valueOf(map.get("交税"))));
            salary.setTotalPay(Double.valueOf(String.valueOf(map.get("应发工资"))));
            salary.setMustPay(Double.valueOf(String.valueOf(map.get("实发工资"))));
            salary.setProxyFee(Double.valueOf(String.valueOf(map.get("代理费"))));
            salary.setStatus((String)map.get("状态"));
            salary.setRemark((String)map.get("备注"));
            salary.setCreateBy(createBy);
            salary.setCreateTime(new Timestamp(System.currentTimeMillis()));
            addSalary(salary);
        }

    }

    @Override
    public void salaryDownload(Map map, HttpServletResponse response, HttpServletRequest request) throws Exception {
        StringBuffer hql = new StringBuffer("from TbSalary where 1=1 ");
        if(map.get("name")!=null && !"".equals(map.get("name"))){
            map.put("name","%"+map.get("name")+"%");
            hql.append("and name like :name ");
        }
        if(map.get("idCard")!=null && !"".equals(map.get("idCard"))){
            map.put("idCard","%"+map.get("idCard")+"%");
            hql.append("and idCard like :idCard ");
        }
        List<TbSalary> tbSalaries = tbSalaryDao.selectSalaryByNoFen(hql.toString(), map);
        List resultList = new ArrayList();
        for(TbSalary salary:tbSalaries){
            List datelist = new ArrayList();
            datelist.add(salary.getName());
            datelist.add(salary.getIdCard());
            datelist.add(salary.getPayCard());
            datelist.add(salary.getPayDate());
            datelist.add(salary.getBaseSalary());
            datelist.add(salary.getBonusPay());
            datelist.add(salary.getOverTimePay());
            datelist.add(salary.getSheBaoPay());
            datelist.add(salary.getGongJiJinPay());
            datelist.add(salary.getTaxPay());
            datelist.add(salary.getTotalPay());
            datelist.add(salary.getMustPay());
            datelist.add(salary.getProxyFee());
            if(salary.getStatus().equals("0")){
                datelist.add("已发");
            }else{
                datelist.add("未发");
            }

            datelist.add(salary.getRemark());

            resultList.add(datelist);
        }

        Map map1 = new HashMap();
        map1.put("fileName","客户工资模板");
        map1.put("dataList",resultList);
        DataOutOfExcel.dataOut(map1,request,response);
    }

    @Override
    public Double getSalaryByIdCard(String idCard) {
        String hql = "select baseSalary from TbSalary where idCard = " + idCard;
        return tbSalaryDao.getSalaryByIdCard(hql);
    }

    @Override
    public List<TbSalary> selectAllGongjijin() {
        List<TbCustomer> isGongjijin = customerService.selectAllCustomerName("isGongjijin");
        List<TbSalary> list = new ArrayList<>();
        for(TbCustomer customer:isGongjijin){
            TbSalary tbSalaryByIdCard = getTbSalaryByIdCard(customer.getIdCard());
            list.add(tbSalaryByIdCard);
        }
        return list;
    }

    @Override
    public TbSalary getTbSalaryByIdCard(String idCard) {
        String hql = "from TbSalary where idCard = ?";
        return tbSalaryDao.getTbSalaryByIdCard(hql, idCard);
    }

    @Override
    public TbSalary payCardIsOne(String payCard) {
        return tbSalaryDao.payCardIsOne(payCard);
    }

    @Override
    public TbSalary selectSalaryByName(String name) {
        return tbSalaryDao.selectSalaryByName(name);
    }
}
