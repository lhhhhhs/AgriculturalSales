package com.AgriculturalSales.bean;

import java.math.BigDecimal;

public class OrderItem {
    private Integer id;

    private String oid;

    private Integer pid;

    private String pName;

    private String pImgPath;

    private BigDecimal pPrice;

    private Integer pCount;

    private BigDecimal totalPrice;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", pid=" + pid +
                ", pName='" + pName + '\'' +
                ", pImgPath='" + pImgPath + '\'' +
                ", pPrice=" + pPrice +
                ", pCount=" + pCount +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public OrderItem(Integer id, String oid, Integer pid, String pName, String pImgPath, BigDecimal pPrice, Integer pCount, BigDecimal totalPrice) {
        this.id = id;
        this.oid = oid;
        this.pid = pid;
        this.pName = pName;
        this.pImgPath = pImgPath;
        this.pPrice = pPrice;
        this.pCount = pCount;
        this.totalPrice = totalPrice;
    }

    public OrderItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getpImgPath() {
        return pImgPath;
    }

    public void setpImgPath(String pImgPath) {
        this.pImgPath = pImgPath == null ? null : pImgPath.trim();
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
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