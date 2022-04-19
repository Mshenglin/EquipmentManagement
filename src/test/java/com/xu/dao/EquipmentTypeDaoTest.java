package com.xu.dao;

import com.xu.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EquipmentTypeDaoTest extends BaseTest {
    @Autowired
    private  EquipmentTypeDao equipmentTypeDao;
    @Test
    public void selectAll(){
        System.out.println(equipmentTypeDao.selectAll());
    }
}
