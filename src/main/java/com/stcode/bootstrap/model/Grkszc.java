package com.stcode.bootstrap.model;

import lombok.Data;

/**
 * 监督检查-日常检查-跨省转出人员信息查询
 * [grkszc -> 个人跨省转出]
 */
@Data
public class Grkszc {

    //代办机构
    private String dbjg;
    //多选
    private String[] dbjgs;
    //统一社会信用代码 [组织机构代码]
    private String dwdm;
    //单位ID
    private String dwid;
    //单位名称
    private String dwmc;

    //公民身份证号码 -> 社会保障号码
    private String bzhm;
    //日期起
    private String dateFrom;
    //日期止

    private String dateTo;
    //转入地行政区划代码 -> 转入地行政区划
    private String zrxzqh;
    //转出地行政区划代码 -> 转出地行政区划
    private String zcxzqh;
    //姓名
    private String grname;
    //检查类型 ->
    private String shlx;


    /**
     * 返回
     */
    //性别 -> 性别  (dmm=0201) -> GR_XX
    private String xb;
    //账户类型 ->帐户类别  dmm=0313 -> GR_YLJF_ZYD
    private String zhlb;
    //转出地(原参保地址) -> 转出社保名称 -> GR_YLJF_ZYD
    private String zcsbmc;
    //转入地(新参保地址) -> 转入社保名称 -> GR_YLJF_ZYD
    private String zrsbmc;
    // 缴费凭证 -> 打印日期 -> GR_JFPZ
    private String dyrq;
    // 缴费凭证 -> 经办人 -> GR_JFPZ
    private String jbr;
    // 联系函 -> 办理日期 -> GR_JFPZ_LXH
    private String blrq;
    //联系函 -> 经办人 -> GR_JFPZ_LXH
    private String lxhjbr;
    //信息表 -> 是否上传网络 -> 部网上传状态 0 已上传  1和空 未上传 -> GR_YLJF_ZYD
    private String zt;
    //信息表 -> 生成日期 -> 信息表上传日期 -> GR_YLJF_ZYD
    private String xxbscrq;
    //信息表 -> 经办人 -> GR_YLJF_ZYD
    private String zydjbr;

    //养老资金财务支付情况 -> 转移支出总额 没找到
    private String zyyyyy;

    //养老资金财务支付情况 -> 统筹基金转移额 -> 统筹基金转移总额 ->GR_YLJF_ZYD
    private String tcjjzyze;
    //养老资金财务支付情况 -> 个人账户转移额 ->个人账户转移总额 ->GR_YLJF_ZYD
    private String grzhzyze;

    //养老资金财务支付情况 -> 财务付款日期 -> 没找到
    private String cwfkrq;

    //养老资金财务支付情况 -> 财务付款经办人 -> 没找到
    private String cwfkjbr;


    /**
     * 分页
     */
    private String offset;
    private String limit;
}
