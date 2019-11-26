package com.hrpms.pojo.operaton_select;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.pojo.operaton_select > TbNeedJobOperation
 * @description TODO
 * @create 2019/11/25  19:53
 * @versiion 1.0
 * @Description:
 */
public class TbNeedJobOperation {
    private String jobNameQuery;
    private String jobTypeQuery;
    private String industryQuery;
    private Integer companyIdQuery;

    private Integer startIndex;
    private Integer pageSize;

    public String getJobNameQuery() {
        return jobNameQuery;
    }

    public String getJobTypeQuery() {
        return jobTypeQuery;
    }

    public String getIndustryQuery() {
        return industryQuery;
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

    public void setJobNameQuery(String jobNameQuery) {

        this.jobNameQuery = jobNameQuery;
    }

    public void setJobTypeQuery(String jobTypeQuery) {
        this.jobTypeQuery = jobTypeQuery;
    }

    public void setIndustryQuery(String industryQuery) {
        this.industryQuery = industryQuery;
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
