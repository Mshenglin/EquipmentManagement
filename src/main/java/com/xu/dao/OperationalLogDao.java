package com.xu.dao;

import com.xu.entity.OperationalLog;
import com.xu.entity.OperationalTypeLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Alkmg
 */
public interface OperationalLogDao {
    /**
     * 插入操作日志
     * @param operationalLog
     * @return
     */
    public Integer insertOperationalLog(OperationalLog operationalLog);
    /**
     * selectAll
     * @return
     */
    public List<OperationalLog> selectAll();

    /**
     * 分页查询
     * @param equipmentTypeId
     * @param username
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<OperationalLog> selectOperationalLogList(@Param("id")Integer equipmentTypeId, @Param("username")String username, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

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
