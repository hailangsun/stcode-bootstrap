package com.stcode.bootstrap.service.grcbzbzy;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.grcbzbzy.GrcbzbzyMapper;
import com.stcode.bootstrap.model.Grcbzbzy;
import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Service("grcbzbzyService")
public class GrcbzbzyServiceImpl implements GrcbzbzyService{

    @Resource
    GrcbzbzyMapper grcbzbzyMapper;

    @Override
    public R search(Grcbzbzy query) {
        String[] spstr = null;
        if(!StringUtils.isEmpty(query.getDbjg())){
            spstr = query.getDbjg().split(",");
        }
        query.setDbjgs(spstr);
        Integer page = query.getOffset() == null ? 1 : Integer.valueOf(query.getOffset());
        Integer rows = query.getLimit() == null ? 10 : Integer.valueOf(query.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Grcbzbzy> grjgzcList = grcbzbzyMapper.search(query);
        PageInfo<Grcbzbzy> pageInfo = new PageInfo<>(grjgzcList);
        long total = pageInfo.getTotal();
        return R.ok().put("rows",grjgzcList).put("total",total);
    }


    @Override
    public R getGRDetailInfo(Grcbzbzy query) {
        List<Grcbzbzy> grDetailList = grcbzbzyMapper.getGRDetailInfo(query);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }


}
