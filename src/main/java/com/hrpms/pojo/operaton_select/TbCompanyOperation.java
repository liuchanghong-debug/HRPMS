package com.hrpms.pojo.operaton_select;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.pojo.operaton_select > TbCompanyOperation
 * @description TODO
 * @create 2019/11/21  20:36
 * @versiion 1.0
 * @Description:
 */
public class TbCompanyOperation {
    private String nameQuery;
    private String companyNoQuery;
    private String idCardQuery;

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

    public void setNameQuery(String nameQuery) {
        this.nameQuery = nameQuery;
    }

    public String getCompanyNoQuery() {
        return companyNoQuery;
    }

    public void setCompanyNoQuery(String companyNoQuery) {
        this.companyNoQuery = companyNoQuery;
    }

    public String getIdCardQuery() {
        return idCardQuery;
    }

    public void setIdCardQuery(String idCardQuery) {
        this.idCardQuery = idCardQuery;
    }
}
