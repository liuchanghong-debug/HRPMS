package com.hrpms.dao.business_menu_dao.report_statistics_dao;

import java.util.List;
import java.util.Map;

public interface SalaryCountDao {

    //动态模糊查询工资费用
    public List<Object[]> selectSalaryCountByDuo(String sql,Map map);
}
