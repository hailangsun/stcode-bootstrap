package com.stcode.bootstrap.controller.ylybcx;


import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.mapper.ylybcx.YlYbCxMapper;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.service.ylybcx.YlybcxService;
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
 * 监督检查-日常检查-职工养老月报外支付查询
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

}
