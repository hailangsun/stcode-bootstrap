package com.stcode.bootstrap.controller.ylybcx;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.stcode.bootstrap.common.BigDecimalUtils;
import com.stcode.bootstrap.commonquery.ylybcx.YlybcxQuery;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.export.ylybcx.YlybcxDetailExport;
import com.stcode.bootstrap.export.ylybcx.YlybcxExport;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.service.ylybcx.YlybcxService;
import com.stcode.bootstrap.utils.R;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * 监督检查-日常检查-职工养老月报外支付查询
 * [ylybcx -> 养老月报查询]
 */
@Controller
@RequestMapping("/ylybcx")
public class YlybcxController  {

    private static String prePath = "\\src\\main\\java\\com\\stcode\\bootstrap\\exceltemplate\\yldetail.xlsx";

    @Resource
    private DmMxMapper dmMxMapper;

    @Resource
    private YlybcxService ylybcxService;

    //代办机构
    @ModelAttribute(value = "DBJG_options")
    public Map<String, String> DBJG_options() {
        Map<String, String> map = new HashMap<>();
        List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm("9007");
        for (DmMx dm:dmmxList) {
            map.put(dm.getDmmxid(),dm.getMxmc());
        }
        return map;
    }

    //绑定当前登录人
    @ModelAttribute(value = "commonquery")
    public Map<String,Object> getCommon(){
        Map<String,Object> result = new HashMap<>();
        //存储到redis中
        YlybcxQuery query = ylybcxService.getQuery();
//        query.setDmmxid("1010");
        result.put("query",query);

        return result;
    }


    @RequestMapping("index")
    public String index() {
        return "ylybcx/ylybcx";
    }


    @RequestMapping("/search")
    @ResponseBody
    public R search(Ylybcx ylybcx) {
        return ylybcxService.getYlybcx(ylybcx);
    }

    /**
     * 查询 职工养老月报外支付人员明细
     * @param query
     * @return
     */
    @RequestMapping("/ylybzfDetail")
    @ResponseBody
    public R ylybzfDetail(Ylybcx query){
        return ylybcxService.getYlybzfDetail(query);
    }


    /**
     * 查询检查对话框
     */
    @RequestMapping("/checkResultTable")
    @ResponseBody
    public R checkResultTable(){
        List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm("10101010");
        return R.ok().put("data", dmmxList);
    }

    /**
     * 检查确定，生成检查结果
     */
    @RequestMapping(value = "insertJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R insertCheckResult(@RequestBody Ylybcx insertJcjg) {
        return ylybcxService.insertJcjg(insertJcjg);
    }

    /**
     * 二级页面 重新检查
     * @param updateJcjg
     * @return
     */
    @RequestMapping(value = "updateJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R updateJcjg(@RequestBody Ylybcx updateJcjg) {
        return ylybcxService.updateJcjg(updateJcjg);
    }

    /**
     * 全部检查
     * @param query
     * @return
     */
    @RequestMapping(value = "/allcheck")
    @ResponseBody
    public R allcheck(Ylybcx query) {
        return ylybcxService.allcheck(query);
    }


    /**
     * 查询个人详情信息
     */
    @RequestMapping("/grDetailInfo")
    @ResponseBody
    public R grDetailInfo(Ylybcx grDetailQuery){
        return ylybcxService.getGRDetailInfo(grDetailQuery);
    }


    /**
     * 随机抽取
     * @param query
     * @return
     */
    @RequestMapping(value = "/randomCheck")
    @ResponseBody
    public R randomCheck(Ylybcx query) {
        return ylybcxService.randomCheck(query);
    }

    /**
     * 获取可随机抽取的数量
     * @param query
     * @return
     */
    @RequestMapping(value = "/getRandomNum")
    @ResponseBody
    public R getRandomNum(Ylybcx query) {
        return ylybcxService.getRandomNum(query);
    }


    //导出
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Ylybcx ylybcx = getYlybcxParms(request);
        if("2".equals(ylybcx.getIsAllExprot())){
            ylybcx.setLimit(String.valueOf(Integer.MAX_VALUE));
            ylybcx.setOffset("0");
        }else {
            ylybcx.setLimit(ylybcx.getMainlimit());
            ylybcx.setOffset(ylybcx.getMainoffset());
        }
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = getExportCommon(response,"职工养老月报外支付查询");
        EasyExcel.write(response.getOutputStream(), YlybcxExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("职工养老月报外支付查询").doWrite(data(ylybcx));
    }

