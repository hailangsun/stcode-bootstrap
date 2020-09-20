package com.stcode.bootstrap.service.aopdemo;

import com.stcode.bootstrap.mapper.aopdemo.AopDemoMapper;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("aopDemoService")
public class AopDemoServiceImpl implements AopDemoService{

    @Resource
    AopDemoMapper aopDemoMapper;

    @Override
    public R search() {
       return R.ok().put("rows",aopDemoMapper.search());
    }
}
