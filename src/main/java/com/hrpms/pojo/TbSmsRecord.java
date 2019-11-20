package com.hrpms.pojo;

import java.math.BigInteger;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSmsRecord
 * @description TODO
 * @create 2019/11/19  21:54
 * @versiion 1.0
 * @Description:短信发件箱表
 */
public class TbSmsRecord {
    private Integer id;//短信编号  主键
    private Integer userId;//发送人  非空
    private String telephone;//接收人电话  非空
    private String content;//短信内容  非空
    private BigInteger sendTime;//发送时间  非空

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getContent() {
        return content;
    }

    public BigInteger getSendTime() {
        return sendTime;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSendTime(BigInteger sendTime) {
        this.sendTime = sendTime;
    }
}
