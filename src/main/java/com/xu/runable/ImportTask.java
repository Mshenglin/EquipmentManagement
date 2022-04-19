package com.xu.runable;

import com.xu.dao.EquipmentDao;
import com.xu.entity.Equipment;
import com.xu.service.impl.EquipmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

 /**
  * @author Alkmg
  */

 public class ImportTask implements Runnable {
     private static final Logger logger = LoggerFactory.getLogger(ImportTask.class);

            private EquipmentDao equipmentDao;
            private Equipment equipment;

                public ImportTask(EquipmentDao equipmentDao,Equipment equipment) {
                    this.equipmentDao=equipmentDao;
                    this.equipment = equipment;
                 }

                @Override
        public void run() {
                   if (null != equipment) {
                           // 业务逻辑，例如批量insert或者update
                       equipmentDao.insertEquipment(equipment);
                       logger.info("现在操作的数据是{}", equipment);
                       }
                }
    }