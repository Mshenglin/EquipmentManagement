package com.xu.entity;

/**
 * 返回到前端的实体类
 * @author Alkmg
 */
public class EquipmentResult extends Equipment{
    private  String leaderName;
    private String  EquipmentTypeName;

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getEquipmentTypeName() {
        return EquipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        EquipmentTypeName = equipmentTypeName;
    }

    @Override
    public String toString() {
        return "EquipmentResult{" +
                "userName='" + leaderName + '\'' +
                ", EquipmentTypeName='" + EquipmentTypeName + '\'' +
                '}';
    }
}
