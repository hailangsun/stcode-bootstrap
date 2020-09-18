package com.stcode.bootstrap.service.grkszc;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.grkszc.GrkszcMapper;
import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("grkszcService")
public class GrkszcServiceImpl implements GrkszcService{

    @Resource
    GrkszcMapper grkszcMapper;

    @Override
    public R search(Grkszc grkszc) {
        String[] spstr = null;
        if(!StringUtils.isEmpty(grkszc.getDbjg())){
            spstr = grkszc.getDbjg().split(",");
        }
        grkszc.setDbjgs(spstr);
        Integer page = grkszc.getOffset() == null ? 1 : Integer.valueOf(grkszc.getOffset());
        Integer rows = grkszc.getLimit() == null ? 10 : Integer.valueOf(grkszc.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Grkszc> grkszcList = grkszcMapper.getGrkszc(grkszc);
        PageInfo<Grkszc> fxXxPageInfo = new PageInfo<>(grkszcList);
        long total = fxXxPageInfo.getTotal();
        return R.ok().put("rows",grkszcList).put("total",total);
    }

}
