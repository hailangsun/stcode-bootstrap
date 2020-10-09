package com.stcode.bootstrap.export.grjgzc;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * 首页查询 - 机关事业单位转出人员信息查询 导出
 */
@Data
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(25)
public class GrjgzcExport {
    @ExcelProperty({"机关事业单位转出人员信息查询", "统一社区信用代码"})
    private String dwdm;
    @ColumnWidth(40)
    @ExcelProperty({"机关事业单位转出人员信息查询", "单位名称"})
    private String dwmc;
    @ExcelProperty({"机关事业单位转出人员信息查询", "社会保障号码"})
    private String bzhm;
    @ExcelProperty({"机关事业单位转出人员信息查询", "姓名"})
    private String grname;
    @ExcelProperty({"机关事业单位转出人员信息查询", "性别"})
    private String xb;
    @ExcelProperty({"机关事业单位转出人员信息查询", "险种"})
    private String xzlx;
    @ExcelProperty({"机关事业单位转出人员信息查询", "转出地(原参保地址)"})
    private String zcsbmc;
    @ExcelProperty({"机关事业单位转出人员信息查询", "转入地(新参保地)"})
    private String zrsbmc;
    @ExcelProperty({"机关事业单位转出人员信息查询", "缴费凭证","打印日期"})
    private String dyrq;
    @ExcelProperty({"机关事业单位转出人员信息查询", "缴费凭证","经办人"})
    private String jbr;
    @ExcelProperty({"机关事业单位转出人员信息查询", "联系函","经办人"})
    private String lxhjbr;
    @ExcelProperty({"机关事业单位转出人员信息查询", "联系函","办理日期"})
    private String lxhblrq;
    @ExcelProperty({"机关事业单位转出人员信息查询", "信息表","生成日期"})
    private String xxbscrq;
    @ExcelProperty({"机关事业单位转出人员信息查询", "信息表","经办人"})
    private String xxbjbr;
    @ExcelProperty({"机关事业单位转出人员信息查询", "信息表","是否上传网部"})
    private String zt;
    @ExcelProperty({"机关事业单位转出人员信息查询", "养老资金财务支付情况","转移支出总额"})
    private String zcje;
    @ExcelProperty({"机关事业单位转出人员信息查询", "养老资金财务支付情况","统筹基金转移额"})
    private String tcjjzyze;
    @ExcelProperty({"机关事业单位转出人员信息查询", "养老资金财务支付情况","个人账户转移额"})
    private String grzhzyze;
    @ExcelProperty({"机关事业单位转出人员信息查询", "养老资金财务支付情况","财务付款日期"})
    private String zy4;
    @ExcelProperty({"机关事业单位转出人员信息查询", "养老资金财务支付情况","财务付款经办人"})
    private String zy5;
    @ExcelProperty({"机关事业单位转出人员信息查询", "职业年金支付情况","职业年金支出总额"})
    private String sqyzfje;
    @ExcelProperty({"机关事业单位转出人员信息查询", "职业年金支付情况","职业年金报盘日期"})
    private String bpscrq;
    @ExcelProperty({"机关事业单位转出人员信息查询", "职业年金支付情况","职业年金回盘日期"})
    private String hpblrq;
    @ExcelProperty({"机关事业单位转出人员信息查询", "职业年金支付情况","回盘经办人"})
    private String hpjbr;
    @ExcelProperty({"基本养老保险转出情况", "检查人"})
    private String jcr;
    @ExcelProperty({"基本养老保险转出情况", "检查日期"})
    private String jcrq;
    @ExcelProperty({"基本养老保险转出情况", "检查"})
    private String jc;
    @ExcelProperty({"基本养老保险转出情况", "重新检查"})
    private String cxjc;
    @ExcelProperty({"基本养老保险转出情况", "备注"})
    private String memo;
    @ExcelProperty({"基本养老保险转出情况", "检查审核人"})
    private String jcshr;
    @ExcelProperty({"基本养老保险转出情况", "检查审核日期"})
    private String jcshrq;
    @ExcelProperty({"基本养老保险转出情况", "检查审核"})
    private String jcsh;
    @ExcelProperty({"基本养老保险转出情况", "检查审核结果"})
    private String jcshjg;

}
