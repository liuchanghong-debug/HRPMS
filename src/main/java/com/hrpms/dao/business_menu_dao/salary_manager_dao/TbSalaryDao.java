package com.hrpms.dao.business_menu_dao.salary_manager_dao;

import com.hrpms.pojo.TbSalary;

import java.util.List;
import java.util.Map;

public interface TbSalaryDao {

    //动态模糊分页查询工资信息
    public List<TbSalary> selectSalaryByDuo(String hql, Map map);

    //动态查询总条数
    public Long selectSalaryCount(String hql, Map map);

    //添加工资信息
    public void addSalary(TbSalary tbSalary);

    //根据id查看工资信息
    public TbSalary selectSalaryById(int id);

    //根据id修改工资信息
    public void updateSalaryById(TbSalary tbSalary);

    //根据id删除工资信息
    public void deleteSalaryById(int id);

    //动态模糊不分页查询
    public List<TbSalary> selectSalaryByNoFen(String hql,Map map);

    /**
     * 根据idCard查询工资信息
     * @param
     * @return
     **/
    Double getSalaryByIdCard(String hql);


    /**
     * 根据idcard 查询所有
     * @param
     * @return
     **/
    TbSalary getTbSalaryByIdCard(String hql, String idCard);

    //银行卡号唯一验证
    public TbSalary payCardIsOne(String payCard);

    //根据name查询所有
    public TbSalary selectSalaryByName(String name);

}
