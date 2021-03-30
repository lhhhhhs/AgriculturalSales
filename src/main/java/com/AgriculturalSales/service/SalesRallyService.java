package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.Product;
import com.AgriculturalSales.bean.SalesRally;
import com.AgriculturalSales.dao.SalesRallyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesRallyService {

    @Autowired
    SalesRallyMapper salesRallyMapper;

    @Autowired
    ProductService productService;

    public void updateSalesRally() {
        salesRallyMapper.deleteByExample(null);
        List<Product> productList = productService.getproductBySales();
        for (Product product : productList) {
            salesRallyMapper.insert(new SalesRally(null, product.getId(), product.getName(), product.getSales(), LocalDate.now()));
        }
    }


    /**
     * 返回数据集合
     * @return
     */
    public Map<String, Integer> getMap() {
        List<SalesRally> salesRallies = salesRallyMapper.selectByExample(null);
        Map<String, Integer> map = new HashMap<>();
        for (SalesRally salesRally : salesRallies) {
            map.put(salesRally.getPname(),salesRally.getSales());
        }
        return map;
    }
}
