package com.AgriculturalSales.bean;

public class Provinces {
    private Integer id;

    private Integer provinceid;

    private String province;

    @Override
    public String toString() {
        return "Provinces{" +
                "id=" + id +
                ", provinceid=" + provinceid +
                ", province='" + province + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }
}