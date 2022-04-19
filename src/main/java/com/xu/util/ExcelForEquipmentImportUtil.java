package com.xu.util;

import com.xu.entity.Equipment;
import com.xu.entity.EquipmentResult;
import com.xu.service.EquipmentService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *excel导入工具类
 * @
 * @author Alkmg
 */
public class ExcelForEquipmentImportUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelForEquipmentImportUtil.class);

    @Autowired
    private EquipmentService equipmentService;
    /**
     * 处理上传的文件
     *
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public  HashMap<String, Object> readExcel(InputStream in, String fileName, Equipment equipment) throws Exception {
        //构建返回值
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        //创建Excel工作薄
        try {
            //判断文件格式是否为excel，是否为空
            //详见：3）后端校验文件格式的方法
            Workbook work = getWorkbook(in, fileName);
            if (null == work) {
                throw new Exception("创建Excel工作薄为空！");
            }

            //读第一页
            Sheet sheet = work.getSheetAt(0);

            //step1: 验证导入文件的标题头是否合法
            String[] columnName = {"器材编号","器材名称","器材价格","负责人","器材类型","部门"};
            //详见 ： 4）校验表格标题行方法
            String resultString = verificationExcelHeadLine(sheet, columnName);
            //有返回信息代表有错误，没有返回信息代表验证通过
            if (!resultString.equals("")) {
                hashMap.put("code", "0");
                hashMap.put("msg", "请使用正确模板上传excel文件。" + resultString);
                return hashMap;
            }

            //step2: 验证导入文件的标题 列 是否合法
            /**String[] colName = {"第一月", "第二月",。。。};
            //详见： 6）判断列标题头是否正确
            String resultStr = verificationExcelHeadLineTwo(sheet, colName);
            //有返回信息代表有错误，没有返回信息代表验证通过
            if (!resultStr.equals("")) {
                hashMap.put("code", "0");
                hashMap.put("msg", "请使用正确模板上传excel文件。" + resultStr);
                return hashMap;
            }
            */
            //step3: 验证数据是否为空，长度是否过长，是否为纯数字。。。
            // 详见7）校验数据是否为空
            String verificationDateResultString = verificationDate(sheet);
            if (!verificationDateResultString.equals("")) {
                hashMap.put("code", "0");
                hashMap.put("msg", verificationDateResultString);
                return hashMap;
            }

            //step4: 文件内容合法，做业务逻辑处理（保存）
            // 详见9）做业务逻辑处理（保存数据）

            String saveResultString = equipmentService.batchInsertEquipment(sheet);
            if (!saveResultString.equals("")) {
                hashMap.put("code", "0");
                hashMap.put("msg", saveResultString);
                return hashMap;
            }

            //以上都顺利进行，返回前端：导入成功
            hashMap.put("code", "1");
            hashMap.put("msg", "导入成功！");
            return hashMap;
        } catch (Exception e) {
            System.out.print("[读取excel异常]" + e.getMessage());
            hashMap.put("code", "0");
            hashMap.put("msg", "读取excel异常");
            return hashMap;
        }

    }

    /**
     * 后端判断文件格式方式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    public  Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }
    /***
     * 校验导入的列表Excel文件标题行是否为标准行
     */
    public static String verificationExcelHeadLine(Sheet sheet, String[] columnName) throws Exception {
        String result = "";
        try {
            //获取第一行：标题行
            Row row = sheet.getRow(0);

            if (row != null && row.getLastCellNum() >= columnName.length) {
                int lastCellNum = row.getLastCellNum();
                //循环遍历指定的数组内容与文件中内容一一对比
                for (int idx = 0; idx < lastCellNum; idx++) {
                    //将格子中的内容转为java的string类型的方法
                    //详见5）将excel格子中的内容转为java的string类型的方法
                    String value = getStringCellValue(row.getCell(idx));
                        if (value == null || !columnName[idx].equals(value)) {
                            result = result+"标题行第" + (idx + 1) + "列名称错误！";

                    }
                }
            } else {
                result = "上传文件首行不能为空或与标准表格表头不一致！";
            }
        } catch (Exception ex) {
            logger.info("【导入成本】校验数据异常");
        }
        return result;
    }
    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    public static String getStringCellValue(Cell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case STRING:
                strCell = cell.getStringCellValue();
                break;
            case NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }
    public String verificationExcelHeadLineTwo(Sheet sheet, String[] columnName) {
        String result = "";
        try {
            int allRowNum = sheet.getLastRowNum();
            for(int i = 1; i <= allRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(1) != null) {
                    String value = getStringCellValue(row.getCell(1));
                    if (value == null || !columnName[i-1].equals(value)) {
                        result = result+"第" +i+"行第2列名称错误！";
                    }
                } else {
                    result = "请选择正确模板！";
                }
            }
        } catch (Exception ex) {
            logger.info("【导入成本】校验数据异常");
        }
        return result;
    }
    /**
     * 校验数据是否为纯数字，是否为空
     * @param sheet
     * @return
     */

    private static String verificationDate(Sheet sheet) {
        String result = "";
        try {
            //循环行
            for(int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                int allCellNum = row.getLastCellNum();
                if (row != null) {
                    //循环列
                    for (int idx = 0; idx < allCellNum; idx++) {
                        String value = getStringCellValue(row.getCell(idx));
                        if(StringUtils.isEmpty(value)) {
                            result = result+"第"+i+"行"+"第"+idx+"列为空；";
                        }else {
                            //第4列为纯数字
                            if(idx==2) {
                                //isNumeric为校验是否为数字的方法
                                //详见：8）校验是否为纯数字
                                if(!isNumeric(value)) {
                                    result = result+"第"+i+"行"+"第"+idx+"列不是数字格式；";
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.info("【导入成本】校验数据异常");
        }
        return result;
    }
    /**
     * 判断是否为数字：直接去创建BigDecimal，失败就是非数字，BigDecimal校验的比我们厉害
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        return true;
    }

}
