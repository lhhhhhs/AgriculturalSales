package com.AgriculturalSales.bean;

public class Kind1 {
    private Integer id;

    private String name;

    private String info;

    private String showImgPath;

    public Kind1() {
    }

    public Kind1(Integer id, String name, String info, String showImgPath) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.showImgPath = showImgPath;
    }

    @Override
    public String toString() {
        return "Kind1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", showImgPath='" + showImgPath + '\'' +
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getShowImgPath() {
        return showImgPath;
    }

    public void setShowImgPath(String showImgPath) {
        this.showImgPath = showImgPath == null ? null : showImgPath.trim();
    }
}