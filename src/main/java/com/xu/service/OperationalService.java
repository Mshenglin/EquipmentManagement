package com.xu.service;

import com.xu.entity.OperationalLog;
import com.xu.entity.OperationalTypeLog;
import com.xu.entity.PageInfo;
import com.xu.enums.OperationalTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.JoinPoint;

import java.util.List;

/**
 * 器材操作日志业务层接口
 * @author Alkmg
 */
public interface OperationalService {
    /**
     * 删除日志记录
     * @param joinPoint
     * @param operationalType
     */
    public void deleteLog(JoinPoint joinPoint, OperationalTypeEnum operationalType);

    /**
     * 更新日志记录
     * @param joinPoint
     * @param operationalType
     */
    public void insertAndUpdateLog(JoinPoint joinPoint,OperationalTypeEnum operationalType);
    /**
     * 分页查询器材类型操作信息
     * @param id
     * @param username
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageInfo<OperationalLog> findEquipmentTypeList(@Param("id") Integer id, @Param("username") String username, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 用于导出所有操作日志
     * @return
     */
    public List<OperationalLog> findAll();
}
