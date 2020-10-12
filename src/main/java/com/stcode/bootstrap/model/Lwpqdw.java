package com.stcode.bootstrap.model;

import lombok.Data;

import java.util.List;

/**
 * 2.1.35 监督检查-日常检查-劳务派遣单位信息查询
 * [lwpqdw -> 劳务派遣单位]
 */
@Data
public class Lwpqdw extends Jcjg{

    //经代办机构
    private String dbjg;
    private String[] dbjgs;


    //统一社会信用代码
    private String dwdm;
    //单位名称
    private String dwmc;
    //单位类型
    private String dwlx;
    //行业性质
    private String tradeproperty;
    //增减原因
    private String zjfs;
    //增减时间
    private String zjrq;
    //经办人
    private String jbr;




//    xx.jfqx, 				-- 经代办机构 -> '缴费区县  (dmm=9007)'
//            ( CASE WHEN zj.XZDM = '01' THEN '养老' ELSE NULL END ) yl,		-- 险种状态 养老
//            ( CASE WHEN zj.XZDM = '02' THEN '失业' ELSE NULL END ) sy,		-- 险种状态 失业
//            ( CASE WHEN zj.XZDM = '04' THEN '工伤' ELSE NULL END ) gs,		-- 险种状态 工伤
//							-- 企业类型
//            jcjg,jcid,
//    jcjg.jcr,				-- 检查人
//    jcjg.jcrq,				-- 检查日期
//    jcjg.jc,				-- 检查
//    jcjg.cxjc,				-- 重新检查
//    jcjg.jcjg,				-- 检查结果
//    jcjg.jcsh,				-- 审核
//    jcjg.jcshr,				-- 审核人
//    jcjg.jcshrq,			-- 审核日期
//    jcjg.jcshjg				-- 审核结果


    /**************通用开始****************/
    //目录
    private String xm;
    private String[] dmids;
    //分页
    private String offset;
    private String limit;
    //检查结果ID
    private String[] formIds;
    //个人ID
    private String[] grIds;
    //公司id
    private String[] dwids;
    //接收个人ID和单位id
    List<JcjgVo> jcjgs;
    /**
     * 主页导出用
     */
    private String mainoffset;
    private String mainlimit;
    //是否全部检查，用于判断是本页检查还是全部检查
    private String checkFlag;
    //是否分页导出
    private String isAllExprot;
    /**************通用结束****************/

}
