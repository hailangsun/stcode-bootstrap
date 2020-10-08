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
import java.util.ArrayList;
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
    public R insertJcjg(Grkszc query) {
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
        List<Jcjg> addBatch = new ArrayList<>();

        if("1".equals(query.getCheckFlag())){//没有选择，默认全部检查
            List<Grkszc> detailNoCheck = grkszcMapper.getNoAllcheck(query);
            for (Grkszc yl: detailNoCheck) {
                Jcjg addJcjg = new Jcjg();
                addJcjg.setGrid(yl.getGrid());
                addJcjg.setDwid(yl.getDwid());
                setJcjg(jcjgName, memo, addJcjg);
                addBatch.add(addJcjg);
            }
        }else {//当前页检查
            for (Jcjg grid: grids) {
                if("".equals(grid.getJcid()) || grid.getJcid() == null){
                    Jcjg addJcjg = new Jcjg();
                    addJcjg.setGrid(grid.getGrid());
                    addJcjg.setDwid(grid.getDwid());
                    setJcjg(jcjgName, memo, addJcjg);
                    addBatch.add(addJcjg);
                }
            }
        }


        int num = 0;
        if(addBatch.size() > 0){
            num = jcjgMapper.addJcjgBatch(addBatch);
        }

        return R.ok().put("num",num);
    }

    private void setJcjg(StringBuilder jcjgName, String memo, Jcjg addJcjg) {
        addJcjg.setJcid(GenerateId.generateId());
        addJcjg.setJc("true");
        addJcjg.setCxjc("false");
        //前端页面显示位置[1-检查 2-检查结果]
        addJcjg.setXm("1");
        addJcjg.setMkdm("3");
        addJcjg.setMkmc("跨省转出人员信息查询");
        getJCJGVO(jcjgName, memo, addJcjg);
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

        List<Jcjg> addBatch = new ArrayList<>();

        for (JcjgVo jcjg:grids) {
            Jcjg addJcjg = new Jcjg();
            addJcjg.setJcid(jcjg.getJcid());
            addJcjg.setCxjc("true");
            //前端页面显示位置[1-检查 2-检查结果]
            addJcjg.setCxxm("2");
            addJcjg.setCxjcr("重新当前登录人");
            addJcjg.setCxjcrq(new Date());
            addJcjg.setCxjcjg(jcjgName.toString());
            addJcjg.setCxmemo(memo);
            addBatch.add(addJcjg);
        }

        if(addBatch.size() > 0){
            jcjgMapper.updateJcjgBatch(addBatch);
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
        List<Jcjg> addBatch = new ArrayList<>();
        for (JcjgVo jcjg:grids) {
            Jcjg addJcjg = new Jcjg();
            addJcjg.setJcid(jcjg.getJcid());
            addJcjg.setJcsh("true");
            addJcjg.setJcshr("检查审核当前登录人");
            addJcjg.setJcshrq(new Date());
            addJcjg.setJcshjg(jcjgName.toString());
            addJcjg.setJcshbz(memo);
            addBatch.add(addJcjg);
        }

        if(addBatch.size() > 0){
            jcjgMapper.updateJcjgBatch(addBatch);
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
