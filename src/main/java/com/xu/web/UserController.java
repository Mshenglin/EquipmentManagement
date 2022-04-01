package com.xu.web;

import com.xu.entity.User;
import com.xu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

/**
 * 用户控制层
 * @author Alkmg
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    /**
     * 将提交数据(username,password)写入User对象
     */
    @RequestMapping(value = "/login")
    public String login(Model model, HttpSession session, HttpServletRequest request) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        // 通过账号和密码查询用户
        User ad = userService.findUserByUsernameAndPassword(user);
        if (ad != null) {
            session.setAttribute("ad", ad);
            return "homepage";
        }
        model.addAttribute("msg", "用户名或密码错误，请重新登录！");
        return "login";
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public User getUserById(Long id) {
        User user = userService.findUserById(id);
        return user;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(User user, Model model, HttpSession session) {
        session.invalidate();
        return "login";
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/signFirst")
    public String signFirst() {
        return "sign";
    }
    /**
     * 用户注册
     */
    @RequestMapping(value = "/sign")
    public String sign(Model model, HttpSession session, HttpServletRequest request) {
        User user=new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setPhone(Long.getLong(request.getParameter("phone")));
        user.setStudentId(request.getParameter("studentId"));
        //TODO 根据部门名称查找id写入
        user.setDepartmentId("1");
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        //权限默认是普通用户
        user.setUserRole(1);
        int result=userService.insertUser(user);
        if(result>0){
            return "login";
        }
        else{
            model.addAttribute("meg","信息填写有误！请重新填写。");
            return "sign";
        }
    }
}
