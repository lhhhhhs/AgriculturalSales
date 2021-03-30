package com.AgriculturalSales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/condition")
public class SerialController  {


    @RequestMapping("/show")
    public String list(HttpServletRequest request) {
    	/* 此处就是拦截器接收的 session，uid 绑定一个默认值用来测试
    	   生产环境要绑定一个能区分用户的值 */
        request.getSession().setAttribute("uid", "con");
        return "show";
    }
}
