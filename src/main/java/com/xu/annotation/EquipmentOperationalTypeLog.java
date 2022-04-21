package com.xu.annotation;

import com.xu.enums.OperationalTypeEnum;

import java.lang.annotation.*;

/**
 * 器材类型操作日志注解
 * @author Alkmg
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EquipmentOperationalTypeLog {
    OperationalTypeEnum operationalType();
    String meg();

}
