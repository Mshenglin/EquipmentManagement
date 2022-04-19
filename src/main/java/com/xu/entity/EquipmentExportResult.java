package com.xu.entity;

import java.io.Serializable;

/**
 * 数据库表导出实体类
 * @author Alkmg
 */
public class EquipmentExportResult extends EquipmentResult implements Serializable {
    private String formatCreatTime;
    private String formatUpdateTime;
    private String formatEquipmentStatus;

    public String getFormatEquipmentStatus() {
        return formatEquipmentStatus;
    }

    public void setFormatEquipmentStatus(String formatEquipmentStatus) {
        this.formatEquipmentStatus = formatEquipmentStatus;
    }

    public String getFormatCreatTime() {
        return formatCreatTime;
    }

    public void setFormatCreatTime(String formatCreatTime) {
        this.formatCreatTime = formatCreatTime;
    }

    public String getFormatUpdateTime() {
        return formatUpdateTime;
    }

    public void setFormatUpdateTime(String formatUpdateTime) {
        this.formatUpdateTime = formatUpdateTime;
    }

    @Override
    public String toString() {
        return "EquipmentExportResult{" +
                "formatCreatTime='" + formatCreatTime + '\'' +
                ", formatUpdateTime='" + formatUpdateTime + '\'' +
                "} " + super.toString();
    }
}
