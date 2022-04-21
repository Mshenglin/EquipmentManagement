package com.xu.dao;

import com.xu.entity.OperationalTypeLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 器材类型操作数据层
 * @author Alkmg
 */
public interface OperationalTypeLogDao {
    /**
     * 插入操作日志
     * @param operationalTypeLog
     * @return
     */
    public Integer insertOperationalTypeLog(OperationalTypeLog operationalTypeLog);
    /**
     * selectAll
     * @return
     */
    public List<OperationalTypeLog> selectAll();

    /**
     * 分页查询
     * @param equipmentTypeId
     * @param username
     * @param currentPage
     * @param pageSize
     * @return
     */
   public List<OperationalTypeLog> selectOperationalTypeLogList(@Param("id")Integer equipmentTypeId,@Param("username")String username, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 分页查询的总条数
     * @param equipmentTypeId
     * @param username
     * @param currentPage
     * @param pageSize
     * @return
     */
   public Integer selectTotalCount(@Param("id")Integer equipmentTypeId,@Param("username")String username, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
}
