package com.stcode.bootstrap.domain;

import java.util.Date;

public class DmMx {
    private String dmmxid;

    private String dmlbid;

    private String mxmc;

    private String mxdm;

    private String dmzt;

    private String lrry;

    private Date lrrq;

    public String getDmmxid() {
        return dmmxid;
    }

    public void setDmmxid(String dmmxid) {
        this.dmmxid = dmmxid == null ? null : dmmxid.trim();
    }

    public String getDmlbid() {
        return dmlbid;
    }

    public void setDmlbid(String dmlbid) {
        this.dmlbid = dmlbid == null ? null : dmlbid.trim();
    }

    public String getMxmc() {
        return mxmc;
    }

    public void setMxmc(String mxmc) {
        this.mxmc = mxmc == null ? null : mxmc.trim();
    }

    public String getMxdm() {
        return mxdm;
    }

    public void setMxdm(String mxdm) {
        this.mxdm = mxdm == null ? null : mxdm.trim();
    }

    public String getDmzt() {
        return dmzt;
    }

    public void setDmzt(String dmzt) {
        this.dmzt = dmzt == null ? null : dmzt.trim();
    }

    public String getLrry() {
        return lrry;
    }

    public void setLrry(String lrry) {
        this.lrry = lrry == null ? null : lrry.trim();
    }

    public Date getLrrq() {
        return lrrq;
    }

    public void setLrrq(Date lrrq) {
        this.lrrq = lrrq;
    }
}