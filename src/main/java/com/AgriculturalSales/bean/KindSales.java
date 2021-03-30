package com.AgriculturalSales.bean;

import java.time.LocalDate;
import java.util.Date;

public class KindSales {
    private Integer id;

    private String k1Name;

    private Integer k1;

    private Integer sales;

    private LocalDate creatTime;

    @Override
    public String toString() {
        return "KindSales{" +
                "id=" + id +
                ", k1Name='" + k1Name + '\'' +
                ", k1=" + k1 +
                ", sales=" + sales +
                ", creatTime=" + creatTime +
                '}';
    }

    public KindSales() {
    }

    public KindSales(Integer id, String k1Name, Integer k1, Integer sales, LocalDate creatTime) {
        this.id = id;
        this.k1Name = k1Name;
        this.k1 = k1;
        this.sales = sales;
        this.creatTime = creatTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getK1Name() {
        return k1Name;
    }

    public void setK1Name(String k1Name) {
        this.k1Name = k1Name == null ? null : k1Name.trim();
    }

    public Integer getK1() {
        return k1;
    }

    public void setK1(Integer k1) {
        this.k1 = k1;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public LocalDate getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDate creatTime) {
        this.creatTime = creatTime;
    }
}