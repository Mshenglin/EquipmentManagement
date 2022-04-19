package com.xu.entity;

/**
 * 实验室器材类型实体类
 * @author mashenglin
 */
public class EquipmentType {
    Long id;
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EquipmentType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
