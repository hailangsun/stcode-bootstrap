package com.stcode.bootstrap.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 监督检查-日常检查-职工养老月报外支付查询 model
 * [Ylybcx -> 养老月报查询 ]
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
    //统一社会信用代码 [组织机构代码]
    private String dwdm;
    //汇总日期起
    private String hzdatefrom;
    //汇总日期止
    private String hzdateto;
    //月报生成日期起
    private String ybdatefrom;
    //月报生成日期止
    private String ybdateto;
    //汇总人
    private String hzr;
    //报表日期
    private String bbrq;
    //支付原因 补支原因  (dmm=0323)
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
    private String ffdd;
    //月报生成日期
    private String scrq;
    //检查类型
    private String shlx;
    //审核标志 0-未审核 1-已审核 审核状态
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
    private String tcfd;
    //经（代）办机构 '支付区县  (dmm=9007)',
    private String zfqx;



    /**
     * 分页
     */
    private String offset;
    private String limit;
    /**
     * 主页导出用
     */
    private String mainoffset;
    private String mainlimit;

    /**
     * 二级页面细单导出用
     */
    private String detailoffset;
    private String detaillimit;

    /**
     * 职工养老月报外支付人员明细
     */
    private String grid;
    //项目
    private String xm;
    //姓名
    private String grname;
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

    /**
     * 字典表，DM_MX
     */
    //代码明细ID
    private String dmmxid;
    //明细名称
    private String mxmc;


    /**
     * 检查结果jcjg
     */
    private String[] formIds;
    private String[] grIds;

    /**
     * 排序
     */
    private String sortOrder;
    private String sortName;

    /**
     * 一级页面总数 总合计金额
     */
    private String mainTotal;

    //随机数
    private String randomnum;
    //随机检查单位id
    private String randomdwids;
    //放sql注入
    private String[] randomids;
    //是否分页导出
    private String isAllExprot;

    /**
     * 二级页面合计
     */
    //总合计
    private String detailTotal;
    //本页合计
    private String currentDetailTotal;
    //检查id
    private String jcid;

    //接收前台检查信息
    List<JcjgVo> jcjgs;
    //是否全部检查，用于判断是本页检查还是全部检查
    private String checkFlag;

    /**
     * 三级页面字段
     */
    //电脑序号
    private String bxh;
    //人员身份[0206]
    private String grsf;
    //离退休金
    private String ylj1;
    //一次性支付个人账户
    private String zfzh;
    //一次性支付补偿金
    private String zfbc;
    //丧葬抚恤补助 【附言0109】
    //补支基础性养老金[附言0102]
    //补支过渡性养老金
    private String ylj3;
    //补支个人账户
    private String ylj2;
    //取暖补贴[附言0106]
    private String zfqn;
    //护理费
    private String zfhl;
    //异地安置费
    private String zfaz;
    //破产企业统筹外负担
    private String zfpc;
    //救济金
    private String zfjj;

}
