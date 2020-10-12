package com.stcode.bootstrap.controller.lwpqdw;

import com.stcode.bootstrap.controller.common.JdjcCommonController;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2.1.35 监督检查-日常检查-劳务派遣单位信息查询
 * [lwpqdw -> 劳务派遣单位]
 */
@Controller
@RequestMapping("/lwpqdw")
public class LwpqdwController extends JdjcCommonController {

    @Resource
    private DmMxMapper dmMxMapper;

    //经代办机构
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
        return "lwpqdw/lwpqdw";
    }

}
