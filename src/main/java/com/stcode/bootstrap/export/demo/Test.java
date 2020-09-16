package com.stcode.bootstrap.export.demo;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private List<WriteSheet> sheets;
    public static void main(String[] args) {

        Test t = new Test();
        t.test00();
        t.test01();
    }




    /**
     * 模拟百万以下数据导入到excel
     */
    private void test00() {

        int num = 0;
        ExcelHandler handler = null;
        ExcelWriter excelWriter = null;
        try {
            //创建handler对象--参数文件夹名
            handler = new ExcelHandler("test_00");
            excelWriter = handler.create("记录", Data1.class);

            List<Data1> list = new ArrayList<>(1024);

            //方式一：一次性导出
         /*   for (int i = 0; i < 1000000; i++) {//总数据 100W条
                num++;
                list.add(new Data1("张三" + num, "123abc" + num));
            }
            handler.write(excelWriter, list);
            */

            //方式二：分多次导出     为了降低导出过程中的内存资源，也可以这样
            for (int a = 0; a < 10; a++) {//模拟拿100 次数据
                for (int i = 0; i < 1000; i++) {//模拟一次拿的数据
                    num++;
                    list.add(new Data1("张三" + num, "123abc" + num));
                }
                handler.write(excelWriter, list);
                list.clear();//必须clear,否则数据会重复
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (null != excelWriter) {
                handler.finish(excelWriter);
            }
        }
    }

    /**
     * 模拟百万以上数据导入到excel
     */
    private void test01() {

        int count = 0;
        int num = 0;
        ExcelHandler handler = null;
        ExcelWriter excelWriter = null;
        try {
            //创建handler对象--参数:文件夹名
            handler = new ExcelHandler("test_001");
            excelWriter = handler.create("记录", Data1.class, 10);

            List<Data1> list = new ArrayList<>(1024);

            //此处依旧可以模仿test00()去优化

            for (int t = 0; t < 10; t++) {//模拟分页页数 每页50W数据

                for (int i = 0; i < 500000; i++) {//模拟每次数据量
                    num++;
                    list.add(new Data1("张三" + num, "123abc" + num));
                }
                //count 将控制插入哪一个sheet
                count += list.size();
                handler.write(excelWriter, list, count);
                list.clear();//必须clear,否则数据会重复
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (null != excelWriter) {
                handler.finish(excelWriter);
            }
        }
    }
}

