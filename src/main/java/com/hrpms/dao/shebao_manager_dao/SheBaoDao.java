package com.hrpms.dao.shebao_manager_dao;

import com.hrpms.pojo.TbSocialInsurance;
import com.hrpms.pojo.TbSocialInsuranceRecord;
import com.hrpms.pojo.operaton_select.TbSocialInsuranceOperation;

import java.util.List;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.shebao_manager_dao > SheBaoDao
 * @description TODO
 * @create 2019/11/23  16:31
 * @versiion 1.0
 * @Description:
 */
public interface SheBaoDao {
    /**
     * 社保信息不分页多条件查询
     * @param
     * @return
     **/
    List<TbSocialInsurance> socialInsuranceQueryByOperation(String hql, TbSocialInsuranceOperation socialInsuranceOperation);
    /**
     * 社保信息多条件分页查询
     * @param
     * @return
     **/
    List socialInsuranceQueryByOperationPaging(String hql, TbSocialInsuranceOperation socialInsuranceOperation);
    /**
     * 社保多条件查询总条数
     * @param
     * @return
     **/
    Long socialInsuranceQueryCountByOperation(String hql, TbSocialInsuranceOperation socialInsuranceOperation);

    /**
     * 获取社保表中的所有包含的可用的客户的身份证号
     * @param 
     * @return 
     **/
    List<String> getUserableInSheBaoOfIdCard(String hql, String dictValue);
    /**
     * 社保信息添加
     * @param
     * @return 
     **/
    void shebaoAdd(TbSocialInsurance socialInsurance);
    /**
     * 社保缴费添加
     * @param
     * @return
     **/
    void shebaoRecordAdd(TbSocialInsuranceRecord socialInsuranceRecord);
    /**
     * 通过编号查询详细信息
     * @param 
     * @return 
     **/
    TbSocialInsurance getSheBaoById(Integer id);
    /**
     * 社保信息修改
     * @param
     * @return
     **/
    void shebaoUpdate(TbSocialInsurance socialInsurance);
    
    /**
     * 社保记录表中所有的社保卡号，用来添加时判断哪些不需要添加社保缴纳信息
     * @param 
     * @return 
     **/
    List<String> getSbCardBySocialInsuranceRecord(String hql);
    /**
     * 查询所有
     * @param 
     * @return 
     **/
    List<TbSocialInsurance> getSocialInsurances(String hql);
    /**
     * 根据集合查询
     * @param 
     * @return 
     **/
    List<TbSocialInsurance> getSocialInsurancesByList(String hql, List<String> sbCards);
    /**
     * 通过社保卡信息查询
     * @param
     * @return
     **/
    /**
     * 根据id查询shebaoRecord
     * @param
     * @return
     **/
    TbSocialInsuranceRecord getSheBaoRecordById(Integer id);
    /**
     * 社保缴费信息修改  删除
     * @param
     * @return
     **/
    void shebaoRecordUpdate(TbSocialInsuranceRecord socialInsuranceRecord);
    /**
     * 根据idCard查询shebaoRecord表中是否有数据
     * @param
     * @return 
     **/
    Object shebaoRecordByIdCard(String idCard);

    /**
     * 社保卡添加是否唯一
     * @param
     * @return
     **/
    Object sbCardIsOnly(String hql, String sbCard);

    /**
     * 社保添加异步查询  社保卡是否唯一  修改  不查自己
     * @param
     * @return
     **/
    Object shebaoSbCardIsOnlyUpdate(String hql, Integer id, String sbCard);

    /**
     * 根据idCard查询社保信息
     * @param
     * @return
     **/
    TbSocialInsurance getSheBaoByIdCard(String hql, String idCard);
    /**
     * 根据idCard查询社保缴费信息
     * @param
     * @return
     **/
    TbSocialInsuranceRecord getSheBaoRecordByIdCard(String hql, String idCard);
}
