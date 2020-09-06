package com.stcode.bootstrap;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.stcode.bootstrap.export.ComplexHeadData;
import com.stcode.bootstrap.export.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class StcodeBootstrapApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * 最简单的写
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 直接写即可
     */
    @Test
    public void simpleWrite() {
        // 写法1
//        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

        // 写法2
        String fileName = "F:\\bkws\\" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            System.out.println(fileName);
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    private List<ComplexHeadData> data() {
        List<ComplexHeadData> list = new ArrayList<ComplexHeadData>();
        for (int i = 0; i < 10; i++) {
            ComplexHeadData data = new ComplexHeadData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            data.setTest1("字符串测试" + i);
            data.setTest2("字符串测试" + i);
            data.setTest3("字符串测试" + i);
            data.setTest4("字符串测试" + i);
            list.add(data);
        }
        return list;
    }


    /**
     * 复杂头写入
     * <p>1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * <p>2. 使用{@link ExcelProperty}注解指定复杂的头
     * <p>3. 直接写即可
     */
    @Test
    public void complexHeadWrite() {
        String fileName = "F:\\bkws\\" + "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板").doWrite(data());
    }


}
