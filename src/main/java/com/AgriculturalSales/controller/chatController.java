package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class chatController {


    @RequestMapping("/chatUser")
    public String chatUser(HttpSession session){
       User user = (User) session.getAttribute("user");
       Websocket.phone=user.getPhone();
        return "chatUser";
    }
    @RequestMapping("/chatAdmin")
    public String chatAdmin(){
        return "chatAdmin";
    }
}
