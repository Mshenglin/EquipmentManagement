package com.xu.service.impl;

import com.xu.dao.EquipmentTypeDao;
import com.xu.dao.OperationalTypeLogDao;
import com.xu.entity.EquipmentType;
import com.xu.entity.OperationalTypeLog;
import com.xu.entity.PageInfo;
import com.xu.entity.User;
import com.xu.enums.OperationalTypeEnum;
import com.xu.service.OperationalTypeService;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Alkmg
 */
@Service
public class OperationalTypeServiceImpl implements OperationalTypeService {
    private static final Logger logger = LoggerFactory.getLogger(OperationalTypeServiceImpl.class);
    @Autowired
    private  OperationalTypeLogDao operationalTypeLogDao;
    @Autowired
    private EquipmentTypeDao equipmentTypeDao;
    @Override
    public void deleteLog(JoinPoint joinPoint, OperationalTypeEnum operationalType, String meg) {
       try {
           //获取参数
           Object[] args=joinPoint.getArgs();
           EquipmentType equipmentType=null;
           HttpSession session=null;
           //根据操作类型的不同去拼接remark
           Long id=null;
           //解析session参数
           for (Object arg:args
                ) {
               if(arg instanceof HttpSession){
                   session= (HttpSession) arg;
                   continue;
               }
               if(arg instanceof Long){
                   id= (Long) arg;
               }
           }
           User user=null;
           if (session != null) {
               //获取到用户的信息
               user= (User) session.getAttribute("ad");
           }
           //获取到user的值不是空构建OperationalType对象向数据库中存
           OperationalTypeLog operationalTypeLog=new OperationalTypeLog();
           if (user != null) {
               operationalTypeLog.setUsername(user.getUsername());
           }
           else {
               operationalTypeLog.setUsername("未知用户");
           }
           operationalTypeLog.setCreateTime(System.currentTimeMillis());
           operationalTypeLog.setOperationalId(operationalType.getType());
           String remark="";
               if(id!=null) {
                   equipmentType = equipmentTypeDao.selectEquipmentTypeById(id);
                   if (equipmentType != null) {
                      operationalTypeLog.setEquipmentTypeId(equipmentType.getId());
                      remark= operateContentDelete(joinPoint,equipmentType);
                   }
               }
           operationalTypeLog.setRemark(remark);
           logger.info("日志内容："+operationalTypeLog.toString());
           operationalTypeLogDao.insertOperationalTypeLog(operationalTypeLog);
       } catch (Exception e) {
           logger.info("器材操作日志添加失败");
           e.printStackTrace();
       }
    }

    @Override
    public void insertAndUpdateLog(JoinPoint joinPoint, OperationalTypeEnum operationalType, String meg) {
        try {
            //获取参数
            Object[] args=joinPoint.getArgs();
            EquipmentType equipmentType=null;
            HttpSession session=null;
            //解析session参数
            for (Object arg:args
            ) {
                if(arg instanceof HttpSession){
                    session= (HttpSession) arg;
                    continue;
                }
                if(arg instanceof EquipmentType){
                    equipmentType= (EquipmentType) arg;
                    continue;
                }
            }
            User user=null;
            if (session != null) {
                //获取到用户的信息
                user= (User) session.getAttribute("ad");
            }
            //获取到user的值不是空构建OperationalType对象向数据库中存
            OperationalTypeLog operationalTypeLog=new OperationalTypeLog();
            if (user != null) {
                operationalTypeLog.setUsername(user.getUsername());
            }
            else {
                operationalTypeLog.setUsername("未知用户");
            }
            operationalTypeLog.setCreateTime(System.currentTimeMillis());
            operationalTypeLog.setOperationalId(operationalType.getType());
            String remark="";
            //根据操作类型的不同去拼接remark
            if(operationalType==OperationalTypeEnum.INSERT){
                if(equipmentType!=null){
                  Long id= equipmentTypeDao.selectEquipmentTypeByName(equipmentType.getName());
                    if (id != null) {
                        operationalTypeLog.setEquipmentTypeId(id);
                        remark=operateContentInsert(joinPoint,args,id);
                    }
                    else {
                        operationalTypeLog.setEquipmentTypeId(0L);
                    }
                }
            }
            else if(operationalType==OperationalTypeEnum.UPDATE){
                if(equipmentType!=null){
                    operationalTypeLog.setEquipmentTypeId(equipmentType.getId());
                }
                remark=operateContentUpdate(joinPoint,args);
            }
            operationalTypeLog.setRemark(remark);
            logger.info("日志内容："+operationalTypeLog.toString());
            operationalTypeLogDao.insertOperationalTypeLog(operationalTypeLog);
        } catch (Exception e) {
            logger.info("器材操作日志添加失败");
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<OperationalTypeLog> findEquipmentTypeList(Integer id, String username, Integer currentPage, Integer pageSize) {
        PageInfo<OperationalTypeLog> pi = new PageInfo<>();
        pi.setPageIndex(currentPage);
        pi.setPageSize(pageSize);
        //获取总条数
        Integer totalCount =operationalTypeLogDao.selectTotalCount(id, username, currentPage, pageSize);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //每一页显示管理员信息数
            //currentPage = (currentPage-1)*pageSize  当前页码数减1*最大条数=开始行数
            List<OperationalTypeLog> adminList =operationalTypeLogDao.selectOperationalTypeLogList(id,username, (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(adminList);
        }
        return pi;
    }

    @Override
    public List<OperationalTypeLog> findAll() {
        return operationalTypeLogDao.selectAll();
    }

    private String operateContentDelete(JoinPoint joinPoint, EquipmentType equipmentType) {
        StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("删除实验器材类型：").append(equipmentType);
        return stringBuilder.toString();
    }

    private String operateContentUpdate(JoinPoint joinPoint,  Object[] args) {
        StringBuilder stringBuilder=new StringBuilder();
        EquipmentType equipmentType=null;
        for (Object arg:args
        ) {
            if(arg instanceof EquipmentType){
                equipmentType= (EquipmentType) arg;
                break;
            }
        }

        if(equipmentType!=null){
            stringBuilder.append("修改实验器材类型：").append(equipmentType.toString());
        }
        return stringBuilder.toString();
    
    }

    private String operateContentInsert(JoinPoint joinPoint, Object[] args, Long id) {
        StringBuilder stringBuilder=new StringBuilder();
        EquipmentType equipmentType=null;
        for (Object arg:args
             ) {
            if(arg instanceof EquipmentType){
                equipmentType= (EquipmentType) arg;
                break;
            }
        }

        if(equipmentType!=null){
            stringBuilder.append("添加实验器材类型：").append("{id:").append(id).append(",name:").append(equipmentType.getName()).append("}");
        }
        return stringBuilder.toString();
    }

}
