package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSmsRecord
 * @description TODO
 * @create 2019/11/19  21:54
 * @versiion 1.0
 * @Description:短信发件箱表
 */
@Entity
public class TbSmsRecord {
    private Integer id;//短信编号  主键
    private Integer user_id;//发送人  非空
    private String telephone;//接收人电话  非空
    private String content;//短信内容  非空
    private Timestamp sendtime;//发送时间  非空

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }


    public String getTelephone() {
        return telephone;
    }

    public String getContent() {
        return content;
    }


    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public Timestamp getSendtime() {
        return sendtime;
    }

    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
