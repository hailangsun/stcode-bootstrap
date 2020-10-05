package com.stcode.bootstrap.export.ylybcx;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * 首页查询 - 职工养老月报外支付 导出
 */
@Data
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(25)
public class YlybcxExport {
    @ColumnWidth(40)
    @ExcelProperty({"职工养老月报外支付", "单位名称"})
    private String dwmc;
    @ExcelProperty({"职工养老月报外支付", "统一社区信用代码"})
    private String dwdm;
    @ExcelProperty({"职工养老月报外支付", "报表日期"})
    private String bbrq;
    @ExcelProperty({"职工养老月报外支付", "经办人"})
    private String jbr;
    @ExcelProperty({"职工养老月报外支付", "业务办理日期"})
    private String blrq;
    @ExcelProperty({"职工养老月报外支付", "月报生成日期"})
    private String scrq;
    @ExcelProperty({"职工养老月报外支付", "补支人数"})
    private String zfrs;
    @ExcelProperty({"职工养老月报外支付", "检查人数"})
    private String jcrs;
    @ExcelProperty({"职工养老月报外支付", "合计金额"})
    private String tcfd;
    @ExcelProperty({"职工养老月报外支付", "支付次数"})
    private String zfcs;
    @ExcelProperty({"职工养老月报外支付", "附言"})
    private String fuyan;
    @ExcelProperty({"职工养老月报外支付", "发放地点"})
    private String ffdd;
    @ExcelProperty({"职工养老月报外支付", "经代办机构"})
    private String zfqx;

}
