package com.stcode.bootstrap.controller.grjgzc;

import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.service.grjgzc.GrjgzcService;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
