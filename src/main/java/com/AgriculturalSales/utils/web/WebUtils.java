package com.AgriculturalSales.utils.web;


import com.AgriculturalSales.service.SyntheticService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public abstract class WebUtils {

    @Autowired
    SyntheticService syntheticService;

    /**
     * 展示商品
     */
    public  void showProduct(){

    }


    /**
     * 展示种类
     */
    public  void showKind(){

    }
}
