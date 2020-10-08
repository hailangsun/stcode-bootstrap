package com.stcode.bootstrap.controller.Jgylybcx;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.stcode.bootstrap.common.BigDecimalUtils;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.export.jgylybcx.JgylybcxDetailExport;
import com.stcode.bootstrap.export.jgylybcx.JgylybcxExport;
import com.stcode.bootstrap.export.ylybcx.YlybcxExport;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.model.Jgylybcx;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.service.jgylybcx.JgylybcxService;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * 监督检查-日常检查-机关养老月报外支付查询
 * [jgylybcx -> 机关养老月报查询]
 */
@Controller
@RequestMapping("/jgylybcx")
public class JgylybcxController {

    @Resource
    private DmMxMapper dmMxMapper;

    @Resource
    private JgylybcxService jgylybcxService;

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

    //检查类型
    @ModelAttribute(value = "SHBZ_options")
    public Map<String, String> SHBZ_options() {
        Map<String, String> map = new HashMap<>();
        List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm("10101010");
        for (DmMx dm:dmmxList) {
            map.put(dm.getDmmxid(),dm.getMxmc());
        }
        return map;
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



    @RequestMapping("index")
    public String index() {
        return "jgylybcx/jgylybcx";
    }

    @RequestMapping("/search")
    @ResponseBody
    public R search(Jgylybcx query) {
        return jgylybcxService.search(query);
    }

    /**
     * 查询 机关养老月报外支付查询 人员明细
     * @param query
     * @return
     */
    @RequestMapping("/jgylybzfDetail")
    @ResponseBody
    public R ylybzfDetail(Jgylybcx query){
        return jgylybcxService.getJgYlybzfDetail(query);
    }

    /**
     * 检查确定，生成检查结果
     */
    @RequestMapping(value = "insertJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R insertCheckResult(@RequestBody Jgylybcx insertJcjg) {
        return jgylybcxService.insertJcjg(insertJcjg);
    }


    /**
     * 重新检查
     * @param updateJcjg
     * @return
     */
    @RequestMapping(value = "updateJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R updateJcjg(@RequestBody Jgylybcx updateJcjg) {
        return jgylybcxService.updateJcjg(updateJcjg);
    }


    /**
     * 审核
     * @param query
     * @return
     */
    @RequestMapping(value = "shJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R shJcjg(@RequestBody Jgylybcx query) {
        return jgylybcxService.shJcjg(query);
    }


    //导出
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Jgylybcx jgylybcx = getYlybcxParms(request);
        if("2".equals(jgylybcx.getIsAllExprot())){
            jgylybcx.setLimit(String.valueOf(Integer.MAX_VALUE));
            jgylybcx.setOffset("0");
        }else {
            jgylybcx.setLimit(jgylybcx.getMainlimit());
            jgylybcx.setOffset(jgylybcx.getMainoffset());
        }
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = getExportCommon(response,"机关事业养老保险月报外支付明细");
        EasyExcel.write(response.getOutputStream(), JgylybcxExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("机关事业养老保险月报外支付明细").doWrite(data(jgylybcx));
    }

    //导出二级页面
    @RequestMapping(value = "/detaildownload")
    public void detaildownload(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Jgylybcx jgylybcx = getYlybcxParms(request);
        if("2".equals(jgylybcx.getIsAllExprot())){
            jgylybcx.setLimit(String.valueOf(Integer.MAX_VALUE));
            jgylybcx.setOffset("0");
        }else {
            jgylybcx.setLimit(jgylybcx.getDetaillimit());
            jgylybcx.setOffset(jgylybcx.getDetailoffset());
        }
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = getExportCommon(response,"机关事业养老保险月报外支付明细");
        EasyExcel.write(response.getOutputStream(), JgylybcxDetailExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("机关事业养老保险月报外支付明细").doWrite(detailData(jgylybcx));
    }


    private Jgylybcx getYlybcxParms(HttpServletRequest request) {
        Map<String,String> parmMap=new HashMap<String,String>();
        Enumeration<String> all = request.getParameterNames();
        String name  = "";
        String value = "";
        while(all.hasMoreElements()){
            name = all.nextElement();
            value = request.getParameter(name);
            parmMap.put(name, value);
        }
        Jgylybcx jgylybcx = JSON.parseObject(JSON.toJSONString(parmMap),Jgylybcx.class);
        return jgylybcx;
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

    private void setResponseHeader(HttpServletResponse response,String excelName)throws UnsupportedEncodingException{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(excelName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }

    private List<JgylybcxExport> data(Jgylybcx data) {
        List<JgylybcxExport> list = new ArrayList<JgylybcxExport>();
        List<Jgylybcx> queryResult = (List<Jgylybcx>) jgylybcxService.search(data).get("rows");
//        BigDecimal tempTotal = BigDecimal.ZERO;
        for (Jgylybcx jgylybcx: queryResult) {
            JgylybcxExport yl = new JgylybcxExport();
            BeanUtils.copyProperties(jgylybcx, yl);
            list.add(yl);
//            if(!"2".equals(data.getIsAllExprot())){
//                tempTotal = BigDecimalUtils.add(tempTotal,new BigDecimal(ylybcx.getTcfd()));
//            }
        }
//        if(cbdwxx.size() > 0){
//            YlybcxExport mainTotal = new YlybcxExport();
//            mainTotal.setJcrs("总合计金额");
//            if(!"2".equals(data.getIsAllExprot())){
//                mainTotal.setTcfd(tempTotal.toString());
//            }else {
//                mainTotal.setTcfd(cbdwxx.get(0).getMainTotal());
//            }
//
//            list.add(mainTotal);
//        }
        return list;
    }

    private List<JgylybcxDetailExport> detailData(Jgylybcx data) {
        List<JgylybcxDetailExport> list = new ArrayList<JgylybcxDetailExport>();
        List<Jgylybcx> queryResult = (List<Jgylybcx>) jgylybcxService.getJgYlybzfDetail(data).get("rows");
        for (Jgylybcx jgylybcx: queryResult) {
            JgylybcxDetailExport yl = new JgylybcxDetailExport();
            BeanUtils.copyProperties(jgylybcx, yl);
            list.add(yl);
        }
        return list;
    }


}
