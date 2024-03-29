package com.hrpms.dao.business_menu_dao.gongjijin_manger_dao;

import com.hrpms.pojo.TbAccumulationFund;

import java.util.List;
import java.util.Map;

public interface TbAccumulationFundDao {
    //动态模糊分页查询公积金信息
    public List<TbAccumulationFund> selectAccumulationByDuo(String hql, Map map);

    //动态查询总条数
    public Long selectAccumulationCount(String hql,Map map);

    //添加公积金信息
    public void addAccumulation(TbAccumulationFund tbAccumulationFund);

    //根据id查询公积金信息
    public TbAccumulationFund selectAccumulationById(int id);

    //根据id修改公积金信息
    public void updateAccumulationById(TbAccumulationFund tbAccumulationFund);

    //根据id删除公积金信息
    public void deleteAccumulationById(int id);

    //动态模糊不分页查询
    public List<TbAccumulationFund> selectAccumulationByNoFen(String hql, Map map);

    //公积金账号唯一验证
    public TbAccumulationFund accountNoIsOne(String accountNo);

    /**
     * 根据idCard查询公积金详细信息
     * @param
     * @return
     **/
    public TbAccumulationFund getAccumulationFundByIdCard(String hql, String idCard);

    //根据name查询公积金信息
    public TbAccumulationFund selectAccumulationByName(String name);

    //查询所有未缴纳公积金的信息
    public List<TbAccumulationFund> gongJiJinByStatusForPhone();
}
