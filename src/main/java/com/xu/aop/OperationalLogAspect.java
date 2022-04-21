package com.xu.aop;

import com.xu.annotation.EquipmentOperationalLog;
import com.xu.enums.OperationalTypeEnum;
import com.xu.service.OperationalService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *器材操作日志切面类
 * @author Alkmg
 */
@Aspect
@Component
public class OperationalLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(OperationalTypeLogAspect.class);
    @Autowired
    private OperationalService operationalService;
    /**
     * 日志切入点
     */
    @Pointcut("@annotation(com.xu.annotation.EquipmentOperationalLog)")
    public void logPointCut(){}

    /**
     * 如果是添加操作，需要在操作后执行，因为id还没形成无法保存日志信息
     * 删除需要在执行目标方法之前进行
     * @param joinPoint
     */
    @Before("logPointCut()")
    public  void doBefore(JoinPoint joinPoint) {
        logger.info("进入器材删除日志记录的切面");
        //解析注解的内容
        String methodName = joinPoint.getSignature().getName();
        Method method = currentMethod(joinPoint, methodName);
        EquipmentOperationalLog log = method.getAnnotation(EquipmentOperationalLog.class);
        if (log.operationalType() == OperationalTypeEnum.DELETE) {
            operationalService.deleteLog(joinPoint, log.operationalType());
        }
    }
    @After("logPointCut()")
    public  void doAfter(JoinPoint joinPoint){
        logger.info("进入器材更改日志记录的切面");
        //解析注解的内容
        String methodName = joinPoint.getSignature().getName();
        Method method = currentMethod(joinPoint,methodName);
        EquipmentOperationalLog log = method.getAnnotation(EquipmentOperationalLog.class);
        if(log.operationalType()== OperationalTypeEnum.UPDATE||log.operationalType()==OperationalTypeEnum.INSERT){
            operationalService.insertAndUpdateLog(joinPoint,log.operationalType());
        }
    }



    /**
     * 获取当前执行的方法
     *
     * @param joinPoint  连接点
     * @param methodName 方法名称
     * @return 方法
     */
    private Method currentMethod(JoinPoint joinPoint, String methodName) {
        /**
         * 获取目标类的所有方法，找到当前要执行的方法
         */
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }

}
