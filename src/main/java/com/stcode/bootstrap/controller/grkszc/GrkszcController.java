package com.stcode.bootstrap.controller.grkszc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.model.JcjgVo;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.service.grkszc.GrkszcService;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("index")
    public String index() {
        return "grkszc/grkszc";
    }

    @RequestMapping("/search")
    @ResponseBody
    public R search(Grkszc grkszc) {
        return grkszcService.search(grkszc);
    }

    /**
     * 查询信息表细单
     */
    @RequestMapping("/searchXxb")
    @ResponseBody
    public R  searchXxb(Grkszc xxb){
        return R.ok();
    }


    /**
     * 检查确定，生成检查结果
     */
    @RequestMapping(value = "insertJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R insertJcjg(@RequestBody Grkszc grkszc) {
       return grkszcService.insertJcjg(grkszc);
    }


    /**
     * 检查确定，生成检查结果
     */
    @RequestMapping(value = "restartCheckJcjg", method = RequestMethod.POST)
    @ResponseBody
    public R restartCheckJcjg(@RequestBody Grkszc grkszc) {
        return grkszcService.restartCheckJcjg(grkszc);
    }






}