package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.dao.OrderItemMapper;
import com.AgriculturalSales.dao.OrderMapper;
import com.AgriculturalSales.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Controller
public class OrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    AddressService addressService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    ProductService productService;


    /**
     * 跳转到订单页面
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/order")
    public String order(Map<String,Object> map,HttpSession session,@RequestParam(value = "flag",defaultValue = "0") Integer flag){
        User user = (User)session.getAttribute("user");
        List<Order> orderList = orderService.getOrderByUid(user.getId());
        if(orderList != null) {
            map.put("orderList", orderList);
        }
        if(flag == 1){
            map.put("msg","没有库存了");
        }
        return "user/order";
    }

    /**
     * 创建订单
     * @param cartList
     * @param session
     * @return
     */
    @RequestMapping("/CreateOrder")
    @ResponseBody
    public Msg createOrder(Integer[] cartList, HttpSession session) {
        String id = orderService.createOrder(cartList,session);
        if(id==null){
            return Msg.fail();
        }
        return Msg.success();
    }


    /**
     * 付钱
     * @param orderId
     * @return
     */
    @RequestMapping("/payment")
    public String payment(String orderId,HttpSession session) throws UnsupportedEncodingException {
        User user = (User)session.getAttribute("user");
        System.out.println("付钱逻辑");
        Boolean aBoolean = addressService.checkAddressByUser(user.getId());
        if (!aBoolean){
            return "redirect:/address";
        }
        Boolean b = orderService.checkOrderAddress(orderId);
        if(!b){
            return "redirect:/address?orderId="+orderId+"&msg="+URLEncoder.encode("请选择一个地址","UTF-8");
        }
        orderService.paymentOrderById(orderId);
        return "redirect:/order";
    }


    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @RequestMapping("/delOrder")
    public String delOrder(String orderId){
        orderService.delOrderById(orderId);
        return "redirect:/order";
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @RequestMapping("/orderDetails")
    public String orderDetails(String orderId,Map<String,Object> map){
        Order order = orderService.getOrderById(orderId);
        List<OrderItem> orderItemList =  orderItemService.getListByOrderId(orderId);
        map.put("order", order);
        map.put("orderItemList", orderItemList);
        return "user/orderDetails";
    }

    /**
     * 直接购买商品
     * @param id
     * @param count
     * @param map
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/BuyProduct")
    public String BuyProduct(Integer id,Integer count,Map<String,Object> map,HttpSession session) throws UnsupportedEncodingException {
        User user = (User)session.getAttribute("user");
        String orderId = orderService.createOrderOnly(id, count, user);
        //没库存
        if(orderId==null){
            return "redirect:/order?flag=1";
        }
        return "redirect:/order";
    }

    /**
     *  确认收货
     */
    @RequestMapping("/confirmReceipt")
    public String confirmReceipt(String orderId,Map<String,Object> map,HttpSession session) {
        User user = (User)session.getAttribute("user");
        orderService.confirmReceipt(orderId);
        return "redirect:/order";
    }

    /**
     * 打开评论
     * @param orderId
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/evaluation")
    public String evaluation(String orderId,Map<String,Object> map,HttpSession session)  {
        System.out.println("orderId = " + orderId);
        map.put("orderId",orderId);
        return "user/evaluation";
    }

    /**
     * 保存评论
     * @param evaluation
     * @param orderId
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/saveEvaluation")
    public String saveEvaluation(String evaluation,String orderId, Map<String,Object> map,HttpSession session)  {
        User user = (User)session.getAttribute("user");
        orderService.finishOrder(orderId);
        Evaluation evaluationObj = new Evaluation(null, evaluation, LocalDate.now(), orderId, user.getPhone(),null);
        evaluationService.saveEvaluation(evaluationObj,orderId);
        return "redirect:/order";
    }
}


