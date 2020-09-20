package com.stcode.bootstrap.controller.demo;

import com.stcode.bootstrap.service.aopdemo.AopDemoService;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/aopdemo")
public class AOPDemoController {

    @Resource
    AopDemoService aopDemoService;

    /**
     * 分页查询
     */
    @RequestMapping("index")
    @ResponseBody
    public R list() {
        return aopDemoService.search();
    }

}
