package com.stcode.bootstrap.controller.grjgzc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.export.grjgzc.GrjgzcExport;
import com.stcode.bootstrap.export.grkszc.GrkszcExport;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.service.grjgzc.GrjgzcService;
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
 * 1.2.27 监督检查-日常检查-机关事业单位转出人员信息查询
 * [Grjgzc -> 个人机关转出]
 */
@Controller
@RequestMapping("/grjgzc")
public class GrjgzcController {

    @Resource
    private DmMxMapper dmMxMapper;

    @Resource
    private GrjgzcService grjgzcService;

    //代办机构
    @ModelAttribute(value = "DBJG_options")
    public Map<String, String> DBJG_options() {
        Map<String, String> map = new HashMap<>();
        List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm("9007");
        for (DmMx dm : dmmxList) {
            map.put(dm.getDmmxid(), dm.getMxmc());
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

    /**
     * 查询检查对话框
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

    @RequestMapping("index")
    public String index() {
        return "grjgzc/grjgzc";
    }

    @RequestMapping("/search")
    @ResponseBody
    public R search(Grjgzc query) {
        return grjgzcService.search(query);
    }



    /**
     * 点击缴费凭证 查询基本养老参保缴费凭证
     */
    @RequestMapping("/grDetailInfo")
    @ResponseBody
    public R grDetailInfo(Grjgzc query){
        return grjgzcService.getGRDetailInfo(query);
    }

    /**
     * 点击联系函 基本养老转移接续联系函
     */
    @RequestMapping("/grlxhInfo")
    @ResponseBody
    public R grlxhInfo(Grjgzc query){
        return grjgzcService.getGrlxhInfo(query);
    }


    /**
     * 点击联系函 职业年金联系函
     */
    @RequestMapping("/grlxhnjInfo")
    @ResponseBody
    public R grlxhnjInfo(Grjgzc query){
        return grjgzcService.getGrlxhnjInfo(query);
    }

    /**
     * 信息表 参保人基本信息
     */
    @RequestMapping("/grxxbInfo")
    @ResponseBody
    public R grxxbInfo(Grjgzc query){
        return grjgzcService.getGrxxbInfo(query);
    }

    /**
     * 查询信息表细单
     */
    @RequestMapping("/searchXxb")
    @ResponseBody
    public R  searchXxb(Grjgzc query){
        return grjgzcService.searchXxb(query);
    }


    /**
     * 查询信息表 标签页 养老信息表
     */
    @RequestMapping("/xxbyl")
    @ResponseBody
    public R  xxbyl(Grjgzc query){
        return grjgzcService.xxbyl(query);
    }


    /**
     * 信息表 职业年金(企业年金)关系转移接续信息表
     */
    @RequestMapping("/grxxbnjInfo")
    @ResponseBody
    public R grxxbnjInfo(Grjgzc query){
        return grjgzcService.grxxbnjInfo(query);
    }


    /**
     * 检查确定，生成检查结果
     */
    @RequestMapping(value = "insertJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R insertJcjg(@RequestBody Grjgzc query) {
        return grjgzcService.insertJcjg(query);
    }

    /**
     * 检查确定，生成检查结果
     */
    @RequestMapping(value = "updateJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R updateJcjg(@RequestBody Grjgzc query) {
        return grjgzcService.restartCheckJcjg(query);
    }

    /**
     * 检查审核，生成检查审核结果
     * @param query
     * @return
     */
    @RequestMapping(value = "examineCheckJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R examineCheckJcjg(@RequestBody Grjgzc query) {
        return grjgzcService.examineCheckJcjg(query);
    }

    //导出
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Grjgzc grjgzc = getYlybcxParms(request);
        if("2".equals(grjgzc.getIsAllExprot())){
            grjgzc.setLimit(String.valueOf(Integer.MAX_VALUE));
            grjgzc.setOffset("0");
        }else {
            grjgzc.setLimit(grjgzc.getMainlimit());
            grjgzc.setOffset(grjgzc.getMainoffset());
        }
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = getExportCommon(response,"机关事业单位转出人员信息查询");
        EasyExcel.write(response.getOutputStream(), GrjgzcExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("机关事业单位转出人员信息查询").doWrite(data(grjgzc));
    }

    private Grjgzc getYlybcxParms(HttpServletRequest request) {
        Map<String,String> parmMap=new HashMap<String,String>();
        Enumeration<String> all = request.getParameterNames();
        String name  = "";
        String value = "";
        while(all.hasMoreElements()){
            name = all.nextElement();
            value = request.getParameter(name);
            parmMap.put(name, value);
        }
        Grjgzc grjgzc = JSON.parseObject(JSON.toJSONString(parmMap),Grjgzc.class);
        return grjgzc;
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

    private List<GrjgzcExport> data(Grjgzc data) {
        List<Grjgzc> queryResult = (List<Grjgzc>) grjgzcService.search(data).get("rows");
        List<GrjgzcExport> list = new ArrayList<GrjgzcExport>(queryResult.size());
        for (Grjgzc grjgzc: queryResult) {
            GrjgzcExport gr = new GrjgzcExport();
            BeanUtils.copyProperties(grjgzc, gr);
            list.add(gr);
        }
        return list;
    }
}
