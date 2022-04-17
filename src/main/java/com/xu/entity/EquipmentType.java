package com.xu.entity;

/**
 * 实验室器材类型实体类
 * @author mashenglin
 */
public class EquipmentType {
    Long id;
    String EquipmentTypeName;

    public EquipmentType(java.lang.Long id, String EquipmentTypeName) {
        this.id = id;
        this.EquipmentTypeName = EquipmentTypeName;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public String getEquipmentTypeName() {
        return EquipmentTypeName;
    }

    public void setLong(String aLong) {
        this.EquipmentTypeName = EquipmentTypeName;
    }

    @Override
    public String toString() {
        return "EquipmentType{" +
                "id=" + id +
                ", Long='" + EquipmentTypeName + '\'' +
                '}';
    }
}
