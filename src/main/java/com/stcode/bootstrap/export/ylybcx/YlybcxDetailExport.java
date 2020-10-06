package com.stcode.bootstrap.export.ylybcx;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * 二级页面 导出
 */
@Data
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(25)
public class YlybcxDetailExport {
    @ColumnWidth(40)
    @ExcelProperty({"职工养老月报外支付人员明细", "单位名称"})
    private String dwmc;
    @ExcelProperty({"职工养老月报外支付人员明细", "统一社区信用代码"})
    private String dwdm;
    @ExcelProperty({"职工养老月报外支付人员明细", "姓名"})
    private String grname;
    @ExcelProperty({"职工养老月报外支付人员明细", "公民身份证号码"})
    private String bzhm;
    @ExcelProperty({"职工养老月报外支付人员明细", "附言"})
    private String fuyan;
    @ExcelProperty({"职工养老月报外支付人员明细", "补发原因"})
    private String bzyy;
    @ExcelProperty({"职工养老月报外支付人员明细", "发放地点"})
    private String ffdd;
    @ExcelProperty({"职工养老月报外支付人员明细", "经办人"})
    private String jbr;
    @ExcelProperty({"职工养老月报外支付人员明细", "合计金额"})
    private String zfje;
    @ExcelProperty({"职工养老月报外支付人员明细", "检查人"})
    private String jcr;
    @ExcelProperty({"职工养老月报外支付人员明细", "检查日期"})
    private String jcrq;
    @ExcelProperty({"职工养老月报外支付人员明细", "检查"})
    private String jc;
    @ExcelProperty({"职工养老月报外支付人员明细", "重新检查"})
    private String cxjc;
    @ExcelProperty({"职工养老月报外支付人员明细", "检查结果"})
    private String jcjg;
    @ExcelProperty({"职工养老月报外支付人员明细", "备注"})
    private String memo;

}
