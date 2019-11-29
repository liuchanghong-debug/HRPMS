package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbNews
 * @description TODO
 * @create 2019/11/19  22:00
 * @versiion 1.0
 * @Description:新闻表
 */
@Entity
public class TbNews {
    private Integer id;//编号  主键
    private String newsTitle;//新闻标题  非空
    private String content;//新闻内容  非空
    private String status;//状态  非空  0 显示  1 隐藏
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp createTime;//创建时间
    private Integer createBy;//创建者
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp updateTime;//更新时间
    private Integer updateBy;//更新者
    private String remark;//备注

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
