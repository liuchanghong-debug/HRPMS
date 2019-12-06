package com.hrpms.service.business_menu_service.report_statistics_service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface GongJiJinCountService {

    //动态模糊查询公积金
    public List<Object[]> selectGongJiJInCountByDuo(Map map);

    //公积金报表导出
    public void gongJiJInCountDownload(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
