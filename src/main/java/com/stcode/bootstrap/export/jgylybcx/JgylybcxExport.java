package com.stcode.bootstrap.export.jgylybcx;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * 监督检查-日常检查-机关养老月报外支付查询 导出
 */
@Data
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(25)
public class JgylybcxExport {
    @ColumnWidth(40)
    @ExcelProperty({"机关事业养老保险月报外支付明细", "单位名称"})
    private String dwmc;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "统一社区信用代码"})
    private String dwdm;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "姓名"})
    private String grname;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "公民身份证号"})
    private String bzhm;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "报表日期"})
    private String bbrq;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "附言"})
    private String fuyan;
//    @ExcelProperty({"机关事业养老保险月报外支付明细", "补支月数"})
//    private String bzys;
//    @ExcelProperty({"机关事业养老保险月报外支付明细", "补支金额"})
//    private String bzje;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "补支原因"})
    private String bzyy;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "经（代）办机构"})
    private String zfqx;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "经办人"})
    private String jbr;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "办理日期"})
    private String blrq;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "检查人"})
    private String jcr;
    @ExcelProperty({"机关事业养老保险月报外支付明细", "检查日期"})
    private String jcrqtostr;
     @ExcelProperty({"机关事业养老保险月报外支付明细", "检查"})
    private String jc;
     @ExcelProperty({"机关事业养老保险月报外支付明细", "重新检查"})
    private String cxjc;
     @ExcelProperty({"机关事业养老保险月报外支付明细", "检查结果"})
    private String jcjg;
     @ExcelProperty({"机关事业养老保险月报外支付明细", "备注"})
    private String memo;
     @ExcelProperty({"机关事业养老保险月报外支付明细", "检查审核人"})
    private String jcshr;
     @ExcelProperty({"机关事业养老保险月报外支付明细", "检查审核日期"})
    private String jcshrqtostr;
     @ExcelProperty({"机关事业养老保险月报外支付明细", "检查审核"})
    private String jcsh;
     @ExcelProperty({"机关事业养老保险月报外支付明细", "检查审核结果"})
    private String jcshjg;

}
