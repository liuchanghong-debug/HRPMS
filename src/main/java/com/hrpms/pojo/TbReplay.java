package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbReplay
 * @description TODO
 * @create 2019/11/19  22:03
 * @versiion 1.0
 * @Description:回复表
 */
public class TbReplay {
    private Integer id;//编号  主键
    private Integer messageId;//留言表id  非空
    private String content;//回复内容  非空
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp replayTime;//回复时间
    private Integer replayBy;//回复者
    private String status;//回复状态  0 显示  1 隐藏
    private String remark;//备注

    public Integer getId() {
        return id;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getReplayTime() {
        return replayTime;
    }

    public Integer getReplayBy() {
        return replayBy;
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

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReplayTime(Timestamp replayTime) {
        this.replayTime = replayTime;
    }

    public void setReplayBy(Integer replayBy) {
        this.replayBy = replayBy;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
