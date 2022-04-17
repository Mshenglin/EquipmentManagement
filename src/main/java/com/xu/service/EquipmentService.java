package com.xu.service;

import com.xu.entity.EquipmentResult;
import com.xu.entity.PageInfo;
import com.xu.entity.Equipment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 器材管理业务层接口
 * @author mashenglin
 */
public interface EquipmentService {
    /**获取总条数
     * @param name
     * @param id
     * @return
     */
    public Integer findTotalCount(@Param("name") String name, @Param("id") Integer id);

    /**
     * 分页查询器材信息
     * @param id
     * @param code
     * @param name
     * @param equipmentType
     * @param department
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageInfo<EquipmentResult> findEquipmentList(@Param("id") Integer id, @Param("code")String code, @Param("name") String name, @Param("equipmentType") String equipmentType , @Param("department")String department, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 添加器材信息
     * @param equipment
     * @return
     */
    public int insertEquipment(Equipment equipment);

    /**
     * 删除器材信息
     * @param id
     * @return
     */
    public int deleteEquipment(Long id);

    /**
     * 修改器材信息
     * @param equipment
     * @return
     */
    public int updateEquipment(Equipment equipment);

    /**
     * 根据器材的id查询器材的信息
     * @param id
     * @return
     */
    public Equipment findEquipmentById(Long id);

    /**
     * 查询器材表中的所有信息
     * @return
     */
    public List<Equipment> findAll();
}
