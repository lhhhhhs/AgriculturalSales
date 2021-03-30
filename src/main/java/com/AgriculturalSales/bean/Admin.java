package com.AgriculturalSales.bean;

public class Admin {
    private Integer id;

    private String adminName;

    private String adminPs;

    private Integer kind;

    public Admin(Integer id, String adminName, String adminPs) {
        this.id = id;
        this.adminName = adminName;
        this.adminPs = adminPs;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", adminPs='" + adminPs + '\'' +
                ", kind=" + kind +
                '}';
    }

    public Admin() {
    }

    public Admin(Integer id, String adminName, String adminPs, Integer kind) {
        this.id = id;
        this.adminName = adminName;
        this.adminPs = adminPs;
        this.kind = kind;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPs() {
        return adminPs;
    }

    public void setAdminPs(String adminPs) {
        this.adminPs = adminPs == null ? null : adminPs.trim();
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }
}