package com.AgriculturalSales.bean;

import java.time.LocalDate;
import java.util.Date;

public class Reply {
    private Integer id;

    private String evaluationText;

    private LocalDate createTime;

    private String oid;

    private String uName;

    private Integer pid;

    private Integer eid;

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", evaluationText='" + evaluationText + '\'' +
                ", createTime=" + createTime +
                ", oid='" + oid + '\'' +
                ", uName='" + uName + '\'' +
                ", pid=" + pid +
                ", eid=" + eid +
                '}';
    }

    public Reply() {
    }

    public Reply(Integer id, String evaluationText, LocalDate createTime, String oid, String uName, Integer pid, Integer eid) {
        this.id = id;
        this.evaluationText = evaluationText;
        this.createTime = createTime;
        this.oid = oid;
        this.uName = uName;
        this.pid = pid;
        this.eid = eid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvaluationText() {
        return evaluationText;
    }

    public void setEvaluationText(String evaluationText) {
        this.evaluationText = evaluationText == null ? null : evaluationText.trim();
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}