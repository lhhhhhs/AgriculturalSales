package com.AgriculturalSales.bean;

public class Kind3 {
    private Integer id;

    private String name;

    private Integer k1;

    private Integer k2;

    private String info;

    public Kind3(Integer id, String name, Integer k1, Integer k2, String info) {
        this.id = id;
        this.name = name;
        this.k1 = k1;
        this.k2 = k2;
        this.info = info;
    }

    public Kind3() {
    }


    @Override
    public String toString() {
        return "Kind3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", k1=" + k1 +
                ", k2=" + k2 +
                ", info='" + info + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}