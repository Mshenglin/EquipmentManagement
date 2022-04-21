package com.xu.entity;

/**
 * 器材类型操作日志记录实体类
 * @author Alkmg
 */
public class OperationalTypeLog {
    private Long id;
    private String username;
    private  Long equipmentTypeId;
    private Long createTime;
    private Integer operationalId;
    private String remark;

    @Override
    public String toString() {
        return "OperationalTypeLog{" +
                "username='" + username + '\'' +
                ", equipmentTypeId=" + equipmentTypeId +
                ", CreatTime=" + createTime +
                ", operationalId=" + operationalId +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getEquipmentTypeId() {
        return equipmentTypeId;
    }

    public void setEquipmentTypeId(Long equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long creatTime) {
        this.createTime = creatTime;
    }

    public Integer getOperationalId() {
        return operationalId;
    }

    public void setOperationalId(Integer operationalId) {
        this.operationalId = operationalId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
