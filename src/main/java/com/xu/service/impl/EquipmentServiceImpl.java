package com.xu.service.impl;

import com.xu.dao.EquipmentDao;
import com.xu.entity.Equipment;
import com.xu.entity.EquipmentResult;
import com.xu.entity.PageInfo;
import com.xu.entity.User;
import com.xu.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 器材业务接口实现类
 * @author Alkmg
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Override
    public Integer findTotalCount(String name, Integer id) {
        return null;
    }

    @Override
    public PageInfo<EquipmentResult> findEquipmentList( Integer id,String code,String name,String equipmentType,String department,  Integer currentPage, Integer pageSize) {
        PageInfo<EquipmentResult> pi = new PageInfo<EquipmentResult>();
        pi.setPageIndex(currentPage);
        pi.setPageSize(pageSize);
        //获取总条数
        Integer totalCount = equipmentDao.selectTotalCount(id,code,name,equipmentType,department);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //每一页显示管理员信息数
            //currentPage = (currentPage-1)*pageSize  当前页码数减1*最大条数=开始行数
            List<EquipmentResult> adminList =	equipmentDao.selectEquipmentList(id,code,name,equipmentType,department,
                    (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(adminList);
        }
        return pi;
    }

    @Override
    public int insertEquipment(Equipment equipment) {
        return equipmentDao.insertEquipment(equipment);
    }

    @Override
    public int deleteEquipment(Long id) {
        return equipmentDao.deleteEquipment(id);
    }

    @Override
    public int updateEquipment(Equipment equipment) {
        return equipmentDao.updateEquipment(equipment);
    }

    @Override
    public Equipment findEquipmentById(Long id) {
        return equipmentDao.selectEquipmentById(id);
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentDao.selectAll();
    }
}
