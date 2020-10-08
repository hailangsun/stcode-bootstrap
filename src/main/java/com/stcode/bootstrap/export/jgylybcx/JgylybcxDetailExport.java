package com.stcode.bootstrap.export.jgylybcx;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * 监督检查-日常检查-机关养老月报外支付查询 二级页面导出
 */
@Data
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(25)
public class JgylybcxDetailExport {
    @ColumnWidth(40)
    @ExcelProperty({"机关事业养老保险月报外支付明细", "单位名称"})
    private String dwmc;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "统一社区信用代码"})
    private String dwdm;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "报表日期"})
    private String bbrq;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "姓名"})
    private String grname;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "公民身份证号"})
    private String bzhm;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "补支原因"})
    private String bzyy;
    //    @ExcelProperty({"机关事业养老保险月报外支付明细", "补支月数"})
    //    private String bzys;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "支付金额"})
    private String zfje;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "基础养老金"})
    private String zfjc;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "个人账户"})
    private String zfzh1;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "过渡养老金"})
    private String zfgd;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "基础补贴"})
    private String ylj3;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "建国前补贴"})
    private String ylj4;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "调整机制"})
    private String ylj5;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "护理费"})
    private String hlf;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "统筹负担"})
    private String tcfd;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "发放方式"})
    private String fffs;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "发放地点"})
    private String ffdd;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "经办人"})
    private String jbr;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "经（代）办机构"})
    private String zfqx;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "办理日期"})
    private String blrq;

}
