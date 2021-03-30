package com.AgriculturalSales.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Order {
    private String id;

    private LocalDateTime creatTime;

    private BigDecimal totalPrice;

    private Integer status;

    private Integer addressId;

    private Integer uid;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", creatTime=" + creatTime +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", addressId=" + addressId +
                ", uid=" + uid +
                '}';
    }

    public Order(String id, LocalDateTime creatTime, BigDecimal totalPrice, Integer status, Integer addressId, Integer uid) {
        this.id = id;
        this.creatTime = creatTime;
        this.totalPrice = totalPrice;
        this.status = status;
        this.addressId = addressId;
        this.uid = uid;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}