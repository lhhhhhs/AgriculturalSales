package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.Kind1;
import com.AgriculturalSales.bean.KindSales;
import com.AgriculturalSales.bean.Product;
import com.AgriculturalSales.bean.ProductExample;
import com.AgriculturalSales.dao.Kind1Mapper;
import com.AgriculturalSales.dao.KindSalesMapper;
import com.AgriculturalSales.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TimingController {

    @Autowired
    Kind1Mapper kind1Mapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    KindSalesMapper kindSalesMapper;

    /**
     * 每天的23点统计今日Kind销量
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void saveTodayKindSales(){
        System.out.println(LocalDate.now());
        List<Kind1> kind1s = kind1Mapper.selectByExample(null);
        for (Kind1 kind1 : kind1s) {
            Integer totalSales = 0;
            ProductExample example = new ProductExample();
            ProductExample.Criteria criteria = example.createCriteria();
            criteria.andK1EqualTo(kind1.getId());
            List<Product> products = productMapper.selectByExample(example);
            for (Product product : products) {
                totalSales = totalSales + product.getSales();
            }
            kindSalesMapper.insert(new KindSales(null, kind1.getName(), kind1.getId(), totalSales, LocalDate.now()));
        }
    }



}
