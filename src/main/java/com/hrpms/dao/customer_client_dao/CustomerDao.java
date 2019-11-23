package com.hrpms.dao.customer_client_dao;

/**
import com.hrpms.pojo.TbCustomer;

import java.util.List;

 * @author GoldFish
 * @package HRPMS > com.hrpms.dao.customer_client_dao > CustomerDao
 * @description TODO
 * @create 2019/11/22  16:56
 * @versiion 1.0
 * @Description:
 */
@Repository
public interface CustomerDao {
    /**
     * 多条件查询
     * @param
     * @return
     **/
    List<TbCustomer> customerAllByOperationAndPaging(String hql, TbCustomerOperation customerOperation);
    /**
     * 条件查询总条数
     * @param
     * @return
     **/
    Long customerCountByOperation(String hql, TbCustomerOperation customerOperation);
    /**
     * 根据id查询个人用户信息
     * @param
     * @return
     **/
    TbCustomer customerById(Integer id);
    /**
     * 添加个人客户
     * @param
     * @return
     **/
    void customerToAdd(TbCustomer tbCustomer);
    /**
     * 修改
     * @param
     * @return
     **/
    void customerUpdate(TbCustomer customer);
    /**
     * 根据条件查询所有数据用于导出
     * @param
     * @return
     **/
    List<TbCustomer> costomerByOperationOutOfExcel(String hql, TbCustomerOperation customerOperation);



    /**
     * 不在集合中的数据对象
     * @param
     * @return
     **/
    List<Object[]> getDataOfNotInList(String hql, List list);
    /**
     * 所有数据  无条件
     * @param
     * @return
     **/
    List<Object[]> getData(String hql);
    /**
     * 根据身份证得到详细信息
     * @param
     * @return
     **/
    TbCustomer getCustomerByIdCard(String hql, String idCard);
    //查询所有客户名称
    public List<TbCustomer> selectAllCustomerName();
}
