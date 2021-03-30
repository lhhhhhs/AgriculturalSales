package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.Cart;
import com.AgriculturalSales.bean.CartExample;
import com.AgriculturalSales.bean.Product;
import com.AgriculturalSales.dao.CartMapper;
import com.AgriculturalSales.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    /**
     * 根据用户id获取购物车信息
     * @param id
     * @return
     */
    public List<Cart> getCartItemByUserId(Integer id) {
        CartExample example = new CartExample();
        CartExample.Criteria criteria = example.createCriteria();
        criteria.andUIdEqualTo(id);
        List<Cart> carts = cartMapper.selectByExampleWithProduct(example);
        if (carts.isEmpty()) {
            return null;
        }else {
            return carts;
        }
    }

    /**
     * 查询用户购物车数量
     * @param id
     * @return
     */
    public long getCountByUserId(Integer id) {
        CartExample example = new CartExample();
        CartExample.Criteria criteria = example.createCriteria();
        criteria.andUIdEqualTo(id);
        return cartMapper.countByExample(example);

    }

    public void reductProduct(Integer id) {
        Cart cart = cartMapper.selectByPrimaryKey(id);
        Integer count = cart.getpCount();
        if (count == 1){
            cartMapper.deleteByPrimaryKey(id);
            return;
        }
        count--;
        Product product = productMapper.selectByPrimaryKey(cart.getpId());
        BigDecimal price = product.getPrice();
        BigDecimal multiply = price.multiply(new BigDecimal(count));
        cartMapper.updateByPrimaryKeySelective(new Cart(id, null, null, count, multiply, null));
    }

    public void addProduct(Integer id) {
        Cart cart = cartMapper.selectByPrimaryKey(id);
        Integer count = cart.getpCount();
        count++;
        Product product = productMapper.selectByPrimaryKey(cart.getpId());
        BigDecimal price = product.getPrice();
        BigDecimal multiply = price.multiply(new BigDecimal(count));
        cartMapper.updateByPrimaryKeySelective(new Cart(id, null, null, count, multiply, null));
    }

    public void save(Cart cart) {
        CartExample example = new CartExample();
        CartExample.Criteria criteria = example.createCriteria();
        criteria.andPIdEqualTo(cart.getpId());
        criteria.andUIdEqualTo(cart.getuId());
        Product product = productMapper.selectByPrimaryKey(cart.getpId());
        List<Cart> cartList = cartMapper.selectByExample(example);
        if (cartList.isEmpty()) {
            cartMapper.insert(cart);
        }else {
            Cart cart1 = cartList.get(0);
            Integer count = cart1.getpCount();
            count+=cart.getpCount();
            BigDecimal price = product.getPrice();
            BigDecimal totalPrice = price.multiply(new BigDecimal(count));
            cart1.setpCount(count);
            cart1.setTotalPrice(totalPrice);
            cartMapper.updateByPrimaryKey(cart1);
        }
    }
}
