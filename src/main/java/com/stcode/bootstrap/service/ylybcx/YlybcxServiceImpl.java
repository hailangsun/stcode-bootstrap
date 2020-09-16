package com.stcode.bootstrap.service.ylybcx;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.ylybcx.YlYbCxMapper;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("ylybcxService")
public class YlybcxServiceImpl implements YlybcxService {

    @Resource
    YlYbCxMapper ylYbCxMapper;

    @Override
    public R getYlybcx(Ylybcx ylybcx) {
        String[] spstr = null;
        if(!StringUtils.isEmpty(ylybcx.getDbjg())){
            spstr = ylybcx.getDbjg().split(",");
        }
        ylybcx.setDbjgs(spstr);
        Integer page = ylybcx.getOffset() == null ? 1 : Integer.valueOf(ylybcx.getOffset());
        Integer rows = ylybcx.getLimit() == null ? 10 : Integer.valueOf(ylybcx.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Ylybcx> ylybcxList = ylYbCxMapper.getYlybcx(ylybcx);
        PageInfo<Ylybcx> fxXxPageInfo = new PageInfo<>(ylybcxList);
        long total = fxXxPageInfo.getTotal();
        return R.ok().put("rows",ylybcxList).put("total",total);
    }

    @Override
    public R getYlybzfDetail(Map ylybcxMap) {
        Integer page = ylybcxMap.get("offset") == null ? 1 : Integer.valueOf(ylybcxMap.get("offset").toString());
        Integer rows = ylybcxMap.get("limit") == null ? 10 : Integer.valueOf(ylybcxMap.get("limit").toString());
        PageHelper.offsetPage(page,rows);
        List<Ylybcx> ylybcxList = ylYbCxMapper.getYlybzfDetail(ylybcxMap);
        PageInfo<Ylybcx> fxXxPageInfo = new PageInfo<>(ylybcxList);
        long total = fxXxPageInfo.getTotal();
        return R.ok().put("rows",ylybcxList).put("total",total);
    }

}
