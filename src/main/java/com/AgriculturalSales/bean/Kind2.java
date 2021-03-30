package com.AgriculturalSales.bean;

public class Kind2 {
    private Integer id;

    private String name;

    private Integer k1;

    private String info;

    @Override
    public String toString() {
        return "Kind2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", k1=" + k1 +
                ", info='" + info + '\'' +
                '}';
    }

    public Kind2() {
    }

    public Kind2(Integer id, String name, Integer k1, String info) {
        this.id = id;
        this.name = name;
        this.k1 = k1;
        this.info = info;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}