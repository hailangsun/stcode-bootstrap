package com.stcode.bootstrap.service.jgylybcx;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.jgylybcx.JgYlYbCxMapper;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("jgylybcxService")
public class JgylybcxServiceImpl implements JgylybcxService {


    @Resource
    JgYlYbCxMapper jgYlYbCxMapper;

    @Override
    public R search(Ylybcx ylybcx) {
        String[] spstr = null;
        if(!StringUtils.isEmpty(ylybcx.getDbjg())){
            spstr = ylybcx.getDbjg().split(",");
        }
        ylybcx.setDbjgs(spstr);
        Integer page = ylybcx.getOffset() == null ? 1 : Integer.valueOf(ylybcx.getOffset());
        Integer rows = ylybcx.getLimit() == null ? 10 : Integer.valueOf(ylybcx.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Ylybcx> ylybcxList = jgYlYbCxMapper.getJgYlybcx(ylybcx);
        PageInfo<Ylybcx> fxXxPageInfo = new PageInfo<>(ylybcxList);
        long total = fxXxPageInfo.getTotal();
        return R.ok().put("rows",ylybcxList).put("total",total);
    }

    @Override
    public R getJgYlybzfDetail(Map jgylybcxMap) {
        Integer page = jgylybcxMap.get("offset") == null ? 1 : Integer.valueOf(jgylybcxMap.get("offset").toString());
        Integer rows = jgylybcxMap.get("limit") == null ? 10 : Integer.valueOf(jgylybcxMap.get("limit").toString());
        PageHelper.offsetPage(page,rows);
        List<Ylybcx> jgylybcxList = jgYlYbCxMapper.getJgYlybzfDetail(jgylybcxMap);
        PageInfo<Ylybcx> fxXxPageInfo = new PageInfo<>(jgylybcxList);
        long total = fxXxPageInfo.getTotal();
        return R.ok().put("rows",jgylybcxList).put("total",total);
    }
}
