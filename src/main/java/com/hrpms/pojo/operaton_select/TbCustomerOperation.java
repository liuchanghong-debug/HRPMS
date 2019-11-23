package com.hrpms.pojo.operaton_select;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.pojo.operaton_select > TbCustomerOperation
 * @description TODO
 * @create 2019/11/23  9:50
 * @versiion 1.0
 * @Description:
 */
public class TbCustomerOperation {
    private String nameQuery;
    private String idCardQuery;
    private Integer companyIdQuery;

    private Integer startIndex;
    private Integer pageSize;



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

    public String getNameQuery() {
        return nameQuery;
    }

    public String getIdCardQuery() {
        return idCardQuery;
    }

    public Integer getCompanyIdQuery() {
        return companyIdQuery;
    }

    public void setNameQuery(String nameQuery) {
        this.nameQuery = nameQuery;
    }

    public void setIdCardQuery(String idCardQuery) {
        this.idCardQuery = idCardQuery;
    }

    public void setCompanyIdQuery(Integer companyIdQuery) {
        this.companyIdQuery = companyIdQuery;
    }
}
