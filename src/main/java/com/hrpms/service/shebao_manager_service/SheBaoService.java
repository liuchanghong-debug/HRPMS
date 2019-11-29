package com.hrpms.service.shebao_manager_service;

import com.hrpms.pojo.TbCustomer;
import com.hrpms.pojo.TbSocialInsurance;
import com.hrpms.pojo.TbSocialInsuranceRecord;
import com.hrpms.pojo.TbSystemDict;
import com.hrpms.pojo.operaton_select.TbSocialInsuranceOperation;
import com.hrpms.utils.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.service.shebao_manager_service > SheBaoService
 * @description TODO
 * @create 2019/11/23  16:32
 * @versiion 1.0
 * @Description:
 */
public interface SheBaoService {
    /**
     * 社保信息多条件查询
     * @param
     * @return
     **/
    Page<TbSocialInsurance> socialInsuranceQueryByOperation(Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation);
    /**
     * 社保详细信息展示
     * @param 
     * @return 
     **/
    TbSocialInsurance getSheBaoById(Integer id);
    /**
     * 社保信息修改
     * @param
     * @return
     **/
    void shebaoUpdate(TbSocialInsurance socialInsurance, Integer updateBy);
    /**
     * 社保信息删除  置删除状态
     * @param
     * @return
     **/
    void shebaoDelete(Integer id, Integer updateBy);
    /**
     * 根据身份正得到客户详细信息
     * @param
     * @return
     **/
    TbCustomer getCustomerByIdCard(String idCard);
    /**
     * 得到公司信息
     * @param 
     * @return 
     **/
    List<Object[]> getCompanys();
    

    /**
     * 社保信息添加
     * @param 
     * @return 
     **/
    void shebaoAdd(TbSocialInsurance socialInsurance, Integer createBy);
    /**
     * 社保缴费添加
     * @param
     * @return
     **/
    void shebaoRecordAdd(TbSocialInsuranceRecord socialInsuranceRecord, Integer createBy);
    /**
     * 获取社保信息中的所有可用的客户身份证  并查询到 客户信息中不包含可用id 的客户信息
     * @param 
     * @return 
     **/
    List<Map> getNotInSociaOfCustomer();
    /**
     * 得到详细的客户信息
     * @param 
     * @return 
     **/
    Map getDetailOfCustomerById(Integer id);
    /**
     * 获取字典中社保状态的值
     * @param
     * @return
     **/
    List<TbSystemDict> getDictBySheBao();
    /**
     * 获取社保记录表中已存在的所有正常状态的社保卡ID
     * @param
     * @return
     **/
    List<String> getSbCardBySocialInsuranceRecord();
    /**
     * 根据正常状态的id查询社保信息表中的字段，根据表中的字段查出
     * @param 
     * @return 
     **/
    List<TbSocialInsurance> getNotPayOfTbSocialInsurance();
    /**
     * 根据List集合查询不属于集合内容的社保信息  排除已有的社保卡信息
     * @param
     * @return
     **/
    List<TbSocialInsurance> getNotInListBySbCard(List<String> sbCards);
    /**
     * 社保缴费详细信息查询
     * @param
     * @return
     **/
    Map shebaoRecordDetailMess(Integer id);
    /**
     * 根据id查询socialIncuranceRecord信息
     * @param
     * @return
     **/
    TbSocialInsuranceRecord getSheBaoRecordById(Integer id);
    /**
     * 社保信息修改
     * @param
     * @return
     **/
    void shebaoRecordUpdate(TbSocialInsuranceRecord socialInsuranceRecord, Integer updateBy);

    /**
     * 根据sbCard查询详细信息，包含 customer, socialInsurance, company
     * @param
     * @return
     **/
    Map getDetailMessBySocialInsuranceId(Integer id);
    /**
     * 查询社保缴费状态
     * @param
     * @return
     **/
    List<TbSystemDict>  getSheBaoPayStatus();
    /**
     * 查询社保缴费状态信息
     * @param
     * @return
     **/
    List<TbSystemDict> getDictBySheBaoPay();
    /**
     * 多条件分页查询社保缴费信息
     * @param 
     * @return 
     **/
    Page<TbSocialInsuranceRecord> socialInsuranceRecordQueryByOperation(Integer currentPage, TbSocialInsuranceOperation socialInsuranceOperation);
    /**
     * 社保缴费删除
     * @param 
     * @return 
     **/
    void shebaoRecordDelete(Integer id, Integer updateBy);
    /**
     * 模板下载
     * @param 
     * @return 
     **/
    void shebaoTemplateDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * 社保信息导出
     * @param
     * @return
     **/
    void dataOutOfExcel(TbSocialInsuranceOperation socialInsuranceOperation, HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * 社保信息导入
     * @param
     * @return
     **/
    void shebaoInOfExcel(InputStream inputStream, Integer createBy) throws IOException, InvalidFormatException, ParseException;

    /**
     * 社保卡添加是否唯一
     * @param
     * @return
     **/
    boolean sbCardIsOnly(String sbCard);

    /**
     * 社保添加异步查询  社保卡是否唯一  修改  不查自己
     * @param
     * @return
     **/
    boolean shebaoSbCardIsOnlyUpdate(Integer id, String sbCard);


}
