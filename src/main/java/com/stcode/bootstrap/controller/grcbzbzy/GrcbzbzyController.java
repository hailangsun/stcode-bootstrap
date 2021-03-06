package com.stcode.bootstrap.controller.grcbzbzy;

import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.model.Grcbzbzy;
import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.service.grcbzbzy.GrcbzbzyService;
import com.stcode.bootstrap.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.2.28监督检查-日常检查-城保和职保转移人员信息查询
 * [Grcbzbzy -> 个人城保职保转移]
 */
@Controller
@RequestMapping("/grcbzbzy")
public class GrcbzbzyController {

    @Resource
    private DmMxMapper dmMxMapper;

    @Autowired
    GrcbzbzyService grcbzbzyService;

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
        return "grcbzbzy/grcbzbzy";
    }

    @RequestMapping("/search")
    @ResponseBody
    public R search(Grcbzbzy query) {
        return grcbzbzyService.search(query);
    }


    @RequestMapping("/grDetailInfo")
    @ResponseBody
    public R grDetailInfo(Grcbzbzy query){
        return grcbzbzyService.getGRDetailInfo(query);
    }

}
