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
import java.util.ArrayList;
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

}
