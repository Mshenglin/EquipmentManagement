package com.xu.dao;

import com.xu.BaseTest;
import com.xu.entity.EquipmentExportResult;
import com.xu.entity.EquipmentResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.dyuproject.protostuff.CollectionSchema.MessageFactories.ArrayList;

public class EquipmentDaoTest extends BaseTest {
    @Autowired
    private  EquipmentDao equipmentDao;
    @Test
    public void selectEquipmentByid(){
        Long id= Long.valueOf(1);
        System.out.println(equipmentDao.selectEquipmentById(id));
    }
    @Test
    public void selectList(){
        List<EquipmentResult> e=new ArrayList<>();
        System.out.println(e=equipmentDao.selectEquipmentList(null,null,null,null,null,1,3));
    }
    @Test
    public void selectExportList(){
        List<EquipmentResult> e=new ArrayList<>();
        System.out.println(e=equipmentDao.selectEquipmentExportList());
    }
    @Test
    public  void nowTime(){
        System.out.println(System.currentTimeMillis());
    }
}
