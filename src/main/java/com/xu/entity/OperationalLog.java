package com.xu.entity;

/**
 *器材操作日志实体类
 * @author Alkmg
 */
public class OperationalLog {
    private Long id;
    private String username;
    private Long equipmentId;
    private Long createTime;
    private Integer operationalId;
    private String remark;

    @Override
    public String toString() {
        return "OperationalLog{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", equipmentId=" + equipmentId +
                ", createTime=" + createTime +
                ", operationalId=" + operationalId +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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
}
