package com.stcode.bootstrap.controller.common;

import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.service.common.ICommonService;
import com.stcode.bootstrap.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ICommonService commonService;

    @RequestMapping("/getDmmxList")
    public ServerResponse<List<DmMx>> getDmmxList(String dmm){

        return commonService.getDmmxList(dmm);
    }
}
