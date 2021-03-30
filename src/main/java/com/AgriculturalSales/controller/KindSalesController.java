package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.KindSales;
import com.AgriculturalSales.service.KindSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KindSalesController {

    @Autowired
    KindSalesService kindSalesService;


    /**
     * 在种类销量表里添加今日的销量数据
     * @return
     */
    @RequestMapping("/saveTodayKindSales")
    public String saveTodayKindSales(){
        kindSalesService.saveTodayKindSales();
        return "redirect:/goAdmin";
    }
}
