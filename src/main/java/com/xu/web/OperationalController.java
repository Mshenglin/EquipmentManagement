package com.xu.web;

import com.xu.entity.OperationalLog;
import com.xu.entity.PageInfo;
import com.xu.service.OperationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 器材日志控制层
 * @author Alkmg
 */
@Controller
public class OperationalController {
    @Autowired
    private OperationalService operationalService;
    @RequestMapping("/findOperational")
    public String findEquipmentType(Integer id, String name,Integer pageIndex, Integer pageSize, Model model){
        PageInfo<OperationalLog> pi=operationalService.findEquipmentTypeList(id,name,pageIndex,pageSize);
        model.addAttribute("mi", pi);
        return "operational_list";
    }
    @RequestMapping("/findOperationalList")
    @ResponseBody
    public List<OperationalLog> findEquipmentTypeList(){
        return operationalService.findAll();
    }
}
