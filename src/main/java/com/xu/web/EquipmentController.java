package com.xu.web;

import com.xu.entity.Equipment;
import com.xu.entity.EquipmentResult;
import com.xu.entity.PageInfo;
import com.xu.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 器材控制层
 * @author Alkmg
 */
@Controller
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @RequestMapping(value = "/findEquipment")
    public String  findEquipment( Integer id,String code,String name,String equipmentType,String department,Integer pageIndex, Integer pageSize, Model model){
        if(equipmentType!=null){
            //根据器材类型名称查询id TODO

        }
        PageInfo<EquipmentResult> pi = equipmentService.findEquipmentList(id,code,name,equipmentType,department,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "equipment_list";
    }
}
