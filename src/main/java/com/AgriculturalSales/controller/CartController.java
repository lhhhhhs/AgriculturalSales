package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.Cart;
import com.AgriculturalSales.bean.Msg;
import com.AgriculturalSales.bean.Product;
import com.AgriculturalSales.bean.User;
import com.AgriculturalSales.service.CartService;
import com.AgriculturalSales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    /**
     * 打开购物车
     * @return
     */
    @RequestMapping("/cart")
    public String goCart(Map<String,Object> map, HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Cart> cartList = cartService.getCartItemByUserId(user.getId());
        long countByUserId = cartService.getCountByUserId(user.getId());
        session.setAttribute("cartCount", countByUserId);
        if (cartList == null){
            map.put("msg", "抱歉！您的购物车为空");
        }else {
            map.put("cartList",cartList);
        }
        return "user/cart";
    }


    /**
     * 购物车商品-1
     * @param id
     * @return
     */
    @RequestMapping("/reductProduct")
    public String reductProduct(Integer id){
        cartService.reductProduct(id);
        return "redirect:/cart";
    }

    /**
     * 购物车商品+1
     * @param id
     * @return
     */
    @RequestMapping("/addProduct")
    public String addProduct(Integer id){
        cartService.addProduct(id);
        return "redirect:/cart";
    }


    /**
     * 添加购物车
     * @return
     */
    @RequestMapping("/addCart")
    @ResponseBody
    public Msg addCart(HttpServletRequest request, Integer pid, Integer count, HttpSession session){
        Product product = productService.getById(pid);
        User user = (User)session.getAttribute("user");
        BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(count));
        Cart cart = new Cart(null, pid, user.getId(), count, totalPrice, null);
        cartService.save(cart);
        return Msg.success();
    }
}
