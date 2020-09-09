package com.stcode.bootstrap.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.util.Date;

@Data
@HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 245)
public class ComplexHeadData {
    @ExcelProperty({"主标题", "字符串标题"})
    private String string;
    @ExcelProperty({"主标题", "日期标题"})
    private Date date;
    @ExcelProperty({"主标题", "数字标题"})
    private Double doubleData;

    @ExcelProperty({"主标题","二级表头", "测试1"})
    private String test1;
    @ExcelProperty({"主标题", "二级表头", "测试2"})
    private String test2;
    @ExcelProperty({"主标题", "二级表头", "测试3"})
    private String test3;
    @ExcelProperty({"主标题", "测试4"})
    private String test4;
    @ExcelProperty({"主标题", "测试5"})
    private String test5;
    @ExcelProperty({"主标题", "测试6"})
    private String test6;


}