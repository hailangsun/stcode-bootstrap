package com.stcode.bootstrap.service.jgylybcx;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.mapper.jcjg.JcjgMapper;
import com.stcode.bootstrap.mapper.jgylybcx.JgYlYbCxMapper;
import com.stcode.bootstrap.model.Jcjg;
import com.stcode.bootstrap.model.JcjgVo;
import com.stcode.bootstrap.model.Jgylybcx;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.GenerateId;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("jgylybcxService")
public class JgylybcxServiceImpl implements JgylybcxService {


    @Resource
    JgYlYbCxMapper jgYlYbCxMapper;
    @Resource
    JcjgMapper jcjgMapper;

    @Override
    public R search(Jgylybcx query) {
        String[] spstr = null;
        if(!StringUtils.isEmpty(query.getDbjg())){
            spstr = query.getDbjg().split(",");
        }
        query.setDbjgs(spstr);
        Integer page = query.getOffset() == null ? 1 : Integer.valueOf(query.getOffset());
        Integer rows = query.getLimit() == null ? 10 : Integer.valueOf(query.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Jgylybcx> ylybcxList = jgYlYbCxMapper.getJgYlybcx(query);
        PageInfo<Jgylybcx> fxXxPageInfo = new PageInfo<>(ylybcxList);
        long total = fxXxPageInfo.getTotal();
        return R.ok().put("rows",ylybcxList).put("total",total);
    }

    @Override
    public R getJgYlybzfDetail(Jgylybcx query) {
        Integer page = query.getOffset() == null ? 1 : Integer.valueOf(query.getOffset());
        Integer rows = query.getLimit() == null ? 10 : Integer.valueOf(query.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Jgylybcx> jgylybcxList = jgYlYbCxMapper.getJgYlybzfDetail(query);
        PageInfo<Jgylybcx> fxXxPageInfo = new PageInfo<>(jgylybcxList);
        long total = fxXxPageInfo.getTotal();
        return R.ok().put("rows",jgylybcxList).put("total",total);
    }

    @Override
    @Transactional
    public R insertJcjg(Jgylybcx query) {
        //个人id
        List<JcjgVo>  grids = query.getJcjgs();
        //检查结果id
        String[] jcids = query.getFormIds();
        //查询检查结果中文
        List<String> mxmcs = jcjgMapper.queryMXMC(jcids);
        StringBuilder jcjgName = new StringBuilder();
        for (String mxmc:mxmcs ) {
            jcjgName.append(mxmc).append(",");
        }
        //备注
        String memo = query.getMemo();

        List<Jcjg> addBatch = new ArrayList<>();

        if("1".equals(query.getCheckFlag())){//没有选择，默认全部检查
            List<Jgylybcx> detailNoCheck = jgYlYbCxMapper.getNoAllcheck(query);
            for (Jgylybcx yl: detailNoCheck) {
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
    @Transactional
    public R updateJcjg(Jgylybcx query) {
        //个人id
        List<JcjgVo>  grids = query.getJcjgs();
        //检查结果id
        String[] jcids = query.getFormIds();
        //查询检查结果中文
        List<String> mxmcs = jcjgMapper.queryMXMC(jcids);
        StringBuilder jcjgName = new StringBuilder();
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
            //重新检查前端页面使用
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
    @Transactional
    public R shJcjg(Jgylybcx query) {
        //个人id
        List<JcjgVo>  grids = query.getJcjgs();
        List<Jcjg> addBatch = new ArrayList<>();
        for (JcjgVo jcjg:grids) {
            Jcjg addJcjg = new Jcjg();
            addJcjg.setJcid(jcjg.getJcid());
            addJcjg.setJcshr("审核登录人");
            addJcjg.setJcshrq(new Date());
            addJcjg.setJcsh(query.getShjg());
            addJcjg.setJcshjg(query.getShyj());
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
        addJcjg.setMkdm("2");
        addJcjg.setMkmc("机关养老月报外支付查询");
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
