package com.AgriculturalSales.controller;

import com.AgriculturalSales.utils.web.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Jump {


    @RequestMapping("/goIndex")
    public String goIndex(){
        return "index";
    }


    @RequestMapping("/goLogin")
    public String goLogin(){
        return "login";
    }


}
