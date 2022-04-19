package com.xu.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xu.entity.*;
import com.xu.service.EquipmentService;
import com.xu.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 器材控制层
 * @author Alkmg
 */
@Controller
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EquipmentTypeService equipmentTypeService;
    @RequestMapping(value = "/findEquipment")
    public String  findEquipment( Integer id,String code,String name,String equipmentType,String department,Integer pageIndex, Integer pageSize, Model model){
        PageInfo<EquipmentResult> pi = equipmentService.findEquipmentList(id,code,name,equipmentType,department,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "equipment_list";
    }
    @RequestMapping("/addEquipment")
    public String addEquipment(@RequestBody Equipment equipment){
        Long time=System.currentTimeMillis()*1000;
        equipment.setCreateTime(time);
        equipment.setUpdateTime(time);
        equipment.setEquipmentStatus(0);
        equipmentService.insertEquipment(equipment);
        return "equipment_list";
    }

    /**
     * 删除实验器材
     * @param id
     * @return
     */
    @RequestMapping("/deleteEquipment")
    public String deleteEquipment(Long id){
       equipmentService.deleteEquipment(id);
       return "equipment_list";
    }

    /**
     * 更新器材状态
     * @param id
     * @return
     */
    @RequestMapping("/updateEquipmentStatus")
    public String updateEquipmentStatus(Long id){
        Equipment equipment=new Equipment();
        equipment.setId(id);
        equipment.setEquipmentStatus(1);
        equipment.setUpdateTime(System.currentTimeMillis()*1000);
        equipmentService.updateEquipment(equipment);
        return "equipment_list";
    }
    /**
     * 更改器材数据
     */
    @RequestMapping(value="/updateEquipment" )
    public String updateEquipment(Equipment equipment){
        equipment.setUpdateTime(System.currentTimeMillis()*1000);
        equipmentService.updateEquipment(equipment);
        return "redirect:/findEquipment";
    }
    /**
     * 导出器材数据
     */
    @RequestMapping("/exportEquipmentList")
    @ResponseBody
    public List<EquipmentExportResult> exportEquipmentList(){
        return  equipmentService.findEquipmentExportAll();
    }
    @RequestMapping( "/findEquipmentById")
    public String findEquipmentById(Long id, HttpSession session) {

        EquipmentResult s= equipmentService.findEquipmentById(id);
        session.setAttribute("s",s);
        return "equipment_edit";
    }
}
