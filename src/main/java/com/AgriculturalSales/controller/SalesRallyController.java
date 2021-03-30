package com.AgriculturalSales.controller;

import com.AgriculturalSales.service.SalesRallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SalesRallyController {

    @Autowired
    SalesRallyService salesRallyService;

    /**
     * 更新商品销量表
     */
    @RequestMapping("/updateSalesRally")
    public String updateSalesRally(){
        salesRallyService.updateSalesRally();
        return "redirect:/goAdmin";
    }
}
