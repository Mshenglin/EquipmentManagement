package com.xu.annotation;

import com.xu.enums.OperationalTypeEnum;

import java.lang.annotation.*;

/**
 * 器材操作日志切面类
 * @author Alkmg
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EquipmentOperationalLog {
    OperationalTypeEnum operationalType();
}
