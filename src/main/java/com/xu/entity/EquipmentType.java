package com.xu.entity;

/**
 * 实验室器材类型实体类
 * @author mashenglin
 */
public class EquipmentType {
    Long id;
    String Long;

    public EquipmentType(java.lang.Long id, String aLong) {
        this.id = id;
        Long = aLong;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    @Override
    public String toString() {
        return "EquipmentType{" +
                "id=" + id +
                ", Long='" + Long + '\'' +
                '}';
    }
}
