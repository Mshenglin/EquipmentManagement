package com.xu.service;

import com.xu.entity.EquipmentType;

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
}
