package com.xu.dao;

import com.xu.entity.Equipment;
import com.xu.entity.EquipmentType;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 添加器材类型
     * @param equipmentType
     * @return
     */
    public Integer insertEquipmentType(EquipmentType equipmentType);

    /**
     * 根据id删除实验器材类型
     * @param id
     * @return
     */
    public Integer deleteEquipmentTypeById(Long id);

    /**
     * 更新实验器材类型
     * @param equipmentType
     * @return
     */
    public Integer updateEquipmentType(EquipmentType equipmentType);

    /**
     * 分页查询
     * @param id
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */

    public List<EquipmentType> selectEquipmentTypeList(@Param("id")Integer id,@Param("name")String name,@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 查询条数
     * @param id
     * @param name
     * @return
     */
    public Integer selectTotalCount(@Param("id")Integer id,@Param("name")String name);
}
