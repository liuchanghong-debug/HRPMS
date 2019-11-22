package com.hrpms.pojo.operaton_select;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.pojo.operaton_select > TbSystemDictOperation
 * @description TODO
 * @create 2019/11/21  16:39
 * @versiion 1.0
 * @Description:
 */
public class TbSystemDictOperation {
    private String nameQuery;
    private String statusQuery;

    private Integer startIndexQuery;
    private Integer pageSize;

    public String getNameQuery() {
        return nameQuery;
    }

    public void setNameQuery(String nameQuery) {
        this.nameQuery = nameQuery;
    }

    public String getStatusQuery() {
        return statusQuery;
    }

    public void setStatusQuery(String statusQuery) {
        this.statusQuery = statusQuery;
    }

    public Integer getStartIndexQuery() {
        return startIndexQuery;
    }

    public void setStartIndexQuery(Integer startIndexQuery) {
        this.startIndexQuery = startIndexQuery;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
