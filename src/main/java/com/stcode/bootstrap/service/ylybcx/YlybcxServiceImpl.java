package com.stcode.bootstrap.service.ylybcx;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.DwXxMapper;
import com.stcode.bootstrap.mapper.jcjg.JcjgMapper;
import com.stcode.bootstrap.mapper.ylybcx.YlYbCxMapper;
import com.stcode.bootstrap.model.Jcjg;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.GenerateId;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("ylybcxService")
public class YlybcxServiceImpl implements YlybcxService {

    @Resource
    YlYbCxMapper ylYbCxMapper;

    @Resource
    JcjgMapper jcjgMapper;

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

    @Override
    @Transactional
    public R insertJcjg(Ylybcx ylybcx) {
        //个人id
        String[] grids = ylybcx.getGrIds();
        //检查结果id
        String[] jcids = ylybcx.getFormIds();
        //查询检查结果中文
        List<String> mxmcs = jcjgMapper.queryMXMC(jcids);
        StringBuilder jcjgName = new StringBuilder();
        for (String mxmc:mxmcs ) {
            jcjgName.append(mxmc).append(",");
        }
        //备注
        String memo = ylybcx.getMemo();
        String uuid = null;
        for (String grid: grids) {
            uuid = GenerateId.generateId();
            Jcjg addJcjg = new Jcjg();
            addJcjg.setJcid(uuid);
            addJcjg.setGrid(grid);
            addJcjg.setJc("true");
            addJcjg.setCxjc("false");
            addJcjg.setDwid(ylybcx.getDwid());
            addJcjg.setXm("1");
            getJCJGVO(jcjgName, memo,addJcjg);
            jcjgMapper.insertJcjg(addJcjg);
        }

        return R.ok();
    }

    /**
     * 抽出方法
     * @param jcjgName
     * @param memo
     * @return
     */
    private void getJCJGVO(StringBuilder jcjgName, String memo,Jcjg addJcjg) {
        addJcjg.setJcr("当前登录人");
        addJcjg.setJcrq(new Date());
        addJcjg.setJcjg(jcjgName.toString());
        addJcjg.setMemo(memo);
    }

}
