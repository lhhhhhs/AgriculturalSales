package com.AgriculturalSales.bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class NewProduct {
    private Integer id;

    private Integer pid;

    private String name;

    private BigDecimal price;

    private Integer sales;

    private Integer stock;

    private String imgPath;

    private String info;

    private Integer k1;

    private Integer k2;

    private Integer k3;

    private String detailsImgPath;

    private LocalDate createTime;

    @Override
    public String toString() {
        return "NewProduct{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                ", info='" + info + '\'' +
                ", k1=" + k1 +
                ", k2=" + k2 +
                ", k3=" + k3 +
                ", detailsImgPath='" + detailsImgPath + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public NewProduct(Integer id, Integer pid, String name, BigDecimal price, Integer sales, Integer stock, String imgPath, String info, Integer k1, Integer k2, Integer k3, String detailsImgPath, LocalDate createTime) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
        this.info = info;
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.detailsImgPath = detailsImgPath;
        this.createTime = createTime;
    }

    public NewProduct() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getK1() {
        return k1;
    }

    public void setK1(Integer k1) {
        this.k1 = k1;
    }

    public Integer getK2() {
        return k2;
    }

    public void setK2(Integer k2) {
        this.k2 = k2;
    }

    public Integer getK3() {
        return k3;
    }

    public void setK3(Integer k3) {
        this.k3 = k3;
    }

    public String getDetailsImgPath() {
        return detailsImgPath;
    }

    public void setDetailsImgPath(String detailsImgPath) {
        this.detailsImgPath = detailsImgPath == null ? null : detailsImgPath.trim();
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }
}