package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbEmailTemplate
 * @description TODO
 * @create 2019/11/19  21:58
 * @versiion 1.0
 * @Description:邮件模板表
 */
@Entity
public class TbEmailTemplate {
    private Integer id;//模板编号  主键
    private String subject;//主题  非空
    private String content;//内容  非空
    private Integer order_id;//顺序号  非空
    private Integer createBy;//创建者

    private Timestamp createTime;//创建时间
    private Integer updateBy;//更新者
    private Timestamp updateTime;//更新时间

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }



    public Integer getCreateBy() {
        return createBy;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
