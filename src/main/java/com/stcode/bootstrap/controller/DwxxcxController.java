package com.stcode.bootstrap.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.domain.DwXx;
import com.stcode.bootstrap.domain.Dwxxcx;
import com.stcode.bootstrap.export.DwxxcxExport;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.service.DwxxcxService;

import com.stcode.bootstrap.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {
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
        EasyExcel.write(response.getOutputStream(), DwxxcxExport.class).sheet("参保单位信息").doWrite(data(dwxxcx));
    }

    private List<DwxxcxExport> data(Dwxxcx data) {
        List<DwxxcxExport> list = new ArrayList<DwxxcxExport>();
        List<DwXx> cbdwxx = (List<DwXx>) dwxxcxService.getCbdwxx(data);
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

}
