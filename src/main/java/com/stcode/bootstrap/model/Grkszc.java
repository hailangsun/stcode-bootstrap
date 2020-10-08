package com.stcode.bootstrap.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 监督检查-日常检查-跨省转出人员信息查询
 * [grkszc -> 个人跨省转出]
 */
@Data
public class Grkszc {

    //个人id
    private String grid;
    //代办机构
    private String dbjg;
    //多选
    private String[] dbjgs;
    //统一社会信用代码 [组织机构代码]
    private String dwdm;
    //单位ID -> GR_YLJF_ZYD
    private String dwid;
    //单位名称 -> DW_XX
    private String dwmc;

    //公民身份证号码 -> 社会保障号码 ->GR_XX
    private String bzhm;
    //日期起
    private String datefrom;
    //日期止
    private String dateto;
    //转入地行政区划代码 -> 转入地行政区划 -> GR_JFPZ_LXH
    private String zrxzqh;
    //转出地行政区划代码 -> 转出地行政区划 -> GR_JFPZ_LXH
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
    private String xxbjbr;

    //养老资金财务支付情况 -> 转移支出总额 没找到
    private String zyyyyy;

    //养老资金财务支付情况 -> 统筹基金转移额 -> 统筹基金转移总额 ->GR_YLJF_ZYD
    private String tcjjzyze;
    //本地参保期间个人账户存储额(元) - >养老资金财务支付情况 -> 个人账户转移额 ->个人账户转移总额 ->GR_YLJF_ZYD
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

    /**
     * 检查结果jcjg
     */
    //检查结果ID
    private String[] formIds;
    //个人ID
    private String[] grIds;
    //公司id
    private String[] dwids;
    //备注
    private String memo;
    //接收个人ID和单位id
    List<JcjgVo> jcjgs;

    //检查人
    private String jcr;
    //检查日期
    private String jcrq;
    //检查
    private String jc;
    //重新检查
    private String cxjc;
    //检查结果
    private String jcjg;
    //检查 id
    private String jcid;
    //检查审核人
    private String jcshr;
    //检查审核日期
    private String jcshrq;
    //检查审核
    private String jcsh;
    //检查审核结果
    private String jcshjg;
    //检查审核备注
    private String jcshbz;

    // 细单 基本养老参保缴费凭证 后续有时间抽取公共类，改为继承方式
    //姓名
    private String xm;
    // todo 个人编号 BXH原个人编号 ->Gr_jfpz_lxh
    private String bxh;
    //编号 Gr_jfpz_lxh
    private String gdbh;
    //户籍 户籍属地 GR_XX
    private String hjsd;
    //在本地参保起止时间 - 本地缴费起日期 GR_JFPZ
    private String qrq;
    //本地实际缴费月数 在本地实际缴费月数 GR_YLJF_ZYD
    private String bdjfys;

    //todo 行政区划代码 ->参保地行政区划代码 GR_YLJF_ZYD 字段无效
    private String xzqh;

    //电话 联系电话 ->GR_YLJF_ZYD
    private String lxdh;
    //地址 邮寄地址 ->GR_YLJF_ZYD
    private String yjdz;
    //邮编 居住地邮政编码 -> GR_XX
    private String yzbm;

    /**
     * 细单 基本养老保险缴费账户转移联系函
     */
    //原参保地社保机构名称 参保地社保机构名称 ->GR_JFPZ_LXH
    private String jfqxmc;

    //原在你处的参保人员 -> 参保人员姓名 GR_JFPZ_LXH
    private String cbryxm;
    //新就业地社保机构开户全称 GR_JFPZ_LXH
    private String khqc;
    //新就业地社保机构开户银行 GR_JFPZ_LXH
    private String khyh;
    //新就业地社保机构银行账号 GR_JFPZ_LXH
    private String yhzh;
    //新就业地社保机构地址 GR_JFPZ_LXH
    private String sbjgdz;
    //新就业地社保机构邮政编码 GR_JFPZ_LXH
    private String sbjgdzyzbm;
    //新就业地社保机构	新就业的社保机构章  汉字 GR_JFPZ_LXH
    private String newjfqxname;

    /**
     * 细单 参保人基本信息
     */
    //出生日期
    private String csrq;
    //参加工作时间 - >GR_YLJF_ZYD
    private String gzrq;
    //首次参保地实行个人缴费时间 ->  GR_YLJF_ZYD
    private String cbdgrjfrq;
    //本人首次缴费时间 ->GR_YLJF_ZYD
    private String scjfrq;
    //本人建立个人账户时间 GR_YLJF_ZYD
    private String grzhrq;
    //在本地缴费起始时间  GR_YLJF_ZYD
    private String bdjfqrq;
    //在本地缴费终止时间 GR_YLJF_ZYD
    private String bdjfzrq;
    //在本地实际缴费时间 - >在本地实际缴费月数 GR_YLJF_ZYD

    //转移日期 GR_YLJF_ZYD
    private String zyrq;
    //1998年1月1日前账户中个人缴费累计存储额1 -> 1998年1月1日前个人缴费累计存储额 GR_YLJF_ZYD
    private String grjf_98q;
    //1998年1月1日至调转上年末个人账户累计存储存额2 -> 1998年1月1日后个人账户累计存储额 GR_YLJF_ZYD
    private String grzh_98h;
    //调转当年计入个人账户本金金额3
    private String dngrzhbj;
    //个人账户基金转移额4 - > 个人账户转移总额 GR_YLJF_ZYD
    // private String grzhzyze;
    //统筹基金转移额5 ->统筹基金转移总额 GR_YLJF_ZYD
//    private String tcjjzyze;
//    转移基金总额6 ->转移基金总额  GR_YLJF_ZYD
    private String zyjjze;
    //行政区划名称
    private String xzmc;
    //年份 GR_YLJF_ZYD_JXMX
    private String nian;
    //缴费起止时间
    //月缴费基数 GR_YLJF_ZYD_JXMX
    private String yjfjs;
    //缴费月数 GR_YLJF_ZYD_JXMX
    private String ys;
    //缴费比例 单位 GR_YLJF_ZYD_JXMX
    private String dwjfbl;
    //缴费比例 划入个人账户比例 GR_YLJF_ZYD_JXMX
    private String dwhzbl;
    //缴费比例 个人 GR_YLJF_ZYD_JXMX
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


    //是否全部检查，用于判断是本页检查还是全部检查
    private String checkFlag;

}
