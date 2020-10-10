package com.stcode.bootstrap.controller.common;

import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通用controller
 */

public class JdjcCommonController {

    @Resource
    private DmMxMapper dmMxMapper;

    /**
     * 查询检查对话框
     */
    @RequestMapping("/checkResultTable")
    @ResponseBody
    public R checkResultTable(){
        List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm("10101010");
        return R.ok().put("data", dmmxList);
    }

}
