package com.stcode.bootstrap.service.grkszc;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.grkszc.GrkszcMapper;
import com.stcode.bootstrap.mapper.jcjg.JcjgMapper;
import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.model.Jcjg;
import com.stcode.bootstrap.model.JcjgVo;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.GenerateId;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("grkszcService")
public class GrkszcServiceImpl implements GrkszcService{

    @Resource
    GrkszcMapper grkszcMapper;

    @Resource
    JcjgMapper jcjgMapper;

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

    @Override
    @Transactional
    public R insertJcjg(Grkszc grkszc) {
        //个人id
        List<JcjgVo> grids      = grkszc.getJcjgs();
        //检查结果id
        String[] jcids          = grkszc.getFormIds();
        //查询检查结果中文
        List<String> mxmcs      = jcjgMapper.queryMXMC(jcids);
        StringBuilder jcjgName  = new StringBuilder();
        for (String mxmc:mxmcs ) {
            jcjgName.append(mxmc).append(",");
        }
        //备注
        String memo = grkszc.getMemo();
        String uuid = null;
        for (JcjgVo grid: grids) {
            uuid = GenerateId.generateId();
            Jcjg addJcjg = new Jcjg();
            addJcjg.setJcid(uuid);
            addJcjg.setGrid(grid.getGrid());
            addJcjg.setJc("true");
            addJcjg.setCxjc("false");
            addJcjg.setDwid(grid.getDwid());
            //显示位置
            addJcjg.setXm("1");
            //个人跨省转出
            addJcjg.setMkdm("grkszc");
            addJcjg.setMkmc("个人跨省转出");
            getJCJGVO(jcjgName, memo,addJcjg);
            jcjgMapper.insertJcjg(addJcjg);
        }

        return R.ok();
    }

    @Override
    public R restartCheckJcjg(Grkszc grkszc) {
        //个人id
        List<JcjgVo> grids      = grkszc.getJcjgs();
        //检查结果id
        String[] jcids          = grkszc.getFormIds();
        //查询检查结果中文
        List<String> mxmcs      = jcjgMapper.queryMXMC(jcids);
        StringBuilder jcjgName  = new StringBuilder();
        for (String mxmc:mxmcs ) {
            jcjgName.append(mxmc).append(",");
        }
        //备注
        String memo = grkszc.getMemo();
        for (JcjgVo grid: grids) {

        }

        return null;
    }

    /**
     * 抽出方法
     * @param jcjgName
     * @param memo
     * @return
     */
    private void getJCJGVO(StringBuilder jcjgName, String memo,Jcjg addJcjg) {
        addJcjg.setJcr("当前登录人");
        addJcjg.setJcrq(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        addJcjg.setJcjg(jcjgName.toString());
        addJcjg.setMemo(memo);
    }

}
