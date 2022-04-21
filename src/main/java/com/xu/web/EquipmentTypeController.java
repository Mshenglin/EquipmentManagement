package com.xu.web;

import com.xu.annotation.EquipmentOperationalTypeLog;
import com.xu.entity.Equipment;
import com.xu.entity.EquipmentResult;
import com.xu.entity.EquipmentType;
import com.xu.entity.PageInfo;
import com.xu.enums.OperationalTypeEnum;
import com.xu.service.EquipmentTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 器材类型管理控制层
 * @author mashenglin
 */
@Controller
public class EquipmentTypeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EquipmentTypeService equipmentTypeService;
    @RequestMapping("/findEquipmentTypeList")
    @ResponseBody
   public List<EquipmentType> findEquipmentTypeList(){
       return equipmentTypeService.findEquipmentTypeList();
   }
    @RequestMapping("/findEquipmentType")
    public String findEquipmentType(Integer id, String name,Integer pageIndex, Integer pageSize, Model model){
        PageInfo<EquipmentType> pi=equipmentTypeService.findEquipmentTypeList(id,name,pageIndex,pageSize);
        model.addAttribute("di", pi);
        return "equipment_type_list";
    }
    /**
     * 删除实验器材
     *
     * @param id
     * @return
     */
    @EquipmentOperationalTypeLog(operationalType = OperationalTypeEnum.DELETE,meg = "删除器材类型")
    @RequestMapping("/deleteEquipmentType")
    public String deleteEquipment(Long id,HttpSession session) {
        equipmentTypeService.deleteEquipmentType(id);
        return "equipment_type_list";
    }
    /**
     * 更改器材数据
     */
    @EquipmentOperationalTypeLog(operationalType = OperationalTypeEnum.UPDATE,meg = ("更新实验器材类型"))
    @RequestMapping(value = "/updateEquipmentType")
    public String updateEquipment(EquipmentType equipment,HttpSession session) {
        logger.info("更新的器材对象为"+equipment.toString());
        equipmentTypeService.updateEquipmentType(equipment);
        return "redirect:/findEquipmentType";
    }
    @EquipmentOperationalTypeLog(operationalType = OperationalTypeEnum.INSERT,meg = "添加实验器材类型")
    @RequestMapping("/addEquipmentType")
    public String addEquipment(@RequestBody EquipmentType equipmentType,HttpSession session) {

        equipmentTypeService.insertEquipmentType(equipmentType);
        return "equipment_type_list";
    }
    @RequestMapping("/findEquipmentTypeById")
    public String findEquipmentById(Long id, HttpSession session) {

        EquipmentType d = equipmentTypeService.findEquipmentTypeById(id);
        session.setAttribute("d", d);
        return "equipment_type_edit";
    }
}
