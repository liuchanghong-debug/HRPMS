package com.hrpms.service.gongjijin_manager_service.impl;

import com.hrpms.dao.gongjijin_manger_dao.TbAccumulationFundDao;
import com.hrpms.pojo.TbAccumulationFund;
import com.hrpms.service.gongjijin_manager_service.TbAccumulationFundService;
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
public class TbAccumulationFundServiceImpl implements TbAccumulationFundService {
    @Autowired
    private TbAccumulationFundDao tbAccumulationFundDao;

    @Override
    public Page<TbAccumulationFund> selectAccumulationByDuo(Integer currentPage, Map map) {
        int pageSize = 3;
        StringBuffer hql1 = new StringBuffer();
        StringBuffer hql2 = new StringBuffer();
        hql1.append("select count(1) from TbAccumulationFund where 1=1 ");
        hql2.append("from TbAccumulationFund where 1=1 ");
        if(map.get("idCard")!=null && !"".equals(map.get("idCard"))){
            map.put("idCard","%"+map.get("idCard")+"%");
            hql1.append("and idCard like :idCard ");
            hql2.append("and idCard like :idCard ");
        }
        if(map.get("accountNo")!=null && !"".equals(map.get("accountNo"))){
            map.put("accountNo","%"+map.get("accountNo")+"%");
            hql1.append("and accountNo like :accountNo ");
            hql2.append("and accountNo like :accountNo ");
        }

        Long count = tbAccumulationFundDao.selectAccumulationCount(hql1.toString(),map);
        Page page = new Page(currentPage,pageSize,count);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        List<TbAccumulationFund> list =tbAccumulationFundDao.selectAccumulationByDuo(hql2.toString(), map);
        page.setDataList(list);
        return page;
    }

    @Override
    public void addAccumulation(TbAccumulationFund tbAccumulationFund) {
        tbAccumulationFundDao.addAccumulation(tbAccumulationFund);
    }

    @Override
    public TbAccumulationFund selectAccumulationById(int id) {
        TbAccumulationFund tbAccumulationFund = tbAccumulationFundDao.selectAccumulationById(id);
        return tbAccumulationFund;
    }

    @Override
    public void updateAccumulationById(TbAccumulationFund tbAccumulationFund) {
        tbAccumulationFundDao.updateAccumulationById(tbAccumulationFund);
    }

    @Override
    public void deleteAccumulationById(int id) {
        tbAccumulationFundDao.deleteAccumulationById(id);
    }

    @Override
    public void AccumulationModleDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Download.fileTemplateDownload(name, request, response);
    }

    @Override
    public void AccumulationUpload(InputStream fin, Integer createBy) throws IOException, InvalidFormatException {
        List<Map<Object,Object>> list = ExcelUpload.parseExcel(fin);
        for(int i=0;i<list.size();i++) {
            TbAccumulationFund accumulation = new TbAccumulationFund();
            Map map = list.get(i);
            accumulation.setName((String) map.get("客户名称"));
            accumulation.setIdCard((String) map.get("身份证号"));
            accumulation.setAccountNo((String) map.get("公积金账号"));
            accumulation.setPayDate( (String) map.get("缴费日期"));
            accumulation.setPayMoney(Double.valueOf(map.get("缴费金额").toString()));
            accumulation.setProxyFee(Double.valueOf(String.valueOf(map.get("代理费"))));
            accumulation.setStatus((String) map.get("状态"));
            accumulation.setRemark((String) map.get("备注"));
            accumulation.setCreateBy(createBy);
            accumulation.setCreateTime(new Timestamp(System.currentTimeMillis()));
            addAccumulation(accumulation);
        }
    }

    @Override
    public void AccumulationDownload(Map map, HttpServletResponse response, HttpServletRequest request) throws Exception {
        StringBuffer hql = new StringBuffer("from TbAccumulationFund where 1=1 ");
        if(map.get("idCard")!=null && !"".equals(map.get("idCard"))){
            map.put("idCard","%"+map.get("idCard")+"%");
            hql.append("and idCard like :idCard ");
        }
        if(map.get("accountNo")!=null && !"".equals(map.get("accountNo"))){
            map.put("accountNo","%"+map.get("accountNo")+"%");
            hql.append("and accountNo like :accountNo ");
        }
        List<TbAccumulationFund> tbAccumulationFunds = tbAccumulationFundDao.selectAccumulationByNoFen(hql.toString(), map);
        List resultList = new ArrayList();
        for(TbAccumulationFund accumulation:tbAccumulationFunds){
            List datelist = new ArrayList();
            datelist.add(accumulation.getName());
            datelist.add(accumulation.getIdCard());
            datelist.add(accumulation.getAccountNo());
            datelist.add(accumulation.getPayDate());
            datelist.add(accumulation.getPayMoney());
            datelist.add(accumulation.getProxyFee());
            datelist.add(accumulation.getStatus());
            datelist.add(accumulation.getRemark());

            resultList.add(datelist);
        }

        Map map1 = new HashMap();
        map1.put("fileName","客户公积金模板");
        map1.put("dataList",resultList);
        DataOutOfExcel.dataOut(map1,request,response);
    }
}
