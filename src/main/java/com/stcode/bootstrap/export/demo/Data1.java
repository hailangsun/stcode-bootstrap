package com.stcode.bootstrap.export.demo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(25)
public class Data1 {

    @ExcelProperty("账号")
    private String account;
    @ExcelProperty("密码")
    private String password;

}
