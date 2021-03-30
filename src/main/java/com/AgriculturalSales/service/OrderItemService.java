package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.OrderItem;
import com.AgriculturalSales.bean.OrderItemExample;
import com.AgriculturalSales.dao.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemMapper orderItemMapper;


    public List<OrderItem> getListByOrderId(String orderId) {
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOidEqualTo(orderId);
        List<OrderItem> orderItemList = orderItemMapper.selectByExample(example);
        return orderItemList;
    }
}
