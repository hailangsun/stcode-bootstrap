package com.stcode.bootstrap.controller.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.domain.DwXx;
import com.stcode.bootstrap.domain.Dwxxcx;
import com.stcode.bootstrap.export.DwxxcxExport;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.mapper.DwXxMapper;
import com.stcode.bootstrap.service.DwxxcxService;

import com.stcode.bootstrap.utils.R;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 单位信息查询
 */
@Controller
@RequestMapping("/dwxxcx")
public class DwxxcxController {

    @Resource
    DwxxcxService dwxxcxService;

    @Resource
    private DmMxMapper dmMxMapper;

    //
    @ModelAttribute(value = "DBJG_options")
    public Map<String, String> DBJG_options() {
        Map<String, String> map = new HashMap<>();
//        List<DmMx> dmmxList = commonService.getDmmxList("9007").getData();
//        List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm("9007");
//        for (DmMx dm:dmmxList) {
//            map.put(dm.getDmmxid(),dm.getMxmc());
//        }
        return map;
    }

    //经办人


    @RequestMapping("index")
    public String index() {
        return "dwxxcx/dwxxcx";
    }

    @RequestMapping("/search")
    @ResponseBody
    public R search(Dwxxcx dwxxcx) {
        return dwxxcxService.getCbdwxx(dwxxcx);
    }


    //查询检查结果
    @RequestMapping(value = "/checkResult")
    @ResponseBody
    public R checkResult(Dwxxcx dwxxcx) {
        dwxxcxService.updateByDwid(dwxxcx);
        return R.ok();
    }
    //列表
    @RequestMapping(value = "/checkResultTable")
    @ResponseBody
    public R checkResultTable() {
        List<Dwxxcx> dwxxcxList = new ArrayList<>();
        List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm("10101010");
        for (DmMx dm:dmmxList) {
            Dwxxcx dwxxcx = new Dwxxcx();
            dwxxcx.setDmmxid(dm.getDmmxid());
            dwxxcx.setMxmc(dm.getMxmc());
            dwxxcxList.add(dwxxcx);
        }
        return R.ok().put("data", dwxxcxList);
    }


    //    导出

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
//    @GetMapping(value = "/download")
//    @RequestMapping(value = "/download")
//    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {
//        Map<String,String> parmMap=new HashMap<String,String>();
//        Enumeration<String> all = request.getParameterNames();
//        String name  = "";
//        String value = "";
//        while(all.hasMoreElements()){
//            name = all.nextElement();
//            value = request.getParameter(name);
//            parmMap.put(name, value);
//        }
//        Dwxxcx dwxxcx = JSON.parseObject(JSON.toJSONString(parmMap),Dwxxcx.class);
//
//        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//        String fileName = URLEncoder.encode("参保单位信息", "UTF-8");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//        EasyExcel.write(response.getOutputStream(), DwxxcxExport.class).sheet("参保单位信息").doWrite(data(dwxxcx));
//    }




    @RequestMapping(value = "/download")
    public void test(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<String,String> parmMap=new HashMap<String,String>();
        Enumeration<String> all = request.getParameterNames();
        String name  = "";
        String value = "";
        while(all.hasMoreElements()){
            name = all.nextElement();
            value = request.getParameter(name);
            parmMap.put(name, value);
        }
        Dwxxcx dwxxcx = JSON.parseObject(JSON.toJSONString(parmMap),Dwxxcx.class);


        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("参保单位信息", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//        headWriteCellStyle.setBorderLeft(BorderStyle.MEDIUM);
//        headWriteCellStyle.setBorderTop(BorderStyle.MEDIUM);
//        headWriteCellStyle.setBorderRight(BorderStyle.MEDIUM);
//        headWriteCellStyle.setBorderBottom(BorderStyle.MEDIUM);

        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)11);
        headWriteCellStyle.setWriteFont(headWriteFont);


        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        // 背景绿色
//        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
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

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        EasyExcel.write(fileName, DwxxcxExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("模板")
//                .doWrite(data(dwxxcx));

        EasyExcel.write(response.getOutputStream(), DwxxcxExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("参保单位信息").doWrite(data(dwxxcx));
    }


    private List<DwxxcxExport> data(Dwxxcx data) {
        List<DwxxcxExport> list = new ArrayList<DwxxcxExport>();
        List<DwXx> cbdwxx = (List<DwXx>) dwxxcxService.getCbdwxx(data).get("rows");
        for (DwXx dwxx: cbdwxx) {
            DwxxcxExport de = new DwxxcxExport();
            BeanUtils.copyProperties(dwxx, de);
            list.add(de);
        }
        return list;
    }



    //点击单位信息
    @RequestMapping("/companyInfo")
    @ResponseBody
    public R company(Dwxxcx dwxxcx){
        List<Dwxxcx> resultList = (List<Dwxxcx>) dwxxcxService.getZJByDwid(dwxxcx.getDwid()).getData();
        return R.ok().put("data",resultList).put("total",resultList.size());
    }

    //检查结果
    @RequestMapping("/cxjg")
    @ResponseBody
    public R cxjg(Dwxxcx dwxxcx){
        List<Dwxxcx> result = (List<Dwxxcx>) dwxxcxService.selectJcjgByDwid(dwxxcx.getDwid()).getData();
        return R.ok().put("data",result);
    }


    @Resource
    DwXxMapper dwXxMapper;

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>1. 创建excel对应的实体对象 参照{@link }
     * <p>2. 设置返回的 参数
     * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("allexport")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("参保单位信息", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

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
            excelWriter = EasyExcel.write(response.getOutputStream(), DwxxcxExport.class).registerWriteHandler(horizontalCellStyleStrategy).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("参保单位信息").build();

            List<DwxxcxExport> dataList = new ArrayList<>(1024);

            int allNum = dwXxMapper.getAllNum();

            Dwxxcx dwxxcx = new Dwxxcx();
            for (int a = 1; a <= 2; a++) {//模拟拿100 次数据
                if(a == 1){
                    dwxxcx.setOffset("0");
                    dwxxcx.setLimit("995");
                }else {
                    dwxxcx.setOffset("995");
                    dwxxcx.setLimit("995");
                }
                dataList = Alldata(dwxxcx);

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


    private List<DwxxcxExport> Alldata(Dwxxcx dwxxcx) {
        Integer page = dwxxcx.getOffset() == null ? 1 : Integer.valueOf(dwxxcx.getOffset());
        Integer rows = dwxxcx.getLimit() == null ? 10 : Integer.valueOf(dwxxcx.getLimit());
        PageHelper.offsetPage(page,rows);
        List<DwxxcxExport> list = new ArrayList<DwxxcxExport>();
        List<DwXx> cbdwxx = dwXxMapper.getAllExprot();
        for (DwXx dwxx: cbdwxx) {
            DwxxcxExport de = new DwxxcxExport();
            BeanUtils.copyProperties(dwxx, de);
            list.add(de);
        }
        return list;
    }


}
