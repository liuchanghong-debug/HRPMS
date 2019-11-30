package com.hrpms.service.gongjijin_manager_service;

import com.hrpms.pojo.TbAccumulationFund;
import com.hrpms.utils.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface TbAccumulationFundService {
    //动态模糊分页查询公积金信息
    public Page<TbAccumulationFund> selectAccumulationByDuo(Integer currentPage, Map map);

    //添加公积金信息
    public void addAccumulation(TbAccumulationFund tbAccumulationFund);

    //根据id查询公积金信息
    public TbAccumulationFund selectAccumulationById(int id);

    //根据id修改公积金信息
    public void updateAccumulationById(TbAccumulationFund tbAccumulationFund);

    //根据id删除公积金信息
    public void deleteAccumulationById(int id);

    //公积金模板下载
    public void AccumulationModleDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception;

    //公积金数据导入
    public void AccumulationUpload(InputStream fin, Integer createBy)throws IOException,InvalidFormatException;

    //公积金数据导出
    public void AccumulationDownload(Map map,HttpServletResponse response,HttpServletRequest request) throws Exception;

    //公积金账号唯一验证
    public TbAccumulationFund accountNoIsOne(String accountNo);

    public TbAccumulationFund getAccumulationFundByIdCard(String idCard);
}
