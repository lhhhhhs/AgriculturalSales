package com.AgriculturalSales.bean;

public class User {
    private Integer id;

    private String phone;

    private String pwd;

    private Integer kind;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", kind=" + kind +
                '}';
    }

    public User() {
    }

    public User(Integer id, String phone, String pwd, Integer kind) {
        this.id = id;
        this.phone = phone;
        this.pwd = pwd;
        this.kind = kind;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }
}