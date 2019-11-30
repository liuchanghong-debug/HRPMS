package com.hrpms.dao.report_statistics_dao;

import java.util.List;
import java.util.Map;

public interface GongJiJinCountDao {

    //动态模糊查询公积金
    public List<Object[]> selectGongJiJInCountByDuo(String sql, Map map);
}
