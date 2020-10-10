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
public class DwxxcxZjExport {

    @ColumnWidth(40)
    @ExcelProperty({"三险单位增减信息", "单位名称"})
    private String dwmc;
    @ExcelProperty({"三险单位增减信息", "组织机构代码"})
    private String dwdm;
    @ExcelProperty({"三险单位增减信息", "缴费经(代)办机构"})
    private String jfqx;
    @ExcelProperty({"三险单位增减信息", "社保登记证号"})
    private String djzh;
    @ExcelProperty({"三险单位增减信息", "险种状态","养老"})
    private String yl;
    @ExcelProperty({"三险单位增减信息", "险种状态","失业"})
    private String sy;
    @ExcelProperty({"三险单位增减信息", "险种状态","工伤"})
    private String gs;
    @ExcelProperty({"三险单位增减信息", "险种状态","机关养老"})
    private String jgyl;
    @ExcelProperty({"三险单位增减信息", "增减日期"})
    private String zjrq;
    @ExcelProperty({"三险单位增减信息", "增减原因"})
    private String memo;
    @ExcelProperty({"三险单位增减信息", "经办人"})
    private String jbr;
    @ExcelProperty({"三险单位增减信息", "经办机构"})
    private String zfqx;

}
