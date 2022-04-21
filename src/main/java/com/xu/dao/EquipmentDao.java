package com.xu.dao;

import com.xu.entity.Equipment;
import com.xu.entity.EquipmentResult;
import org.apache.ibatis.annotations.Param;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.List;

/**
 * 实验室器材的Dao层实现
 * @author mashenglin
 */
public interface EquipmentDao {
    /**获取总条数
     * @return
     */
    public Integer selectTotalCount(@Param("id") Integer id, @Param("code")String code, @Param("name") String name, @Param("equipmentType") String equipmentType , @Param("department")String department);

    /**
     * 分页查询器材信息
     * @param name
     * @param id
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<EquipmentResult> selectEquipmentList(@Param("id") Integer id, @Param("code")String code, @Param("name") String name, @Param("equipmentType") String equipmentType , @Param("department")String department, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 添加Equipment信息
     * @param equipment
     * @return
     */
    public int insertEquipment(Equipment equipment);

    /**
     * 删除Equipment信息
     * @param id
     * @return
     */
    public int deleteEquipment(Long id);

    /**
     * 修改Equipment信息
     * @param equipment
     * @return
     */
    public int updateEquipment(Equipment equipment);

    /**
     * 根据Equipment的id查询Equipment的信息
     * @param id
     * @return
     */
    public EquipmentResult selectEquipmentById(Long id);

    /**
     * 查询Equipment表中的所有信息
     * @return
     */
    public List<Equipment> selectAll();

    /**
     * 导出实验器材
     * @return
     */
    List<EquipmentResult> selectEquipmentExportList();
    /**
     * 根据器材的编号去查询器材的id
     * 用于操作日志记录，由于在插入之前 没有形成id
     * @param code 器材编号
     * @return
     */
    public Equipment selectEquipmentByCode(String code);
}
