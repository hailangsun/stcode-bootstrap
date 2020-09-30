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




    /**
     * 一级页面
     */



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
