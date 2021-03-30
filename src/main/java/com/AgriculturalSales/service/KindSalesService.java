package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.Kind1;
import com.AgriculturalSales.bean.KindSales;
import com.AgriculturalSales.bean.Product;
import com.AgriculturalSales.bean.ProductExample;
import com.AgriculturalSales.dao.Kind1Mapper;
import com.AgriculturalSales.dao.KindSalesMapper;
import com.AgriculturalSales.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KindSalesService {


    @Autowired
    Kind1Mapper kind1Mapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    KindSalesMapper kindSalesMapper;

    /**
     *在种类销量表里保存数据
     */
    public void saveTodayKindSales() {
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
