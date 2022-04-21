package com.xu.enums;

/**
 * 操作类型记录枚举类
 * @author Alkmg
 */
public enum OperationalTypeEnum {

    INSERT(1, "增加"), UPDATE(2, "修改"), DELETE(3, "删除");

    private int type;

    private String typeInfo;

    private OperationalTypeEnum(int type, String typeInfo) {
        this.type = type;
        this.typeInfo = typeInfo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static OperationalTypeEnum stateOf(int index) {
        for (OperationalTypeEnum state : values()) {
            if (state.getType() == index) {
                return state;
            }
        }
        return null;
    }
}
