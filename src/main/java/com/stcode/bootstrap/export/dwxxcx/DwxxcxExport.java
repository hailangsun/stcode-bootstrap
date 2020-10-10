package com.stcode.bootstrap.export.dwxxcx;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(25)
public class DwxxcxExport {

    @ColumnWidth(40)
    @ExcelProperty({"参保单位信息", "单位名称"})
    private String dwmc;
    @ExcelProperty({"参保单位信息", "经代办机构"})
    private String ssqx;
    @ExcelProperty({"参保单位信息", "统一社区信用代码"})
    private String dwdm;
    @ColumnWidth(10)
    @ExcelProperty({"参保单位信息", "单位类型"})
    private String dwlx;
    @ExcelProperty({"参保单位信息", "隶属关系"})
    private String lsgx;
    @ExcelProperty({"参保单位信息", "行业类型"})
    private String hydm;
    @ExcelProperty({"参保单位信息", "经办人"})
    private String jbr;
    @ExcelProperty({"参保单位信息", "增减原因"})
    private String zjyy;
    @ExcelProperty({"参保单位信息", "增减时间"})
    private String zjrq;
    @ExcelProperty({"参保单位信息", "险种状态", "养老"})
    private String yl;
    @ExcelProperty({"参保单位信息", "险种状态", "失业"})
    private String sy;
    @ExcelProperty({"参保单位信息", "险种状态", "工伤"})
    private String gs;
    @ExcelProperty({"参保单位信息", "险种状态", "机关养老"})
    private String jgyl;
    @ExcelProperty({"参保单位信息", "险种状态", "城乡居民"})
    private String cxjm;
    @ExcelProperty({"参保单位信息", "险种状态", "老年保障"})
    private String lnbz;
    @ExcelProperty({"参保单位信息", "检查人"})
    private String jcr;
    @ExcelProperty({"参保单位信息", "检查日期"})
    private String jcrq;
    @ExcelProperty({"参保单位信息", "检查"})
    private String jc;
    @ExcelProperty({"参保单位信息", "重新检查"})
    private String cxjc;
    @ExcelProperty({"参保单位信息", "检查结果"})
    private String jcjg;
    @ExcelProperty({"参保单位信息", "备注"})
    private String memo;

}
