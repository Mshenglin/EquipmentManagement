package com.xu.web;

import com.xu.entity.PageInfo;
import com.xu.entity.User;
import com.xu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户控制层
 * @author Alkmg
 */
@Controller
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
    public String sign(User user,Model model) {

        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        //权限默认是普通用户
        user.setUserRole(1);
        int result=userService.insertUser(user);
        if(result>0){
            logger.info("用户注册成功"+user.toString());
            return "login";
        }
        else{
            model.addAttribute("meg","信息填写有误！请重新填写。");
            return "sign";
        }
    }
    /**
     * 添加管理员信息
     */
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    @ResponseBody
    public String addAdmin( @RequestBody User user) {
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        int a = userService.insertUser(user);
        return "admin_list";
    }
    /**
     * 分页查询
     */
    @RequestMapping(value = "/findUser")
    public String findAdmin(String username,Integer pageIndex,
                            Integer id ,Integer pageSize, Model model) {

        PageInfo<User> ai = userService.findUserList(username,id,pageIndex,pageSize);
        model.addAttribute("ai",ai);
        return "admin_list";
    }
    /**
     * 根据id查询用户信息
     */
    @RequestMapping( "/findUserById")
    public String findAdminById( Long id,HttpSession session) {
        User a= userService.findUserById(id);
        session.setAttribute("a",a);
        return "admin_edit";
    }
    /**
     * 删除管理员信息；将请求体a_id写入参数a_id
     */
    @RequestMapping( "/deleteAdmin")
    @ResponseBody
    public String deleteAdmin(Long id) {
        int a = userService.deleteUser(id);
        return "admin_list";
    }

    /**
     * 修改管理员信息
     * 将提交数据写入User对象
     */
    @RequestMapping( value = "/updateUser", method = RequestMethod.POST)
    public String updateAdmin(User user) {
        user.setUpdateTime(System.currentTimeMillis());
        int a = userService.updateUser(user);
        return "redirect:/findUser";
    }
    /**
     * 导出Excel
     */
    @RequestMapping(value = "/exportUserList" , method = RequestMethod.POST)
    @ResponseBody
    public List<User> exportAdmin(){
        List<User> admin = userService.findAll();
        return admin;
    }
}
