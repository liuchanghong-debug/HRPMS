package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbEmailRecord
 * @description TODO
 * @create 2019/11/19  21:57
 * @versiion 1.0
 * @Description:邮件发件箱表
 */
@Entity
public class TbEmailRecord {
    private Integer id;//邮件编号  主键
    private Integer user_id;//发送人  非空
    private String to_addr;//接收人邮箱  非空
    private String subject;//主题  非空
    private String content;//内容  非空
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp sendTime;//发送时间  非空
    private String status;//状态  非空  1 已发送  -1 发送失败

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTo_addr() {
        return to_addr;
    }

    public void setTo_addr(String to_addr) {
        this.to_addr = to_addr;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {

        this.id = id;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
