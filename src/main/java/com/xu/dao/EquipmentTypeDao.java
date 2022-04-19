package com.xu.dao;

import com.xu.entity.EquipmentType;

import java.util.List;

/**
 * 器材管理控制层
 * @author Alkmg
 */
public interface EquipmentTypeDao {
    /**
     * 获取所有器材类型
     * @return
     */
    public List<EquipmentType> selectAll();

    /**
     * 根据id查询器材名称
     * @param id
     * @return
     */
    public EquipmentType selectEquipmentTypeById(Long id);
    /**
     * 根据name查询器材id
     * @param name
     * @return
     */
    public Long selectEquipmentTypeByName(String name);

}
