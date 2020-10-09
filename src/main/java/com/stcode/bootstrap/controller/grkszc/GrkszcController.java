package com.stcode.bootstrap.controller.grkszc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.export.grkszc.GrkszcExport;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.service.grkszc.GrkszcService;
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
import java.net.URLEncoder;
import java.util.*;

/**
 * 1.2.24监督检查-日常检查-跨省转出人员信息查询
 * [grkszc -> 个人跨省转出]
 */
@Controller
@RequestMapping("/grkszc")
public class GrkszcController {


    @Resource
    GrkszcService grkszcService;

    @Resource
    private DmMxMapper dmMxMapper;

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
        return "grkszc/grkszc";
    }

    @RequestMapping("/search")
    @ResponseBody
    public R search(Grkszc query) {
        return grkszcService.search(query);
    }

    /**
     * 查询信息表细单
     */
    @RequestMapping("/searchXxb")
    @ResponseBody
    public R  searchXxb(Grkszc query){
        return grkszcService.searchXxb(query);
    }


    /**
     * 检查确定，生成检查结果
     */
    @RequestMapping(value = "insertJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R insertJcjg(@RequestBody Grkszc query) {
       return grkszcService.insertJcjg(query);
    }


    /**
     * 检查确定，生成检查结果
     */
    @RequestMapping(value = "updateJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R updateJcjg(@RequestBody Grkszc query) {
        return grkszcService.restartCheckJcjg(query);
    }


    /**
     * 检查审核，生成检查审核结果
     * @param query
     * @return
     */
    @RequestMapping(value = "examineCheckJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R examineCheckJcjg(@RequestBody Grkszc query) {
        return grkszcService.examineCheckJcjg(query);
    }



    /**
     * 查询缴费凭证 个人详情信息
     */
    @RequestMapping("/grDetailInfo")
    @ResponseBody
    public R grDetailInfo(Grkszc query){
        return grkszcService.getGRDetailInfo(query);
    }

    /**
     * 基本养老保险缴费账户转移联系函
     */
    @RequestMapping("/grlxhInfo")
    @ResponseBody
    public R grlxhInfo(Grkszc query){
        return grkszcService.getGrlxhInfo(query);
    }


    /**
     * 信息表 参保人基本信息
     */
    @RequestMapping("/grxxbInfo")
    @ResponseBody
    public R grxxbInfo(Grkszc query){
        return grkszcService.getGrxxbInfo(query);
    }


    /**
     * 查询检查审核对话框
     */
    @RequestMapping("/getjcshTable")
    @ResponseBody
    public R getjcshTable(){
        //存到redis中
        List<DmMx> result = new ArrayList<>(4);
        List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm("10101010");
        for (DmMx dmMx:dmmxList) {
            if(dmMx.getDmmxid().equals("01")){
                result.add(dmMx);
            }
            if(dmMx.getDmmxid().equals("13")){
                result.add(dmMx);
            }
            if(dmMx.getDmmxid().equals("09")){
                result.add(dmMx);
            }
        }
        return R.ok().put("data", result);
    }


    //导出
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Grkszc grkszc = getYlybcxParms(request);
        if("2".equals(grkszc.getIsAllExprot())){
            grkszc.setLimit(String.valueOf(Integer.MAX_VALUE));
            grkszc.setOffset("0");
        }else {
            grkszc.setLimit(grkszc.getMainlimit());
            grkszc.setOffset(grkszc.getMainoffset());
        }
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = getExportCommon(response,"基本养老保险转出情况");
        EasyExcel.write(response.getOutputStream(), GrkszcExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("基本养老保险转出情况").doWrite(data(grkszc));
    }

    private Grkszc getYlybcxParms(HttpServletRequest request) {
        Map<String,String> parmMap=new HashMap<String,String>();
        Enumeration<String> all = request.getParameterNames();
        String name  = "";
        String value = "";
        while(all.hasMoreElements()){
            name = all.nextElement();
            value = request.getParameter(name);
            parmMap.put(name, value);
        }
        Grkszc grkszc = JSON.parseObject(JSON.toJSONString(parmMap),Grkszc.class);
        return grkszc;
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

    private List<GrkszcExport> data(Grkszc data) {
        List<Grkszc> queryResult = (List<Grkszc>) grkszcService.search(data).get("rows");
        List<GrkszcExport> list = new ArrayList<GrkszcExport>(queryResult.size());
        for (Grkszc grkszc: queryResult) {
            GrkszcExport gr = new GrkszcExport();
            BeanUtils.copyProperties(grkszc, gr);
            list.add(gr);
        }
        return list;
    }

}
