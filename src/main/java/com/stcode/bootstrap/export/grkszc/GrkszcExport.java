package com.stcode.bootstrap.export.grkszc;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * 首页查询 - 跨省转出人员信息查询 导出
 */
@Data
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(25)
public class GrkszcExport {
    @ExcelProperty({"基本养老保险转出情况", "统一社区信用代码"})
    private String dwdm;
    @ColumnWidth(40)
    @ExcelProperty({"基本养老保险转出情况", "单位名称"})
    private String dwmc;
    @ExcelProperty({"基本养老保险转出情况", "社会保障号码"})
    private String bzhm;
    @ExcelProperty({"基本养老保险转出情况", "姓名"})
    private String grname;
    @ExcelProperty({"基本养老保险转出情况", "性别"})
    private String xb;
    @ExcelProperty({"基本养老保险转出情况", "账户类型"})
    private String zhlb;
    @ExcelProperty({"基本养老保险转出情况", "转出地(原参保地址)"})
    private String zcsbmc;
    @ExcelProperty({"基本养老保险转出情况", "转入地(新参保地)"})
    private String zrsbmc;
    @ExcelProperty({"基本养老保险转出情况", "缴费凭证","打印日期"})
    private String dyrq;
    @ExcelProperty({"基本养老保险转出情况", "缴费凭证","经办人"})
    private String jbr;
    @ExcelProperty({"基本养老保险转出情况", "联系函","经办人"})
    private String lxhjbr;
    @ExcelProperty({"基本养老保险转出情况", "联系函","办理日期"})
    private String lxhblrq;
    @ExcelProperty({"基本养老保险转出情况", "信息表","经办人"})
    private String xxbjbr;
    @ExcelProperty({"基本养老保险转出情况", "信息表","生成日期"})
    private String xxbscrq;
    @ExcelProperty({"基本养老保险转出情况", "信息表","是否上传网部"})
    private String zt;
    @ExcelProperty({"基本养老保险转出情况", "养老资金财务支付情况","转移支出总额"})
    private String zy1;
    @ExcelProperty({"基本养老保险转出情况", "养老资金财务支付情况","统筹基金转移额"})
    private String zy2;
    @ExcelProperty({"基本养老保险转出情况", "养老资金财务支付情况","个人账户转移额"})
    private String zy3;
    @ExcelProperty({"基本养老保险转出情况", "养老资金财务支付情况","财务付款日期"})
    private String zy4;
    @ExcelProperty({"基本养老保险转出情况", "养老资金财务支付情况","财务付款经办人"})
    private String zy5;
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
