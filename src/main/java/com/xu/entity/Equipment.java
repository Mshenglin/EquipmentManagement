package com.xu.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 实验室器材实体类
 * @author mashenglin
 */
public class Equipment implements Serializable {
    /**
     * 器材id
     */
    private Long id;
    /**
     * 器材编号
     */
    private String code;
    /**
     * 器材名称
     */
    private String name;
    /**
     * 价格
     */
    private Double price;
    /**
     * 部门
     */
    private String department;



    /**
     * 描述
     */
    private String description;
    /**
     * 负责人id
     */
    private Integer leaderId;
    /**
     * 器材类型id
     */
    private Integer equipmentTypeId;

    /**
     * 器材状态
     */
    private Integer equipmentStatus;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;

    public Equipment() {
    }

    public Equipment(Long id, String code, String name, Double price, Integer leaderId, Integer equipmentTypeId, String department, Long createTime, Long updateTime) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.leaderId = leaderId;
        this.equipmentTypeId = equipmentTypeId;
        this.department = department;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getEquipmentTypeId() {
        return equipmentTypeId;
    }

    public void setEquipmentTypeId(Integer equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getName(), getPrice(), getLeaderId(), getEquipmentTypeId(), getDepartment(), getCreateTime(), getUpdateTime());
    }

    public Integer getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(Integer equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", department='" + department + '\'' +
                ", description='" + description + '\'' +
                ", leaderId=" + leaderId +
                ", equipmentTypeId=" + equipmentTypeId +
                ", equipmentStatus=" + equipmentStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
