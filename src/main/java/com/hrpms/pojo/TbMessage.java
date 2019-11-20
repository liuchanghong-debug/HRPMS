package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbMessage
 * @description TODO
 * @create 2019/11/19  22:02
 * @versiion 1.0
 * @Description:留言表
 */
public class TbMessage {
    private Integer id;//编号  主键
    private String title;//留言标题  非空
    private String content;//留言内容  非空
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp createTime;//留言时间  非空
    private String status;//留言状态  非空  0 显示  1 隐藏
    private String remark;//备注

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public String getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
