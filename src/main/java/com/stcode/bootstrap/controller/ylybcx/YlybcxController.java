package com.stcode.bootstrap.controller.ylybcx;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.export.ylybcx.YlybcxExport;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.mapper.ylybcx.YlYbCxMapper;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.service.ylybcx.YlybcxService;
import com.stcode.bootstrap.utils.R;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 监督检查-日常检查-职工养老月报外支付查询
 * [ylybcx -> 养老月报查询]
 */
@Controller
@RequestMapping("/ylybcx")
public class YlybcxController  {

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
     * @param ylybzfMap
     * @return
     */
    @RequestMapping("/ylybzfDetail")
    @ResponseBody
    public R ylybzfDetail(@RequestBody Map<String,Object> ylybzfMap){
        return ylybcxService.getYlybzfDetail(ylybzfMap);
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
    @RequestMapping(value = "/insertJcjg")
    @ResponseBody
    public R insertCheckResult(Ylybcx insertJcjg) {
        return ylybcxService.insertJcjg(insertJcjg);
    }


    /**
     * 查询个人详情信息
     */
    @RequestMapping("/grDetailInfo")
    @ResponseBody
    public R grDetailInfo(Ylybcx grDetailQuery){
        return ylybcxService.getGRDetailInfo(grDetailQuery);
    }


    //导出
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
        Ylybcx ylybcx = JSON.parseObject(JSON.toJSONString(parmMap),Ylybcx.class);
        ylybcx.setLimit(ylybcx.getMainlimit());
        ylybcx.setOffset(ylybcx.getMainoffset());
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = getExportCommon(response,"职工养老月报外支付查询");
        EasyExcel.write(response.getOutputStream(), YlybcxExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("职工养老月报外支付查询").doWrite(data(ylybcx));
    }


    private HorizontalCellStyleStrategy getExportCommon(HttpServletResponse response,String excelName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(excelName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

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
        for (Ylybcx ylybcx: cbdwxx) {
            YlybcxExport yl = new YlybcxExport();
            BeanUtils.copyProperties(ylybcx, yl);
            list.add(yl);
        }
        return list;
    }
}
