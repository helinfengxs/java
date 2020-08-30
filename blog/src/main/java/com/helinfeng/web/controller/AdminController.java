package com.helinfeng.web.controller;

import com.helinfeng.web.domain.User;
import com.helinfeng.web.service.TypeService;
import com.helinfeng.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private UserService userService;

    private TypeService typeService;
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     *
     * @param userName 用户名
     * @param passWord 面试
     * @param redirectAttributes 重定向传值对象
     * @param request 请求对象
     * @return 账号密码错误返回登录页面,正确返回后台首页
     */
    @PostMapping("/login")
    public String adminLogin(String userName, String passWord, RedirectAttributes redirectAttributes, HttpServletRequest request){
        User user = userService.checkUser(userName, passWord);
        if(user == null){
            redirectAttributes.addFlashAttribute("message","账号或者密码错误");
            return "redirect:/admin";
        }
        HttpSession session = request.getSession();
        user.setPassWord(null);
        session.setAttribute("user",user);
        return "admin/index.html";
    }

    /**
     * 注销
     * @param request 请求对象
     * @return 返回登录页面
     */
    @GetMapping("/loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/admin";
    }

    /**
     * 用户注册
     * @param user 用户对象
     * @return 返回登录页面
     */
    @PostMapping("/saveUser")
    @ResponseBody
    public Map<String ,Object> registry(User user){
        Map<String ,Object> map = new HashMap<>();
        boolean result = userService.addUser(user);
        if (result){
            map.put("msg","success");
            map.put("data","添加管理员成功");
        }else{
            map.put("msg","failed");
            map.put("data","添加管理员失败");
        }
        return map;
    }


//    @GetMapping("/tags")
//    public String tags(){
//        return "admin/tags";
//    }
}
