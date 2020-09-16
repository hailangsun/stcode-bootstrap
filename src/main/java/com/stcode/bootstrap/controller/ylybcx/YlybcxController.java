package com.stcode.bootstrap.controller.ylybcx;


import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.mapper.ylybcx.YlYbCxMapper;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.service.ylybcx.YlybcxService;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监督检查-日常检查-职工养老月报外支付查询
 */
@Controller
@RequestMapping("/ylybcx")
public class YlybcxController  {

    @Resource
    private DmMxMapper dmMxMapper;

    @Resource
    private YlybcxService ylybcxService;

    public static final String SESSION_ATTR_TEMP_QUERY = "gr_detail_info";

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
//        List<DmMx> ylybcxList = new ArrayList<>();
        List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm("10101010");
//        for (DmMx dm:dmmxList) {
//            Ylybcx ylybcx = new Ylybcx();
//            ylybcx.setDmmxid(dm.getDmmxid());
//            ylybcx.setMxmc(dm.getMxmc());
//            ylybcx.setMxdm(dm.getMxmc());
//            ylybcxList.add(ylybcx);
//        }
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
//    @RequestMapping("/grDetailInfo")
//    public String grDetailInfo(ModelMap modelMap, HttpSession session, Ylybcx grDetailQuery){
//        Ylybcx test = new Ylybcx();
//        test.setDwmc("测试啊啊啊啊啊啊啊");
//        modelMap.addAttribute(SESSION_ATTR_TEMP_QUERY, test);
//       return "ylybcx/test";
//    }

    @RequestMapping("/grDetailInfo")
    @ResponseBody
    public Ylybcx grDetailInfo(@ModelAttribute(SESSION_ATTR_TEMP_QUERY) Ylybcx grDetailQuery){
        grDetailQuery = new Ylybcx();
        grDetailQuery.setDwmc("测试啊啊啊啊啊啊啊");
        return grDetailQuery;
    }

}
