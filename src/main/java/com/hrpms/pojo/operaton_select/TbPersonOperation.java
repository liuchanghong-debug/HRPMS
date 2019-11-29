package com.hrpms.pojo.operaton_select;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.pojo.operaton_select > TbPersonOperation
 * @description TODO
 * @create 2019/11/26  15:02
 * @versiion 1.0
 * @Description:
 */
public class TbPersonOperation {
    private String nameQuery;
    private String idCardQuery;
    private String jobInterentsionQuery;
    private String forAddressQuery;

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

    public String getJobInterentsionQuery() {
        return jobInterentsionQuery;
    }

    public void setJobInterentsionQuery(String jobInterentsionQuery) {
        this.jobInterentsionQuery = jobInterentsionQuery;
    }

    public String getForAddressQuery() {
        return forAddressQuery;
    }

    public void setForAddressQuery(String forAddressQuery) {
        this.forAddressQuery = forAddressQuery;
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
