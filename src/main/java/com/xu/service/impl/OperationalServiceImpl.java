package com.xu.service.impl;

import com.xu.dao.EquipmentDao;
import com.xu.dao.OperationalLogDao;
import com.xu.entity.*;
import com.xu.enums.OperationalTypeEnum;
import com.xu.service.OperationalService;
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
public class OperationalServiceImpl implements OperationalService {
    private static final Logger logger = LoggerFactory.getLogger(OperationalServiceImpl.class);
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private OperationalLogDao operationalLogDao;
    @Override
    public void deleteLog(JoinPoint joinPoint, OperationalTypeEnum operationalType) {
        try {
            //获取参数
            Object[] args=joinPoint.getArgs();
            EquipmentResult equipment=null;
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
            OperationalLog operationalLog=new OperationalLog();
            if (user != null) {
                operationalLog.setUsername(user.getUsername());
            }
            else {
                operationalLog.setUsername("未知用户");
            }
            operationalLog.setCreateTime(System.currentTimeMillis());
            operationalLog.setOperationalId(operationalType.getType());
            String remark="";
            if(id!=null) {
                equipment = equipmentDao.selectEquipmentById(id);
                if (equipment != null) {
                    operationalLog.setEquipmentId(equipment.getId());
                    remark= operateContentDelete(joinPoint,equipment);
                }
            }
            operationalLog.setRemark(remark);
            logger.info("日志内容："+operationalLog.toString());
            operationalLogDao.insertOperationalLog(operationalLog);
        } catch (Exception e) {
            logger.info("器材操作日志添加失败");
            e.printStackTrace();
        }
    }

    @Override
    public void insertAndUpdateLog(JoinPoint joinPoint, OperationalTypeEnum operationalType) {
        try {
            //获取参数
            Object[] args=joinPoint.getArgs();
            Equipment equipment=null;
            HttpSession session=null;
            Long id=null;

            //解析session参数
            for (Object arg:args
            ) {
                if(arg instanceof HttpSession){
                    session= (HttpSession) arg;
                    continue;
                }
                else if(arg instanceof Equipment){
                    equipment= (Equipment) arg;
                    continue;
                }
                else if(arg instanceof Long){
                    id= (Long) arg;
                }
            }
            User user=null;
            if (session != null) {
                //获取到用户的信息
                user= (User) session.getAttribute("ad");
            }
            //获取到user的值不是空构建OperationalType对象向数据库中存
            OperationalLog operationalLog=new OperationalLog();
            if (user != null) {
                operationalLog.setUsername(user.getUsername());
            }
            else {
                operationalLog.setUsername("未知用户");
            }
            operationalLog.setCreateTime(System.currentTimeMillis());
            operationalLog.setOperationalId(operationalType.getType());
            String remark="";
            //根据操作类型的不同去拼接remark
            if(operationalType==OperationalTypeEnum.INSERT){
                Equipment res=null;
                if(equipment!=null){
                   res=equipmentDao.selectEquipmentByCode(equipment.getCode());
                }
                if (res != null) {
                    remark=operateContentInsert(joinPoint,args,res);
                    operationalLog.setEquipmentId(res.getId());
                }
                }
            else if(operationalType==OperationalTypeEnum.UPDATE){
                //更新为两种状态，一个是信息更新，一个是目前是否在库更新
                if(equipment!=null){
                   // 信息更新
                    remark=operateContentUpdate(joinPoint,equipment);
                    operationalLog.setEquipmentId(equipment.getId());
                }
                else if(id!=null){
                    // 状态更新
                    remark=operateContentUpdate(joinPoint,id);operationalLog.setEquipmentId(id);
                }

            }
            operationalLog.setRemark(remark);
            logger.info("日志内容："+operationalLog.toString());
            operationalLogDao.insertOperationalLog(operationalLog);
        } catch (Exception e) {
            logger.info("器材操作日志添加失败");
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<OperationalLog> findEquipmentTypeList(Integer id, String username, Integer currentPage, Integer pageSize) {
        PageInfo<OperationalLog> pi = new PageInfo<>();
        pi.setPageIndex(currentPage);
        pi.setPageSize(pageSize);
        //获取总条数
        Integer totalCount =operationalLogDao.selectTotalCount(id, username, currentPage, pageSize);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //每一页显示管理员信息数
            //currentPage = (currentPage-1)*pageSize  当前页码数减1*最大条数=开始行数
            List<OperationalLog> adminList =operationalLogDao.selectOperationalLogList(id,username, (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(adminList);
        }
        return pi;
    }

    @Override
    public List<OperationalLog> findAll() {
        return operationalLogDao.selectAll();
    }

    private String operateContentDelete(JoinPoint joinPoint, Equipment equipment) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("删除实验器材：").append(equipment);
        return stringBuilder.toString();
    }

    private String operateContentUpdate(JoinPoint joinPoint, Equipment equipment) {
        StringBuilder stringBuilder=new StringBuilder();
        if(equipment!=null){
            stringBuilder.append("修改实验器材类型：").append(equipment.toString());
        }
        return stringBuilder.toString();

    }
    private String operateContentUpdate(JoinPoint joinPoint, Long id) {
        StringBuilder stringBuilder=new StringBuilder();
        if(id!=null){
            stringBuilder.append("修改实验器材状态类型：").append("{id:").append(id).append("}");
        }
        return stringBuilder.toString();

    }
    private String operateContentInsert(JoinPoint joinPoint, Object[] args, Equipment equipment) {
        StringBuilder stringBuilder=new StringBuilder();
        if(equipment!=null){
            stringBuilder.append("添加实验器材类型：").append(equipment.toString());
        }
        return stringBuilder.toString();
    }

}
