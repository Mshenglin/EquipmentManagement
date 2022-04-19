package com.xu.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xu.entity.*;
import com.xu.service.EquipmentService;
import com.xu.service.EquipmentTypeService;
import com.xu.util.ExcelForEquipmentImportUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

/**
 * 器材控制层
 * @author Alkmg
 */
@Controller
public class EquipmentController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ExcelForEquipmentImportUtil util;

    @RequestMapping(value = "/findEquipment")
    public String findEquipment(Integer id, String code, String name, String equipmentType, String department, Integer pageIndex, Integer pageSize, Model model) {
        PageInfo<EquipmentResult> pi = equipmentService.findEquipmentList(id, code, name, equipmentType, department, pageIndex, pageSize);
        model.addAttribute("pi", pi);
        return "equipment_list";
    }

    @RequestMapping("/addEquipment")
    public String addEquipment(@RequestBody Equipment equipment) {
        Long time = System.currentTimeMillis() * 1000;
        equipment.setCreateTime(time);
        equipment.setUpdateTime(time);
        equipment.setEquipmentStatus(0);
        equipmentService.insertEquipment(equipment);
        return "equipment_list";
    }

    /**
     * 删除实验器材
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteEquipment")
    public String deleteEquipment(Long id) {
        equipmentService.deleteEquipment(id);
        return "equipment_list";
    }

    /**
     * 更新器材状态
     *
     * @param id
     * @return
     */
    @RequestMapping("/updateEquipmentStatus")
    public String updateEquipmentStatus(Long id) {
        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setEquipmentStatus(1);
        equipment.setUpdateTime(System.currentTimeMillis() * 1000);
        equipmentService.updateEquipment(equipment);
        return "equipment_list";
    }

    /**
     * 更改器材数据
     */
    @RequestMapping(value = "/updateEquipment")
    public String updateEquipment(Equipment equipment) {
        logger.info("updateEquipment" + equipment);
        equipment.setUpdateTime(System.currentTimeMillis() * 1000);
        equipmentService.updateEquipment(equipment);
        return "redirect:/findEquipment";
    }

    /**
     * 导出器材数据
     */
    @RequestMapping("/exportEquipmentList")
    @ResponseBody
    public List<EquipmentExportResult> exportEquipmentList() {
        return equipmentService.findEquipmentExportAll();
    }

    @RequestMapping("/findEquipmentById")
    public String findEquipmentById(Long id, HttpSession session) {

        EquipmentResult s = equipmentService.findEquipmentById(id);
        session.setAttribute("s", s);
        return "equipment_edit";
    }

    /**
     * 文件模板下载
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        //获取项目中模板为输入流
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("templates/DetailTemp.xlsx");
        //利用poi框架导出文件
        //org.apache.poi.xssf.usermodel.XSSFWorkbook;
        XSSFWorkbook wb0 = new XSSFWorkbook(is);
        try {
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment;filename=" + new String("导入器导入清单模板.xlsx".getBytes("gb2312"), "ISO8859-1"));
            response.setContentType("application/msexcel;charset=UTF-8");
            wb0.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入excel文件
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public HashMap<String, Object> importData(@RequestPart(value = "file") MultipartFile file, HttpServletRequest request) {

        //获取导入时传来的参数：可做业务处理：略
        String year = request.getParameter("year");
        String id = request.getParameter("id");

        //构建返回前端值，此处使用HashMap<String, Object>
        //其中key为code value为1代表成功，value为0代表失败
        //   key为msg，value写入具体错误信息是什么
        HashMap<String, Object> resultMap = null;
        try {
            InputStream inputStream = file.getInputStream();
            //写个工具类，处理excel 的校验、保存：返回值直接是可以对接前端的
            resultMap = util.readExcel(inputStream, file.getOriginalFilename(), new Equipment());
            inputStream.close();
            return resultMap;
        } catch (Exception e) {
            System.out.print("[导入异常]" + e.getMessage());
            resultMap.put("code", "0");
            resultMap.put("msg", "读取文件异常！");
            return resultMap;
        }
    }
}
