package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.User;
import com.AgriculturalSales.service.AdminService;
import com.AgriculturalSales.service.CartService;
import com.AgriculturalSales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    AdminService adminService;

    /**
     * 登录
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session, String loginPhone, String loginPwd, HttpServletRequest request, HttpServletResponse response, Map<String,Object> map){
        System.out.println("loginPhone = " + loginPhone);
        System.out.println("loginPwd = " + loginPwd);
        if(adminService.check(loginPhone,loginPwd,session)){
            return "redirect:/goAdmin";
        }
        //验证电话是否存在
        if(!userService.checkedPhone(loginPhone)){
            //验证用户密码是否匹配
            if(userService.checkedUser(loginPhone, loginPwd)){
                User user  = userService.getByPhone(loginPhone);
                if(user != null) {
                    long count = cartService.getCountByUserId(user.getId());
                    session.setAttribute("user", user);
                    session.setAttribute("cartCount", count);
                    return "redirect:/goIndex";
                }else {
                    request.setAttribute("msg", "用户错误！");
                    return "login";
                }
            }else {
                request.setAttribute("msg", "密码错误！");
                return "login";
            }
        }else {
            request.setAttribute("msg", "电话号码不存在！");
            return "login";
        }
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }


}
