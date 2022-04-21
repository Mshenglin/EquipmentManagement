package com.xu.service.impl;

import com.xu.dao.EquipmentTypeDao;
import com.xu.entity.EquipmentResult;
import com.xu.entity.EquipmentType;
import com.xu.entity.PageInfo;
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

    @Override
    public Integer findTotalCount(Integer id, String name) {
        return equipmentTypeDao.selectTotalCount(id,name);
    }

    @Override
    public PageInfo<EquipmentType> findEquipmentTypeList(Integer id, String name, Integer currentPage, Integer pageSize) {
        PageInfo<EquipmentType> pi = new PageInfo<>();
        pi.setPageIndex(currentPage);
        pi.setPageSize(pageSize);
        //获取总条数
        Integer totalCount = equipmentTypeDao.selectTotalCount(id,name);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //每一页显示管理员信息数
            //currentPage = (currentPage-1)*pageSize  当前页码数减1*最大条数=开始行数
            List<EquipmentType> adminList =	equipmentTypeDao.selectEquipmentTypeList(id,name, (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(adminList);
        }
        return pi;
    }

    @Override
    public int insertEquipmentType(EquipmentType equipmentType) {
        return equipmentTypeDao.insertEquipmentType(equipmentType);
    }

    @Override
    public int deleteEquipmentType(Long id) {
        return equipmentTypeDao.deleteEquipmentTypeById(id);
    }

    @Override
    public int updateEquipmentType(EquipmentType equipmentType) {
        return equipmentTypeDao.updateEquipmentType(equipmentType);
    }
}
