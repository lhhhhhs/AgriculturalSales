package com.AgriculturalSales.bean;

import java.math.BigDecimal;

public class Cart {
    private Integer id;

    private Integer pId;

    private Integer uId;

    private Integer pCount;

    private BigDecimal totalPrice;

    private Product product;

    public Cart() {
    }

    public Cart(Integer id, Integer pId, Integer uId, Integer pCount, BigDecimal totalPrice, Product product) {
        this.id = id;
        this.pId = pId;
        this.uId = uId;
        this.pCount = pCount;
        this.totalPrice = totalPrice;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", pId=" + pId +
                ", uId=" + uId +
                ", pCount=" + pCount +
                ", totalPrice=" + totalPrice +
                ", product=" + product +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getpCount() {
        return pCount;
    }

    public void setpCount(Integer pCount) {
        this.pCount = pCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}