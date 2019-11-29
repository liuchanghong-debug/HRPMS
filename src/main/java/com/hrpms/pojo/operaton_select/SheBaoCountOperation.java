package com.hrpms.pojo.operaton_select;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.pojo.operaton_select > SheBaoCountOperation
 * @description TODO
 * @create 2019/11/29  20:31
 * @versiion 1.0
 * @Description:
 */
public class SheBaoCountOperation {
    private String nameQuery;
    private String idCardQuery;
    private String sbCardQuery;
    private Integer companyIdQuery;

    private Integer startIndex;
    private Integer pageSize;

    public String getNameQuery() {
        return nameQuery;
    }

    public String getIdCardQuery() {
        return idCardQuery;
    }

    public String getSbCardQuery() {
        return sbCardQuery;
    }

    public Integer getCompanyIdQuery() {
        return companyIdQuery;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setNameQuery(String nameQuery) {

        this.nameQuery = nameQuery;
    }

    public void setIdCardQuery(String idCardQuery) {
        this.idCardQuery = idCardQuery;
    }

    public void setSbCardQuery(String sbCardQuery) {
        this.sbCardQuery = sbCardQuery;
    }

    public void setCompanyIdQuery(Integer companyIdQuery) {
        this.companyIdQuery = companyIdQuery;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
