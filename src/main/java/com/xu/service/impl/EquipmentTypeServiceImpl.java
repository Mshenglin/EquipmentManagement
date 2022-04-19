package com.xu.service.impl;

import com.xu.dao.EquipmentTypeDao;
import com.xu.entity.EquipmentType;
import com.xu.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alkmg
 */
@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
    @Autowired
    private EquipmentTypeDao equipmentTypeDao;
    @Override
    public List<EquipmentType> findEquipmentTypeList() {
        return equipmentTypeDao.selectAll();
    }

    @Override
    public EquipmentType findEquipmentTypeById(Long id) {
        return equipmentTypeDao.selectEquipmentTypeById(id);
    }

    @Override
    public Long findEquipmentTypeByName(String name) {
        return equipmentTypeDao.selectEquipmentTypeByName(name);
    }
}
