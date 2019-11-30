package com.hrpms.dao.report_statistics_dao;

import java.util.List;
import java.util.Map;

public interface CaiWuCountDao {
    //动态模糊查询工资费用
    public List<Object[]> selectCaiWuCountByDuo(String sql, Map map);
}
