package com.stcode.bootstrap.service.grjgzc;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.grjgzc.GrjgzcMapper;
import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("grjgzcService")
public class GrjgzcServiceImpl implements GrjgzcService{

    @Resource
    GrjgzcMapper grjgzcMapper;

    @Override
    public R search(Grjgzc query) {
        String[] spstr = null;
        if(!StringUtils.isEmpty(query.getDbjg())){
            spstr = query.getDbjg().split(",");
        }
        query.setDbjgs(spstr);
        Integer page = query.getOffset() == null ? 1 : Integer.valueOf(query.getOffset());
        Integer rows = query.getLimit() == null ? 10 : Integer.valueOf(query.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Grjgzc> grjgzcList = grjgzcMapper.search(query);
        PageInfo<Grjgzc> pageInfo = new PageInfo<>(grjgzcList);
        long total = pageInfo.getTotal();
        return R.ok().put("rows",grjgzcList).put("total",total);
    }

    @Override
    public R getGRDetailInfo(Grjgzc query) {
        List<Grjgzc> grDetailList = grjgzcMapper.getGRDetailInfo(query);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }

    @Override
    public R getGrlxhInfo(Grjgzc query) {
        List<Grjgzc> grDetailList = grjgzcMapper.getGrlxhInfo(query);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }

    @Override
    public R getGrlxhnjInfo(Grjgzc query) {
        List<Grjgzc> grDetailList = grjgzcMapper.getGrlxhnjInfo(query);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }

    @Override
    public R getGrxxbInfo(Grjgzc query) {
        List<Grjgzc> grDetailList = grjgzcMapper.getGrxxbInfo(query);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }

    @Override
    public R searchXxb(Grjgzc query) {
        Integer page = query.getOffset() == null ? 1 : Integer.valueOf(query.getOffset());
        Integer rows = query.getLimit() == null ? 10 : Integer.valueOf(query.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Grjgzc> grDetailList = grjgzcMapper.getSearchXxb(query);
        PageInfo<Grjgzc> pageInfo = new PageInfo<>(grDetailList);
        long total = pageInfo.getTotal();
        return R.ok().put("rows",grDetailList).put("total",total);
    }

    @Override
    public R grxxbnjInfo(Grjgzc query) {
        List<Grjgzc> grDetailList = grjgzcMapper.grxxbnjInfo(query);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }
}
