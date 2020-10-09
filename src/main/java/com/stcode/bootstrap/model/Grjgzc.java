package com.stcode.bootstrap.model;

import lombok.Data;

import java.util.List;

/**
 *1.2.27监督检查-日常检查-机关事业单位转出人员信息查询
 * [Grjgzc -> 个人机关转出]
 */
@Data
public class Grjgzc extends Jcjg {

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


    //统一社会信用代码 [组织机构代码]
    private String dwdm;
    //单位名称 -> DW_XX
    private String dwmc;

    //公民身份证号码 -> 社会保障号码 ->GR_XX
    private String bzhm;
    //姓名
    private String grname;
    //性别 -> 性别  (dmm=0201) -> GR_XX
    private String xb;
    //险种 -> XZLX -> 险种类型 Gr_jgsy_jfpz_lxh
    private String xzlx;
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
    private String xxbjbr;

    /**
     * 养老资金财务支付情况
     */
    //转移支出总额 -> 实际转出金额 GR_YLJF_ZYD
    private String zcje;
    //统筹基金转移额
    private String tcjjzyze;
    //个人账户转移额
    private String grzhzyze;
    //财务付款日期

    //财务付款经办人

    /**
     * 职业年金支付情况
     */
    //职业年金支出总额 ->税前应支付金额 GR_ZYNJ_ZYD_DPMX
    private String sqyzfje;

    //职业年金报盘日期 -> 生成日期 GR_ZYNJ_ZYD_BP
    private String bpscrq;
    //报盘经办人

    //职业年金回盘日期 -> 办理日期 GR_ZYNJ_ZYD_DP
    private String hpblrq;

    //回盘经办人 ->
    private  String hpjbr;

    //在本地参保起止时间
    private String bdqrq;
    //本地实际缴费月数
    private String jfys;
    //本地参保期间个人账户存储额(元)
    private String jgylzhje;
    //职业年金个人账户份额或个人账户存额
    private String zynjzhje;
    //电话 -> 经办机构电话
    private String jbjgdh;
    //地址 -> 经办机构地址
    private String jbjgdz;
    //邮编 -> 经办机构邮编
    private String jbjgyb;
    //联系函细表 原参保地社保机构名称
    private String jfqxmc;
    //联系函细表  原在你处参保人员
    private String cbryxm;
    //新参保险种类型 需转换 机关事业单位养老保险
    private String jgxzlx;
    //新参保险种类型 需转换 企业职工养老保险
    private String qyxzlx;
    // 新就业地职业(企业)年金管理机构 地址
    private String sbjgdz;
    //新就业地职业(企业)年金管理机构 邮政编码
    private String sbjgdzyzbm;
    //新就业地职业(企业)年金管理机构 联系人
    private String jbrname;
    // 新就业地职业(企业)年金管理机构 联系电话
    private String lxdh;
    //新就业地社保机构职业年金	开户全称
    private String khqc;
    //新就业地社保机构职业年金	银行
    private String khyh;
    //新就业地社保机构职业年金	银行12位交换号
    private String khhh;
    // 新就业地社保机构职业年金 银行账号
    private String yhzh;
    private String year;
    private String month;
    private String day;
    //编号
    private String bh;
    //个人编号
    private String gdbh;
    //出生日期
    private String csrq;
    //户籍地址
    private String hjsd;
    //参加工作时间
    private String gzrq;
    //首次参保地实行个人缴费时间
    private String cbdgrjfrq;
    //本人首次缴费时间
    private String scjfrq;
    //本人建立个人账户时间
    private String grzhrq;
    //在本地缴费起始时间
    private String bdjfqrq;
    //在本地缴费终止时间
    private String bdjfzrq;
    //在本地实际缴费时间 ->在本地实际缴费月数
    private String bdjfys;
    //转移日期
    private String zyrq;
    //1998年1月1日前账户中个人缴费累计存储额1
    private String grjf_98q;
    //1998年1月1日至调转上年末个人账户累计存储额2
    private String grzh_98h;
    //调转当年计入个人账户本金金额3
    private String dngrzhbj;
    //转移基金总额6
    private String zyjjze;
    //参保地区 行政区划代码
    private String xzqh;
    //名称
    private String xzmc;
    //年份
    private String nian;
    //缴费起止时间
    private String qrq;
    //缴费月数
    private String ys;
    //月缴费基数
    private String yjfjs;
    //缴费比例 单位
    private String dwjfbl;
    //缴费比例 划入个人账户比例
    private String dwhzbl;
    //缴费比例 个人
    private String grjfbl;
    //当年记账金额 小计
    private String dnjzje;
    //当年记账金额 个人缴费
    private String dngrjf;
    //当年记账利息 小计
    private String dnjzlx;
    //当年记账利息 个人缴费
    private String dngrlx;
    //至本年末账户累计存储额 小计
    private String zbnmljzhje;
    //至本年末账户累计存储额 个人缴费
    private String zbnmljgrjf;
    //备注
    private String memo;
    //个人编号
    private String grbh;
    //转出单位名称
    private String zcdwmc;
    //职业年金总额 ->职业年金补助总额
    private String zynjbzze;
    //记账金额
    private String jzje;
    //实账金额
    private String szje;
    //补记的职业年金金额 ->补记金额
    private String bjje;
    //改革前试点划转金额 ->改革前划转金额
    private String ggqhzje;
    //企业年金金额 -> 企业年金
    private String qynjje;

    //标签页 养老信息表
    //上年度在离职工月平均工资
    private String zgyjgz;

    /**
     * 分页
     */
    private String offset;
    private String limit;

    //日期起
    private String datefrom;
    //日期止
    private String dateto;

    //是否全部检查，用于判断是本页检查还是全部检查
    private String checkFlag;
    //是否分页导出
    private String isAllExprot;
    //检查日期
    private String jcrqtostr;
    //检查审核日期
    private String jcshrqtostr;


    /**
     * 主页导出用
     */
    private String mainoffset;
    private String mainlimit;
}
