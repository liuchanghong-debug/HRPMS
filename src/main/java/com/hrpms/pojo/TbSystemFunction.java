package com.hrpms.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSystemFunction
 * @description TODO
 * @create 2019/11/19  21:15
 * @versiion 1.0
 * @Description:系统权限表
 */
public class TbSystemFunction {
    private Integer id;//id  主键
    private String funcName;//权限名称  非空
    private String funcUrl;//权限链接
    private Integer funcType;//权限类型  0 菜单  1 按钮
    private String funcNote;//权限备注
    private Integer parentId;//权限父id
    private String iconClass;//图标样式
    private String iconUrl;//图标路径
    private Integer sortNum;//排序
    private String status;//状态  0 正常  1 停用
    private Integer createBy;//创建者
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp createTime;//创建时间
    private Integer updateBy;//更新者
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp updateTime;//更新时间

    public Integer getId() {
        return id;
    }

    public String getFuncName() {
        return funcName;
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public Integer getFuncType() {
        return funcType;
    }

    public String getFuncNote() {
        return funcNote;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getIconClass() {
        return iconClass;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl;
    }

    public void setFuncType(Integer funcType) {
        this.funcType = funcType;
    }

    public void setFuncNote(String funcNote) {
        this.funcNote = funcNote;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public void setStatus(String status) {
        this.status = status;
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
