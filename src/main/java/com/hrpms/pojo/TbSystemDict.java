package com.hrpms.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author GoldFish
 * @package hrpms > com.hrpms.pojo > TbSystemDict
 * @description TODO
 * @create 2019/11/19  21:18
 * @versiion 1.0
 * @Description:数据字典表
 */
@Entity
@DynamicUpdate
@DynamicInsert
public class TbSystemDict {
    private Integer id;//编号  主键
    private String name;//字典名称  非空
    private String value;//存储值  非空
    private String label;//显示值
    private String description;//描述
    private Integer sort;//排序
    private Integer parentId;//父id
    private String status;//状态  0 正常  1 删除
    private Integer createBy;//创建者
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp createTime;//创建时间
    private Integer updateBy;//更新者
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss.SSS")
    private Timestamp updateTime;//更新时间


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, nullable = false)
    public Integer getId() {
        return id;
    }
    @Column(length = 50, nullable = false)
    public String getName() {
        return name;
    }
    @Column(length = 50, nullable = false)
    public String getValue() {
        return value;
    }
    @Column(length = 100)
    public String getLabel() {
        return label;
    }
    @Column(length = 100)
    public String getDescription() {
        return description;
    }
    @Column(length = 11)
    public Integer getSort() {
        return sort;
    }
    @Column(length = 11)
    public Integer getParentId() {
        return parentId;
    }
    @Column(length = 2)
    public String getStatus() {
        return status;
    }
    @Column(length = 11)
    public Integer getCreateBy() {
        return createBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
    @Column(length = 11)
    public Integer getUpdateBy() {
        return updateBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }


    public void setId(Integer id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return "TbSystemDict{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", sort=" + sort +
                ", parentId=" + parentId +
                ", status='" + status + '\'' +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
