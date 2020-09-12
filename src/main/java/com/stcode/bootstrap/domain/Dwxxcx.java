package com.stcode.bootstrap.domain;


import java.util.Date;

/**
 * 单位信息查询[dwxxcx]
 */
public class Dwxxcx {

    private String dwid;
    private String id;

    //经代办机构
    private String dbjg;
    private String[] dbjgs;

    //险种
    private String xz;
    //统一社会信用代码
    private String xydm;
    //单位名称
    private String dwmc;
    //单位类型
    private String dwlx;
    //日期
    private String dateFrom;
    private String dateTo;
    //机关事业标识
    private String jgsybs;
    //增加方式
    private String zjfs;
    //缴费类型
    private String jflx;
    //隶属关系
    private String lsgx;
    //行业性质(代码
    private String hydm;
    //经办人
    private String jbr;
    //重点关注
    private String zdgz;
    //检查类型
    private String jclx;

    //目录
    private String xm;

    private String[] dmids;
    private String[] formIds;

    //
    private String dmmxid;
    // 代码类别ID
    private String dmlbid;
    //
    private String mxmc;

    private String memo;
    private String jcid;

    //后续抽出占时使用 检查结果类
    private String jcr;
    private Date jcrq;
    private String jc;
    private String cxjc;
    private String jcjg;

    //三险单位增减信息
    //单位全称
    private String dwdm;
    //缴费经(代)办机构
    private String ssqx;
    //社保登记号
    private String djzh;
    //增减日期
    private String zjrq;
    //养老
    private String yl;
    //失业
    private String sy;
    //工伤
    private String gs;
    //机关养老
    private String jgyl;

    //分页
    private String offset;
    private String limit;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getYl() {
        return yl;
    }

    public void setYl(String yl) {
        this.yl = yl;
    }

    public String getSy() {
        return sy;
    }

    public void setSy(String sy) {
        this.sy = sy;
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public String getJgyl() {
        return jgyl;
    }

    public void setJgyl(String jgyl) {
        this.jgyl = jgyl;
    }

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    public String getSsqx() {
        return ssqx;
    }

    public void setSsqx(String ssqx) {
        this.ssqx = ssqx;
    }

    public String getDjzh() {
        return djzh;
    }

    public void setDjzh(String djzh) {
        this.djzh = djzh;
    }

    public String getZjrq() {
        return zjrq;
    }

    public void setZjrq(String zjrq) {
        this.zjrq = zjrq;
    }

    public String getJcr() {
        return jcr;
    }

    public void setJcr(String jcr) {
        this.jcr = jcr;
    }

    public Date getJcrq() {
        return jcrq;
    }

    public void setJcrq(Date jcrq) {
        this.jcrq = jcrq;
    }

    public String getJc() {
        return jc;
    }

    public void setJc(String jc) {
        this.jc = jc;
    }

    public String getCxjc() {
        return cxjc;
    }

    public void setCxjc(String cxjc) {
        this.cxjc = cxjc;
    }

    public String getJcjg() {
        return jcjg;
    }

    public void setJcjg(String jcjg) {
        this.jcjg = jcjg;
    }

    public String getDwid() {
        return dwid;
    }

    public void setDwid(String dwid) {
        this.dwid = dwid;
    }

    public String getJcid() {
        return jcid;
    }

    public void setJcid(String jcid) {
        this.jcid = jcid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDmmxid() {
        return dmmxid;
    }

    public void setDmmxid(String dmmxid) {
        this.dmmxid = dmmxid;
    }

    public String[] getFormIds() {
        return formIds;
    }

    public void setFormIds(String[] formIds) {
        this.formIds = formIds;
    }

    public String getDmlbid() {
        return dmlbid;
    }

    public void setDmlbid(String dmlbid) {
        this.dmlbid = dmlbid;
    }

    public String getMxmc() {
        return mxmc;
    }

    public void setMxmc(String mxmc) {
        this.mxmc = mxmc;
    }

    public String[] getDmids() {
        return dmids;
    }

    public void setDmids(String[] dmids) {
        this.dmids = dmids;
    }

    public String[] getDbjgs() {
        return dbjgs;
    }

    public void setDbjgs(String[] dbjgs) {
        this.dbjgs = dbjgs;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDbjg() {
        return dbjg;
    }

    public void setDbjg(String dbjg) {
        this.dbjg = dbjg;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDwlx() {
        return dwlx;
    }

    public void setDwlx(String dwlx) {
        this.dwlx = dwlx;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getJgsybs() {
        return jgsybs;
    }

    public void setJgsybs(String jgsybs) {
        this.jgsybs = jgsybs;
    }

    public String getZjfs() {
        return zjfs;
    }

    public void setZjfs(String zjfs) {
        this.zjfs = zjfs;
    }

    public String getJflx() {
        return jflx;
    }

    public void setJflx(String jflx) {
        this.jflx = jflx;
    }

    public String getLsgx() {
        return lsgx;
    }

    public void setLsgx(String lsgx) {
        this.lsgx = lsgx;
    }

    public String getHydm() {
        return hydm;
    }

    public void setHydm(String hydm) {
        this.hydm = hydm;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public String getZdgz() {
        return zdgz;
    }

    public void setZdgz(String zdgz) {
        this.zdgz = zdgz;
    }

    public String getJclx() {
        return jclx;
    }

    public void setJclx(String jclx) {
        this.jclx = jclx;
    }
}
