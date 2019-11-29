package com.hrpms.pojo;

/**
 * @author GoldFish
 * @package HRPMS > com.hrpms.pojo > SheBaoCount
 * @description TODO
 * @create 2019/11/29  20:41
 * @versiion 1.0
 * @Description: 社保统计实体类
 */
public class SheBaoCount {
    private Integer id;
    private String name;
    private String idCard;
    private String sbCard;
    private String companyName;
    private String sbMonth;
    private String sbMoneyCount;
    private String cost;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSbCard() {
        return sbCard;
    }

    public void setSbCard(String sbCard) {
        this.sbCard = sbCard;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSbMonth() {
        return sbMonth;
    }

    public void setSbMonth(String sbMonth) {
        this.sbMonth = sbMonth;
    }

    public String getSbMoneyCount() {
        return sbMoneyCount;
    }

    public void setSbMoneyCount(String sbMoneyCount) {
        this.sbMoneyCount = sbMoneyCount;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
