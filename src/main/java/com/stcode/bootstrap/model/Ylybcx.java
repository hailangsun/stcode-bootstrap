package com.stcode.bootstrap.model;

import lombok.Data;

import java.util.Date;

/**
 * 监督检查-日常检查-职工养老月报外支付查询 model
 */
@Data
public class Ylybcx {
    /**
     *前端条件
     */
    //代办机构
    private String dbjg;
    //多选
    private String[] dbjgs;
    //统一社会信用代码
    private String dwdm;
    //汇总日期起
    private String hzDateFrom;
    //汇总日期止
    private String hzDateTo;
    //汇总人
    private String hzr;
    //报表日期
    private String bbrq;
    //支付原因
    private String bzyy;
    //业务转财务标志
    private String cwbz;
    //业务标志
    private String ywbz;
    //支付金额大于
    private String zfje;
    //支付次数大于
    private String zfcs;
    //附言
    private String fuyan;
    //发放地点
    private String fffs;
    //月报生成日期
    private String scrq;
    //检查类型
    private String shbz;
    //支付人数合计
    private String zfrs;

    /**
     * 查询返回字段
     */
    //业务序号
    private String ywxh;
    //单位ID
    private String dwid;
    //单位全称
    private String dwmc;
    //经办人
    private String jbr;
    //业务办理日期
    private String blrq;
    //补支人数
    private String bzrs;
    //审核人:
    private String shr;
    //检查人数
    private String jcrs;
    //合计金额
    private String hjje;

    /**
     * 分页
     */
    private String offset;
    private String limit;

    /**
     * 职工养老月报外支付人员明细
     */
    //项目
    private String xm;
    //姓名
    private String name;
    //公民身份证号码:
    private String bzhm;
    //补发原因
    private String bfyy;
    //检查人
    private String jcr;
    //检查日期
    private Date jcrq;
    //检查
    private String jc;
    //重新检查
    private String cxjc;
    //检查结果
    private String jcjg;
    //备注
    private String memo;

}
