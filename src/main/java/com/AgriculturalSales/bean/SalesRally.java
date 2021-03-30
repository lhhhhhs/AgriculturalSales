package com.AgriculturalSales.bean;

import java.time.LocalDate;
import java.util.Date;

public class SalesRally {
    private Integer id;

    private Integer pid;

    private String pname;

    private Integer sales;

    private LocalDate creatTime;

    public SalesRally() {
    }

    @Override
    public String toString() {
        return "SalesRally{" +
                "id=" + id +
                ", pid=" + pid +
                ", pname='" + pname + '\'' +
                ", sales=" + sales +
                ", creatTime=" + creatTime +
                '}';
    }

    public SalesRally(Integer id, Integer pid, String pname, Integer sales, LocalDate creatTime) {
        this.id = id;
        this.pid = pid;
        this.pname = pname;
        this.sales = sales;
        this.creatTime = creatTime;
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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
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