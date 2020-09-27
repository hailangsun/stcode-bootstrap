package com.stcode.bootstrap.model;

import lombok.Data;

import java.util.List;

/**
 * 1.2.28监督检查-日常检查-城保和职保转移人员信息查询
 */
@Data
public class Grcbzbzy extends Jcjg{


    //个人id
    private String grid;
    //代办机构
    private String dbjg;
    //多选
    private String[] dbjgs;

    //检查结果ID
    private String[] formIds;
    //个人ID
    private String[] grIds;
    //公司id
    private String[] dwids;
    //接收个人ID和单位id
    List<JcjgVo> jcjgs;

    //社会保障号码
    private String bzhm;

    //姓名
    private String grname;
    //性别
    private String xb;
    //联系电话
    private String lxdh;
    //户籍性质
    private String aac009;
    //转移方向 [没找到]
    //居民养老保险转职工养老保险 可折算职工养老保险年限
    private String zsnx;
    //居民养老保险转职工养老保险 折算后职工养老保险缴费年限
    //居民养老保险转职工养老保险 重复缴费清退金额 --没有这个人字段
    //居民养老保险转职工养老保险 转移个人账户存储额
    private String bxhj;
    //居民养老保险转职工养老保险 经办人
    private String jbr;
    //居民养老保险转职工养老保险 办理日期
    private String blrq;
    //职工养老保险转居民养老保险 可折算居民养老保险年限
    private String cnv005;
    //职工养老保险转居民养老保险 折算后居民养老保险缴费年限 [没找到]
    //职工养老保险转居民养老保险 重复缴费清退金额 [没有此字段]
    //职工养老保险转居民养老保险 转移个人账户存储额  ->转出金额
    private String aic083;
    //职工养老保险转居民养老保险 经办人
    private String aae011;
    //职工养老保险转居民养老保险 办理日期
    private String aae035;
    //清退个人账户金额 [没有此字段]




    /**
     * 分页
     */
    private String offset;
    private String limit;
}
