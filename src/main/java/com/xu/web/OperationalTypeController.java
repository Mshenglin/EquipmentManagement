package com.xu.web;

import com.xu.entity.EquipmentType;
import com.xu.entity.OperationalTypeLog;
import com.xu.entity.PageInfo;
import com.xu.service.OperationalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 器材类型操作日志查询
 * @author Alkmg
 */
@Controller
public class OperationalTypeController {
    @Autowired
    private OperationalTypeService operationalTypeService;
    @RequestMapping("/findOperationalType")
    public String findEquipmentType(Integer id, String name,Integer pageIndex, Integer pageSize, Model model){
        PageInfo<OperationalTypeLog> pi=operationalTypeService.findEquipmentTypeList(id,name,pageIndex,pageSize);
        model.addAttribute("ci", pi);
        return "operational_type_list";
    }
    @RequestMapping("/findOperationalTypeList")
    @ResponseBody
    public List<OperationalTypeLog> findEquipmentTypeList(){
        return operationalTypeService.findAll();
    }
}
