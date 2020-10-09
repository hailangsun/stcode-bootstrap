package com.stcode.bootstrap.service.grjgzc;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.grjgzc.GrjgzcMapper;
import com.stcode.bootstrap.mapper.jcjg.JcjgMapper;
import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.model.Jcjg;
import com.stcode.bootstrap.model.JcjgVo;
import com.stcode.bootstrap.utils.GenerateId;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("grjgzcService")
public class GrjgzcServiceImpl implements GrjgzcService{

    @Resource
    GrjgzcMapper grjgzcMapper;

    @Resource
    JcjgMapper jcjgMapper;

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
    public R xxbyl(Grjgzc query) {
        List<Grjgzc> grDetailList = grjgzcMapper.xxbyl(query);
        return R.ok().put("rows",grDetailList).put("total",grDetailList.size());
    }

    @Override
    public R grxxbnjInfo(Grjgzc query) {
        List<Grjgzc> grDetailList = grjgzcMapper.grxxbnjInfo(query);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }

    @Override
    public R insertJcjg(Grjgzc query) {
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
            List<Grjgzc> detailNoCheck = grjgzcMapper.getNoAllcheck(query);
            for (Grjgzc yl: detailNoCheck) {
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

    @Override
    public R restartCheckJcjg(Grjgzc query) {
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

    @Override
    public R examineCheckJcjg(Grjgzc query) {
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

    private void setJcjg(StringBuilder jcjgName, String memo, Jcjg addJcjg) {
        addJcjg.setJcid(GenerateId.generateId());
        addJcjg.setJc("true");
        addJcjg.setCxjc("false");
        //前端页面显示位置[1-检查 2-检查结果]
        addJcjg.setXm("1");
        addJcjg.setMkdm("4");
        addJcjg.setMkmc("机关事业单位转出人员信息查询");
        getJCJGVO(jcjgName, memo, addJcjg);
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