    //二级页面导出
    @RequestMapping(value = "/detaildownload")
    public void detaildownload(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Ylybcx ylybcx = getYlybcxParms(request);
        if("2".equals(ylybcx.getIsAllExprot())){
            ylybcx.setLimit(String.valueOf(Integer.MAX_VALUE));
            ylybcx.setOffset("0");
        }else {
            ylybcx.setLimit(ylybcx.getDetaillimit());
            ylybcx.setOffset(ylybcx.getDetailoffset());
        }
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = getExportCommon(response,"职工养老月报外支付人员明细");
        EasyExcel.write(response.getOutputStream(), YlybcxDetailExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("职工养老月报外支付人员明细").doWrite(detailData(ylybcx));
    }

    /**
     * 三级页面导出
     * @param response
     * @throws IOException
     */
    @GetMapping("grdetaildownload")
    public void download(HttpServletResponse response,String grid) throws IOException {
        String getPath =  Class.class.getClass().getResource("/").getPath().replaceFirst("/", "");
        String[] splitPath = getPath.split("/");
        splitPath = Arrays.copyOf(splitPath, splitPath.length - 2);
        StringBuilder sb = new StringBuilder();
        for (String str: splitPath) {
           sb.append(str).append(File.separator);
        }

        String templateFileName =  sb.toString().substring(0, sb.length() - 1)+prePath;
        Ylybcx query = new Ylybcx();
        query.setGrid(grid);
        Ylybcx data = (Ylybcx) ylybcxService.getGRDetailInfo(query).get("data");

        setResponseHeader(response, "养老月报外支付人员详细情况");
        EasyExcel.write(response.getOutputStream(),Ylybcx.class).withTemplate(templateFileName).sheet().doFill(data);
    }


    private Ylybcx getYlybcxParms(HttpServletRequest request) {
        Map<String,String> parmMap=new HashMap<String,String>();
        Enumeration<String> all = request.getParameterNames();
        String name  = "";
        String value = "";
        while(all.hasMoreElements()){
            name = all.nextElement();
            value = request.getParameter(name);
            parmMap.put(name, value);
        }
        Ylybcx ylybcx = JSON.parseObject(JSON.toJSONString(parmMap),Ylybcx.class);
        return ylybcx;
    }


    private HorizontalCellStyleStrategy getExportCommon(HttpServletResponse response,String excelName) throws UnsupportedEncodingException {
        setResponseHeader(response,excelName);
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)11);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
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
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    private List<YlybcxExport> data(Ylybcx data) {
        List<YlybcxExport> list = new ArrayList<YlybcxExport>();
        List<Ylybcx> cbdwxx = (List<Ylybcx>) ylybcxService.getYlybcx(data).get("rows");
        BigDecimal tempTotal = BigDecimal.ZERO;
        for (Ylybcx ylybcx: cbdwxx) {
            YlybcxExport yl = new YlybcxExport();
            BeanUtils.copyProperties(ylybcx, yl);
            list.add(yl);
            if(!"2".equals(data.getIsAllExprot())){
                tempTotal = BigDecimalUtils.add(tempTotal,new BigDecimal(ylybcx.getTcfd()));
            }
        }
        if(cbdwxx.size() > 0){
            YlybcxExport mainTotal = new YlybcxExport();
            mainTotal.setJcrs("总合计金额");
            if(!"2".equals(data.getIsAllExprot())){
                mainTotal.setTcfd(tempTotal.toString());
            }else {
                mainTotal.setTcfd(cbdwxx.get(0).getMainTotal());
            }

            list.add(mainTotal);
        }
        return list;
    }

    private List<YlybcxDetailExport> detailData(Ylybcx data) {
        List<YlybcxDetailExport> list = new ArrayList<YlybcxDetailExport>();
        List<Ylybcx> ylList = (List<Ylybcx>) ylybcxService.getYlybzfDetail(data).get("rows");
        for (Ylybcx ylybcx: ylList) {
            YlybcxDetailExport yl = new YlybcxDetailExport();
            BeanUtils.copyProperties(ylybcx, yl);
            list.add(yl);
        }
        if(ylList.size() > 0){
            YlybcxDetailExport total = new YlybcxDetailExport();
            total.setDwdm("总合计金额");
            total.setGrname(ylList.get(0).getDetailTotal());
            total.setJbr("本页合计金额");
            total.setZfje(ylList.get(0).getCurrentDetailTotal());
            list.add(total);
        }
        return list;
    }

    private void setResponseHeader(HttpServletResponse response,String excelName)throws UnsupportedEncodingException{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(excelName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }



}
