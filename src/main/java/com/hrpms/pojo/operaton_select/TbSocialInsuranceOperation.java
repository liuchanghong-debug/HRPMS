package com.hrpms.pojo.operaton_select;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.pojo.operaton_select > TbSocialInsuranceOperation
 * @description TODO
 * @create 2019/11/23  22:24
 * @versiion 1.0
 * @Description:
 */
public class TbSocialInsuranceOperation {
    private String nameQuery;
    private String idCardQuery;
    private String sbCardQuery;

    private Integer startIndex;
    private Integer pageSize;

    public String getNameQuery() {
        return nameQuery;
    }

    public void setNameQuery(String nameQuery) {
        this.nameQuery = nameQuery;
    }

    public String getIdCardQuery() {
        return idCardQuery;
    }

    public void setIdCardQuery(String idCardQuery) {
        this.idCardQuery = idCardQuery;
    }

    public String getSbCardQuery() {
        return sbCardQuery;
    }

    public void setSbCardQuery(String sbCardQuery) {
        this.sbCardQuery = sbCardQuery;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
