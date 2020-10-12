package com.stcode.bootstrap.controller.dwxxcx;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.stcode.bootstrap.controller.common.JdjcCommonController;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.export.dwxxcx.DwxxcxExport;
import com.stcode.bootstrap.export.dwxxcx.DwxxcxZjExport;
import com.stcode.bootstrap.export.grjgzc.GrjgzcExport;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.model.Dwxxcx;
import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.service.dwxxcx.DwxxcxService;
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
 * 1.2.17  监督检查-日常检查-单位信息查询
 * [dwxxcx -> 单位信息查询]
 */
@Controller
@RequestMapping("/dwxxcx")
public class DwxxcxController extends JdjcCommonController {

    @Resource
    DwxxcxService dwxxcxService;

    @Resource
    private DmMxMapper dmMxMapper;

    //经办机构
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
        return "dwxxcx/dwxxcx";
    }

    @RequestMapping("/search")
    @ResponseBody
    public R search(Dwxxcx dwxxcx) {
        return dwxxcxService.search(dwxxcx);
    }

    /**
     * 检查确定，生成检查结果
     */
    @RequestMapping(value = "insertJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R insertJcjg(@RequestBody Dwxxcx query) {
        return dwxxcxService.insertJcjg(query);
    }

    /**
     * 检查确定，重新检查
     * @param updateJcjg
     * @return
     */
    @RequestMapping(value = "updateJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R updateJcjg(@RequestBody Dwxxcx updateJcjg) {
        return dwxxcxService.updateJcjg(updateJcjg);
    }



    //点击单位信息
    @RequestMapping("/zjInfo")
    @ResponseBody
    public R zjInfo(Dwxxcx query){
      return dwxxcxService.getZJByDwid(query);
    }

    //检查结果
    @RequestMapping("/cxjg")
    @ResponseBody
    public R cxjg(Dwxxcx query){
      return dwxxcxService.selectJcjgByDwid(query);
    }


    //导出
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Dwxxcx dwxxcx = getYlybcxParms(request);
        if("2".equals(dwxxcx.getIsAllExprot())){
            dwxxcx.setLimit(String.valueOf(Integer.MAX_VALUE));
            dwxxcx.setOffset("0");
        }else {
            dwxxcx.setLimit(dwxxcx.getMainlimit());
            dwxxcx.setOffset(dwxxcx.getMainoffset());
        }
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = getExportCommon(response,"参保单位信息");
        EasyExcel.write(response.getOutputStream(), DwxxcxExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("参保单位信息").doWrite(data(dwxxcx));
    }

    //二级页面增减导出
    @RequestMapping(value = "/zjdownload")
    public void zjdownload(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Dwxxcx dwxxcx = getYlybcxParms(request);
        if("2".equals(dwxxcx.getIsAllExprot())){
            dwxxcx.setLimit(String.valueOf(Integer.MAX_VALUE));
            dwxxcx.setOffset("0");
        }else {
            dwxxcx.setLimit(dwxxcx.getDetaillimit());
            dwxxcx.setOffset(dwxxcx.getDetailoffset());
        }
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = getExportCommon(response,"三险单位增减信息");
        EasyExcel.write(response.getOutputStream(), DwxxcxZjExport.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("三险单位增减信息").doWrite(zjdata(dwxxcx));
    }


    private Dwxxcx getYlybcxParms(HttpServletRequest request) {
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
        return dwxxcx;
    }


    private List<DwxxcxZjExport> zjdata(Dwxxcx data) {
        List<Dwxxcx> queryResult = (List<Dwxxcx>) dwxxcxService.getZJByDwid(data).get("rows");
        List<DwxxcxZjExport> list = new ArrayList<DwxxcxZjExport>(queryResult.size());
        for (Dwxxcx dwxxcx: queryResult) {
            DwxxcxZjExport zj = new DwxxcxZjExport();
            BeanUtils.copyProperties(dwxxcx, zj);
            list.add(zj);
        }
        return list;
    }

    private List<DwxxcxExport> data(Dwxxcx data) {
        List<Dwxxcx> queryResult = (List<Dwxxcx>) dwxxcxService.search(data).get("rows");
        List<DwxxcxExport> list = new ArrayList<DwxxcxExport>(queryResult.size());
        for (Dwxxcx dwxxcx: queryResult) {
            DwxxcxExport gr = new DwxxcxExport();
            BeanUtils.copyProperties(dwxxcx, gr);
            list.add(gr);
        }
        return list;
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
}
