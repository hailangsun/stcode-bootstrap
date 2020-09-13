package com.stcode.bootstrap;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.stcode.bootstrap.domain.DwXx;
import com.stcode.bootstrap.domain.Dwxxcx;
import com.stcode.bootstrap.export.ComplexHeadData;
import com.stcode.bootstrap.export.DemoData;
import com.stcode.bootstrap.export.DwxxcxExport;
import com.stcode.bootstrap.export.demo.Data1;
import com.stcode.bootstrap.service.DwxxcxService;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
            excelWriter.write(data(new Dwxxcx()), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


    @Resource
    DwxxcxService dwxxcxService;
    private List<DwxxcxExport> data(Dwxxcx data) {
        data.setOffset(data.getOffset());
        data.setLimit(data.getLimit());
        List<DwxxcxExport> list = new ArrayList<DwxxcxExport>();
        List<DwXx> cbdwxx = (List<DwXx>) dwxxcxService.getCbdwxx(data).get("rows");
        for (DwXx dwxx: cbdwxx) {
            DwxxcxExport de = new DwxxcxExport();
            BeanUtils.copyProperties(dwxx, de);
            list.add(de);
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
        EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板").doWrite(data(new Dwxxcx()));
    }




    @Test
    public void myOKexport() {
        // 写法2
        String fileName = "F:\\bkws\\" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {

            // 头的策略
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            // 背景设置为红色
            headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontHeightInPoints((short)11);
            headWriteCellStyle.setWriteFont(headWriteFont);

            // 内容的策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
            contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

            // 背景绿色
            WriteFont contentWriteFont = new WriteFont();
            // 字体大小
            contentWriteFont.setFontHeightInPoints((short)12);
            contentWriteFont.setFontName("NSimSun");
            contentWriteCellStyle.setWriteFont(contentWriteFont);

            contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
            contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
            contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
            contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);

            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

//            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            excelWriter = EasyExcel.write(fileName, DwxxcxExport.class).registerWriteHandler(horizontalCellStyleStrategy).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("参保单位信息").build();

            List<DwxxcxExport> dataList = new ArrayList<>(1024);

            Dwxxcx dwxxcx = new Dwxxcx();
            for (int a = 1; a <= 2; a++) {//模拟拿100 次数据
                if(a == 1){
                    dwxxcx.setOffset("0");
                    dwxxcx.setLimit("995");
                }else {
                    dwxxcx.setOffset("995");
                    dwxxcx.setLimit("995");
                }
                dataList = data(dwxxcx);

                excelWriter.write(dataList, writeSheet);
                dataList.clear();//必须clear,否则数据会重复
            }

        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }


    }



}
