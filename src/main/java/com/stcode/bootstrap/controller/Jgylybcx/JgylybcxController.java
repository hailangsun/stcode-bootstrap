package com.stcode.bootstrap.controller.Jgylybcx;

import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.service.jgylybcx.JgylybcxService;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监督检查-日常检查-机关养老月报外支付查询
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
    public R search(Ylybcx ylybcx) {
        return jgylybcxService.search(ylybcx);
    }

    /**
     * 查询 职工养老月报外支付人员明细
     * @param jgylybzfMap
     * @return
     */
    @RequestMapping("/jgylybzfDetail")
    @ResponseBody
    public R ylybzfDetail(@RequestBody Map<String,Object> jgylybzfMap){
        return jgylybcxService.getJgYlybzfDetail(jgylybzfMap);
    }


}
