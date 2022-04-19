package com.xu.web;

import com.xu.entity.EquipmentType;
import com.xu.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 器材类型管理控制层
 * @author mashenglin
 */
@Controller
public class EquipmentTypeController {
    @Autowired
    private EquipmentTypeService equipmentTypeService;
    @RequestMapping("/findEquipmentTypeList")
    @ResponseBody
   public List<EquipmentType> findEquipmentTypeList(){
       return equipmentTypeService.findEquipmentTypeList();
   }

}
