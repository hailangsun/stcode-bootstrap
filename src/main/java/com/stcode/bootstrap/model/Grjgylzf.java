package com.stcode.bootstrap.model;

import lombok.Data;

/**
 * 1.2.22  监督检查-日常检查-机关养老月报外支付查询
 * [Grjgylzf -> 个人机关养老支付]
 */
@Data
public class Grjgylzf extends Jcjg{
    /**
     * 条件查询
     */
    private String grid;
    //代办机构
    private String dbjg;
    //多选
    private String[] dbjgs;
    //统一社会信用代码 [组织机构代码]
    private String dwdm;
    //报表日期起
    private String hzDateFrom;
    //报表日期止
    private String hzDateTo;
    //名称
    private String grname;
    //公民身份证号
    private String bzhm;
    //附言
    private String fuyan;
    //经办人
    private String jbr;
    //补支金额大于
    private String bzjgdy;
    //检查类型
    private String jclx;
    //审核状态
    private String shbz;


    /**
     * 一级页面 返回结果
     */
    //单位名称
    private String dwmc;
    //报表日期
    private String bbrq;
    //补支原因
    private String bzyy;
    //经（代）办机构
    private String zfqx;
    //办理日期
    private String blrq;

    /**
     * 点击名称 二级页面
     */
    //支付金额
    private String zfje;
    //基础养老金
    private String zfjc;
    //个人账户
    private String zfzh1;
    //过渡养老金
    private String zfgd;
    //基础补贴
    private String ylj3;
    //建国前补贴
    private String ylj4;
    //调整机制
    private String ylj5;
    //护理费
    private String hlf;
    //统筹负担
    private String tcfd;
    //发放方式 (dmm=0290)
    private String fffs;
    //发放地点(dmm=0233)
    private String ffdd;

    /**
     * 检查结果jcjg
     */
    private String[] formIds;
    private String[] grIds;

    /**
     * 分页
     */
    private String offset;
    private String limit;


}
