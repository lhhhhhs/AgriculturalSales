package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.dao.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    AddressMapper addressMapper;


    /**
     * 创建订单
     * @param cartList
     * @param session
     */
    public String createOrder(Integer[] cartList, HttpSession session) {
        User user = (User)session.getAttribute("user");
//        AddressExample example = new AddressExample();
//        AddressExample.Criteria criteria = example.createCriteria();
//        criteria.andUidEqualTo(user.getId());
//        criteria.andDefaultStatusEqualTo(1);
//        List<Address> addressList = addressMapper.selectByExample(example);
//        if (addressList.isEmpty()){
//            return null;
//        }
//        Address address = addressList.get(0);
        long currentTimeMillis = System.currentTimeMillis();
        String Id = String.valueOf(currentTimeMillis)+user.getId();
        BigDecimal totalPrice = new BigDecimal(0);
        for (Integer cartId : cartList) {
            Cart cart = cartMapper.selectByPrimaryKey(cartId);
            Product product = productMapper.selectByPrimaryKey(cart.getpId());
            if (product.getStock()<=0){
                return null;
            }
            OrderItem orderItem = new OrderItem(null, Id, cart.getpId(), product.getName(), product.getImgPath(), product.getPrice(), cart.getpCount(), cart.getTotalPrice());
            orderItemMapper.insert(orderItem);
            totalPrice = totalPrice.add(cart.getTotalPrice());
            cartMapper.deleteByPrimaryKey(cartId);
        }
        Order order = new Order(Id, LocalDateTime.now(), totalPrice, 1, null, user.getId());
        orderMapper.insert(order);
       return Id;
    }

    /**
     * 获取用户的订单
     * @param id
     * @return
     */
    public List<Order> getOrderByUid(Integer id) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(id);
        List<Order> orders = orderMapper.selectByExample(example);
        if (orders.isEmpty()) {
            return null;
        }else {
            return orders;
        }
    }

    /**
     * 订单付款修改订单状态
     * @param orderId
     */
    public void paymentOrderById(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setStatus(2);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 取消订单
     * @param orderId
     */
    public void delOrderById(String orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOidEqualTo(orderId);
        orderItemMapper.deleteByExample(example);
    }

    /**
     * 根据订单编号获取订单
     * @param orderId
     * @return
     */
    public Order getOrderById(String orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    public void updateOrder(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 检查地址是否发货 返回true代表已发货
     * @param orderId
     * @return
     */
    public Boolean checkOrderStatus(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        Integer status = order.getStatus();
        if(status >= 3){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 直接够买商品的订单创建
     * @param id
     * @param count
     */
    public String createOrderOnly(Integer id, Integer count,User user) {
//        AddressExample example = new AddressExample();
//        AddressExample.Criteria criteria = example.createCriteria();
//        criteria.andUidEqualTo(user.getId());
//        criteria.andDefaultStatusEqualTo(1);
//        List<Address> addressList = addressMapper.selectByExample(example);
//        if (addressList.isEmpty()){
//            return null;
//        }
//        Address address = addressList.get(0);
        long currentTimeMillis = System.currentTimeMillis();
        String Id = String.valueOf(currentTimeMillis)+user.getId();
        Product product = productMapper.selectByPrimaryKey(id);
        if(product.getStock()<=0){
            return null;
        }
        BigDecimal price = product.getPrice();
        BigDecimal totalPrice = price.multiply(new BigDecimal(count));
        Order order = new Order(Id, LocalDateTime.now(), totalPrice, 1, null, user.getId());
        OrderItem orderItem = new OrderItem(null, Id, product.getId(), product.getName(), product.getImgPath(), product.getPrice(), count, totalPrice);
        orderItemMapper.insert(orderItem);
        orderMapper.insert(order);
        return Id;
    }

    /**
     * 检查订单是否有地址 返回false就是没地址
     * @param orderId
     * @return
     */
    public Boolean checkOrderAddress(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        Integer addressId = order.getAddressId();
        if (addressId==null) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * 确认收货
     * @param orderId
     */
    public void confirmReceipt(String orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(4);
        orderMapper.updateByPrimaryKeySelective(order);
        //销量和库存变化
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOidEqualTo(orderId);
        List<OrderItem> orderItemList = orderItemMapper.selectByExample(example);
        for (OrderItem orderItem : orderItemList) {
            Integer pid = orderItem.getPid();
            Integer count = orderItem.getpCount();
            Product product = productMapper.selectByPrimaryKey(pid);
            Integer sales = product.getSales();
            Integer stock = product.getStock();
            sales = count + sales;
            stock = stock - count;
            product.setSales(sales);
            product.setStock(stock);
            productMapper.updateByPrimaryKey(product);
        }
    }

    /**
     * 完成订单
     * @param orderId
     */
    public void finishOrder(String orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(5);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    public List<Order> getAll(Integer page, Integer limit) {
        OrderExample example = new OrderExample();
        example.setOrderByClause("creat_time");
        PageHelper.startPage(page,limit);
        List<Order> orders = orderMapper.selectByExample(example);
        return orders;
    }

    public int getCount() {
        return (int)orderMapper.countByExample(null);
    }

    public List<Order> getAllLikeid(Integer page, Integer limit, String id) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andIdLike("%"+id+"%");
        PageHelper.startPage(page,limit);
        List<Order> orders = orderMapper.selectByExample(example);
        return orders;
    }

    public int getCountLikeid(String id) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andIdLike("%"+id+"%");
        return (int)orderMapper.countByExample(example);
    }
}


