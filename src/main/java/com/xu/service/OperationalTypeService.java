package com.xu.service;

import com.xu.entity.EquipmentType;
import com.xu.entity.OperationalTypeLog;
import com.xu.entity.PageInfo;
import com.xu.enums.OperationalTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.JoinPoint;

import java.util.List;

/**
 * 器材类型操作日志Service
 * @author Alkmg
 */
public interface OperationalTypeService {
    /**
     * 删除日志记录
     * @param joinPoint
     * @param operationalType
     * @param meg
     */
    public void deleteLog(JoinPoint joinPoint,OperationalTypeEnum operationalType, String meg);

    /**
     * 更新日志记录
     * @param joinPoint
     * @param operationalType
     * @param meg
     */
    public void insertAndUpdateLog(JoinPoint joinPoint,OperationalTypeEnum operationalType, String meg);
    /**
     * 分页查询器材类型操作信息
     * @param id
     * @param username
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageInfo<OperationalTypeLog> findEquipmentTypeList(@Param("id") Integer id, @Param("username") String username, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 用于导出所有操作日志
     * @return
     */
    public List<OperationalTypeLog> findAll();
}
