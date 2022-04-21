package com.xu.service.impl;

import com.xu.dao.EquipmentDao;
import com.xu.dao.EquipmentTypeDao;
import com.xu.dao.UserDao;
import com.xu.entity.*;
import com.xu.runable.ImportTask;
import com.xu.service.EquipmentService;
import com.xu.service.OperationalService;
import com.xu.util.DateUtil;
import com.xu.util.ExcelForEquipmentImportUtil;
import com.xu.util.ThreadPoolUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.xu.util.ExcelForEquipmentImportUtil.getStringCellValue;

/**
 * 器材业务接口实现类
 * @author Alkmg
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private EquipmentTypeDao equipmentTypeDao;
    @Autowired
    private  ThreadPoolUtil threadPoolUtil;
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
    public EquipmentResult findEquipmentById(Long id) {
        return equipmentDao.selectEquipmentById(id);
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentDao.selectAll();
    }

    @Override
    public List<EquipmentExportResult> findEquipmentExportAll() {
        List<EquipmentExportResult> res=new LinkedList<>();
        List<EquipmentResult> ru=equipmentDao.selectEquipmentExportList();
        //遍历赋值
        for (EquipmentResult equipmentResult:ru
             ) {
            EquipmentExportResult equipmentExportResult=new EquipmentExportResult();
            BeanUtils.copyProperties(equipmentResult,equipmentExportResult);
            equipmentExportResult.setFormatCreatTime(DateUtil.getFormatTime(equipmentResult.getCreateTime()));
            equipmentExportResult.setFormatUpdateTime(DateUtil.getFormatTime(equipmentResult.getUpdateTime()));
            if(equipmentResult.getEquipmentStatus()==0){
                equipmentExportResult.setFormatEquipmentStatus("已入库");
            }
            else{
                equipmentExportResult.setFormatEquipmentStatus("已出库");
            }
            res.add(equipmentExportResult);
        }
        return res;
    }

    @Override
    public String batchInsertEquipment(Sheet sheet) {
        String result = "";

        //构建子表数据
        List<Equipment> equipmentList = new ArrayList<Equipment>();
        Row row = null;
        try {
            for (int i =1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                Equipment equipment = new Equipment();
                //组装对象
                equipment.setCode(getStringCellValue(row.getCell(0)));
                equipment.setName(getStringCellValue(row.getCell(1)));
                equipment.setPrice(Double.parseDouble(getStringCellValue(row.getCell(2))));
                //负责人和器材类型需要到数据库查询
                String leaderName=getStringCellValue(row.getCell(3));
                String equipmentTypeName=getStringCellValue(row.getCell(4));
                Integer leaderId=null;
                Integer equipmentTypeId=null;
                if(leaderName!=null&&equipmentTypeName!=null){
                     leaderId=userDao.selectUserIdByName(leaderName);
                     equipmentTypeId= Math.toIntExact(equipmentTypeDao.selectEquipmentTypeByName(equipmentTypeName));
                }
                else {
                    logger.info("解析单元格时leaderName或equipmentTypeName为空",leaderName,equipmentTypeName);
                }
                equipment.setEquipmentTypeId(equipmentTypeId);
                equipment.setLeaderId(leaderId);
                equipment.setEquipmentStatus(0);
                equipment.setDepartment(getStringCellValue(row.getCell(5)));
                Long time=System.currentTimeMillis();
                equipment.setCreateTime(time);
                equipment.setUpdateTime(time);
                equipmentList.add(equipment);
            }
        }catch(Exception e) {
            result = "数据格式异常无法保存。";
            return result;
        }
        logger.info("导入实验器材数据"+equipmentList.toString());
        //使用线程池批量导入
        try{
            for (int i = 0; i < equipmentList.size(); i++) {
                threadPoolUtil.execute(new ImportTask(equipmentDao,equipmentList.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    }

