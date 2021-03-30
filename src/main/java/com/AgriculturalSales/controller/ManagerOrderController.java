package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.service.AddressService;
import com.AgriculturalSales.service.OrderItemService;
import com.AgriculturalSales.service.OrderService;
import com.AgriculturalSales.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerOrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @Autowired
    OrderItemService orderItemService;

    /**
     * 跳转到订单管理页面
     * @return
     */
    @RequestMapping("/orderInfoManager")
    public String orderInfoManager(){
        return "manager/orderManager";
    }

    /**
     * 跳转到订单状态管理页面
     * @return
     */
    @RequestMapping("/orderStatusManager")
    public String orderStatusManager(){
        return "manager/orderStatusManager";
    }

    /**
     * 订单数据表格接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/orderTable")
    @ResponseBody
    public Map orderTable(Integer page, Integer limit, HttpSession session){
        List<Order> orders = orderService.getAll(page,limit);
        int count = orderService.getCount();
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", orders);
        return map;
    }


    /**
     * 订单管理查看地址
     * @param addressId
     * @return
     */
    @RequestMapping("/orderAdd")
    @ResponseBody
    public Msg orderAdd(Integer addressId){
        Address addressById = addressService.getAddressById(addressId);
        return Msg.success().add("add", addressById);
    }

    /**
     * 订单管理查看用户名
     * @param uid
     * @return
     */
    @RequestMapping("/orderuser")
    @ResponseBody
    public Msg orderuser(Integer uid){
        User user = userService.getById(uid);
        return Msg.success().add("user", user);
    }


    /**
     * 订单管理查看订单项
     * @return
     */
    @RequestMapping("/orderItem")
    @ResponseBody
    public Msg orderItem(String id){
        List<OrderItem> listByOrderId = orderItemService.getListByOrderId(id);
        return Msg.success().add("itemList",listByOrderId);
    }

    /**
     * 搜索订单
     * @param id
     * @return
     */
    @RequestMapping("/orderSearch")
    @ResponseBody
    public Map orderSearch(Integer page, Integer limit,String id){
        System.out.println("id = " + id);
        List<Order> orders = orderService.getAllLikeid(page,limit,id);
        int count = orderService.getCountLikeid(id);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", orders);
        return map;
    }


    /**
     * 发货
     * @return
     */
    @RequestMapping("/delivery")
    @ResponseBody
    public Msg delivery(String orderId){
        System.out.println("orderId = " + orderId);
        Order orderById = orderService.getOrderById(orderId);
        if(orderById.getStatus()<=1){
            return Msg.fail().add("msg", "还未付款！");
        }
        if (orderById.getStatus()==2){
            orderById.setStatus(3);
            orderService.updateOrder(orderById);
            return Msg.success();
        }
        return Msg.fail().add("msg", "未知错误！");
    }


}
