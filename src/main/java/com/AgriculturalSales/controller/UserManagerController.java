package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.Admin;
import com.AgriculturalSales.bean.Msg;
import com.AgriculturalSales.bean.Order;
import com.AgriculturalSales.bean.User;
import com.AgriculturalSales.service.AdminService;
import com.AgriculturalSales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserManagerController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    /**
     * 跳转到user管理页面
     *
     * @return
     */
    @RequestMapping("/userManager")
    public String userManager() {
        return "manager/userManager";
    }


    /**
     * 跳转到admin管理页面
     *
     * @return
     */
    @RequestMapping("/adminManager")
    public String adminManager() {
        return "manager/adminManager";
    }


    /**
     * user数据表格接口
     *
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/userTable")
    @ResponseBody
    public Map userTable(Integer page, Integer limit, HttpSession session) {
        List<User> users = userService.getAll(page, limit);
        int count = userService.getCount();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", users);
        return map;
    }


    /**
     * 搜索用户表
     *
     * @param page
     * @param limit
     * @param session
     * @param phone
     * @return
     */
    @RequestMapping("/userSearch")
    @ResponseBody
    public Map userSearch(Integer page, Integer limit, HttpSession session, String phone) {
        List<User> users = userService.getAllByPhone(page, limit, phone);
        int count = userService.getCountByPhone(phone);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", users);
        return map;
    }


    /**
     * admin数据表格接口
     *
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/adminTable")
    @ResponseBody
    public Map adminTable(Integer page, Integer limit, HttpSession session) {
        List<Admin> admins = adminService.getAll(page, limit);
        int count = adminService.getCount();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", admins);
        return map;
    }


    /**
     * 搜索管理表
     *
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/adminSearch")
    @ResponseBody
    public Map adminSearch(Integer page, Integer limit, HttpSession session, String name) {
        List<Admin> admins = adminService.getAllByName(page, limit, name);
        int count = adminService.getCountByName(name);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", admins);
        return map;
    }


    @RequestMapping("/updateAdminPwd")
    @ResponseBody
    public Msg updateAdminPwd(String p1, String p2, String p3, Integer id) {
        if (p1 == null || p2 == null || p3 == null || id == null) {
            return Msg.fail().add("msg", "错误！");
        }
        if(!p2.equals(p3)){
            return Msg.fail().add("msg", "两次密码不一致！");
        }
        if(!adminService.checkPwd(id,p1)){
            return Msg.fail().add("msg", "旧密码错误！");
        }
        adminService.updatePwd(p2,id);
        return Msg.success();
    }


}
