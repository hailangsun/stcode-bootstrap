package com.stcode.bootstrap.service.grcbzbzy;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.common.BigDecimalUtils;
import com.stcode.bootstrap.mapper.grcbzbzy.GrcbzbzyMapper;
import com.stcode.bootstrap.model.Grcbzbzy;
import com.stcode.bootstrap.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
        Grcbzbzy result = new Grcbzbzy();
        List<Grcbzbzy> grDetailList = grcbzbzyMapper.getGRDetailInfo(query);
        List<Grcbzbzy> grDetailJointList = grcbzbzyMapper.getGRDetailInfoJoint(query);
        if(grDetailList.size() > 0){
            BeanUtils.copyProperties(grDetailList.get(0),result);
            BigDecimal nmgrjf = new BigDecimal(grDetailList.get(0).getNmgrjf());
            BigDecimal nmdwhz = new BigDecimal(grDetailList.get(0).getNmdwhz());
            result.setNmhj(BigDecimalUtils.add(nmgrjf,nmdwhz).toString());
        }
        if(grDetailJointList.size() > 0){
            result.setGrjf(grDetailJointList.get(0).getGrjf());
            result.setDwhz(grDetailJointList.get(0).getDwhz());
            result.setDnhj(BigDecimalUtils.add( new BigDecimal(grDetailJointList.get(0).getGrjf()), new BigDecimal(grDetailJointList.get(0).getDwhz())).toString());
        }


        return R.ok().put("data",result);

    }


}
