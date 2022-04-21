package com.xu.service;

import com.xu.entity.Equipment;
import com.xu.entity.EquipmentResult;
import com.xu.entity.EquipmentType;
import com.xu.entity.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 器材管理业务控制层
 * @author Alkmg
 */
public interface EquipmentTypeService {
    /**
     * 获取类型列表
     * @return
     */
    public List<EquipmentType> findEquipmentTypeList();

    /**
     * id查找信息
     * @param id
     * @return
     */
    public  EquipmentType findEquipmentTypeById(Long id);

    /**
     * name查找id
     * @param name
     * @return
     */
    public  Long findEquipmentTypeByName(String name);
    /**获取总条数
     * @param name
     * @param id
     * @return
     */
    public Integer findTotalCount( @Param("id") Integer id,@Param("name") String name);

    /**
     * 分页查询器材信息
     * @param id
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageInfo<EquipmentType> findEquipmentTypeList(@Param("id") Integer id, @Param("name") String name, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 添加器材信息
     * @param equipmentType
     * @return
     */
    public int insertEquipmentType(EquipmentType equipmentType);

    /**
     * 删除器材信息
     * @param id
     * @return
     */
    public int deleteEquipmentType(Long id);

    /**
     * 修改器材信息
     * @param equipmentType
     * @return
     */
    public int updateEquipmentType(EquipmentType equipmentType);

}
