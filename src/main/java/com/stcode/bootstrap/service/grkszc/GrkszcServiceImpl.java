package com.stcode.bootstrap.service.grkszc;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.grkszc.GrkszcMapper;
import com.stcode.bootstrap.mapper.jcjg.JcjgMapper;
import com.stcode.bootstrap.model.*;
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
        PageInfo<Grkszc> pageInfo = new PageInfo<>(grkszcList);
        long total = pageInfo.getTotal();
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
            //前端页面显示位置[1-检查 2-检查结果]
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
    @Transactional
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
            //todo 查询检查是否存
            Jcjg addJcjg = new Jcjg();
            addJcjg.setJcid(grid.getJcid());
            addJcjg.setCxjc("true");
            //前端页面显示位置[1-检查 2-检查结果]
            addJcjg.setCxxm("2");
            //个人跨省转出
            addJcjg.setMkdm("grkszc");
            addJcjg.setMkmc("个人跨省转出");
            addJcjg.setCxjcr("重新当前登录人");
            addJcjg.setCxjcrq(new Date());
            addJcjg.setCxjcjg(jcjgName.toString());
            addJcjg.setCxmemo(memo);
            jcjgMapper.updateJcjg(addJcjg);
        }

        return R.ok();
    }

    //检查审核
    @Override
    public R examineCheckJcjg(Grkszc query) {
        //个人id
        List<JcjgVo> grids      = query.getJcjgs();
        //检查结果id
        String[] jcids          = query.getFormIds();
        //查询检查结果中文
        List<String> mxmcs      = jcjgMapper.queryMXMC(jcids);
        StringBuilder jcjgName  = new StringBuilder();
        for (String mxmc:mxmcs ) {
            jcjgName.append(mxmc).append(",");
        }
        //备注
        String memo = query.getMemo();
        for (JcjgVo grid: grids) {
            //todo 查询检查是否存
            Jcjg addJcjg = new Jcjg();
            addJcjg.setJcid(grid.getJcid());
            addJcjg.setJcsh("true");
            //个人跨省转出
            addJcjg.setMkdm("grkszc");
            addJcjg.setMkmc("个人跨省转出");
            addJcjg.setJcshr("检查审核当前登录人");
            addJcjg.setJcshrq(new Date());
            addJcjg.setJcshjg(jcjgName.toString());
            addJcjg.setJcshbz(memo);
            jcjgMapper.updateJcjg(addJcjg);
        }

        return R.ok();

    }

    @Override
    public R getGRDetailInfo(Grkszc grInfo) {
        List<Grkszc> grDetailList = grkszcMapper.getGRDetailInfo(grInfo);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }

    @Override
    public R getGrlxhInfo(Grkszc query) {
        List<Grkszc> grDetailList = grkszcMapper.getGrlxhInfo(query);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }

    @Override
    public R getGrxxbInfo(Grkszc query) {
        List<Grkszc> grDetailList = grkszcMapper.getGrxxbInfo(query);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }

    @Override
    public R searchXxb(Grkszc query) {
        Integer page = query.getOffset() == null ? 1 : Integer.valueOf(query.getOffset());
        Integer rows = query.getLimit() == null ? 10 : Integer.valueOf(query.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Grkszc> grDetailList = grkszcMapper.getSearchXxb(query);
        PageInfo<Grkszc> fxXxPageInfo = new PageInfo<>(grDetailList);
        long total = fxXxPageInfo.getTotal();
        return R.ok().put("rows",grDetailList).put("total",total);
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
