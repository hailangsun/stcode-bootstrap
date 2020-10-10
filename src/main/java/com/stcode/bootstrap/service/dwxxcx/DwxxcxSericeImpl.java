package com.stcode.bootstrap.service.dwxxcx;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.stcode.bootstrap.mapper.dwxxcx.DwXxMapper;
import com.stcode.bootstrap.mapper.jcjg.JcjgMapper;
import com.stcode.bootstrap.model.Dwxxcx;
import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.model.Jcjg;
import com.stcode.bootstrap.model.JcjgVo;
import com.stcode.bootstrap.utils.GenerateId;
import com.stcode.bootstrap.utils.R;
import com.stcode.bootstrap.utils.ServerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service("dwxxcxService")
public class DwxxcxSericeImpl implements DwxxcxService {

    @Resource
    DwXxMapper dwXxMapper;

    @Resource
    JcjgMapper jcjgMapper;

    @Override
    public R search(Dwxxcx query) {
        getDbjg(query);
        Integer page = query.getOffset() == null ? 1 : Integer.valueOf(query.getOffset());
        Integer rows = query.getLimit() == null ? 10 : Integer.valueOf(query.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Dwxxcx> dwXxes = dwXxMapper.getCbdwxx(query);
        PageInfo<Dwxxcx> pageInfo = new PageInfo<>(dwXxes);
        long total = pageInfo.getTotal();
        return R.ok().put("rows",dwXxes).put("total",total);
    }

    @Override
    @Transactional
    public R insertJcjg(Dwxxcx query) {

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
            List<Dwxxcx> detailNoCheck = dwXxMapper.getNoAllcheck(query);
            for (Dwxxcx yl: detailNoCheck) {
                Jcjg addJcjg = new Jcjg();
                addJcjg.setDwid(yl.getDwid());
                setJcjg(jcjgName, memo, addJcjg);
                addBatch.add(addJcjg);
            }
        }else {//当前页检查
            for (Jcjg grid: grids) {
                if("".equals(grid.getJcid()) || grid.getJcid() == null){
                    Jcjg addJcjg = new Jcjg();
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
    public R updateJcjg(Dwxxcx query) {
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
        if(grids.size() > 0){
            grids = grids.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(JcjgVo::getDwid))), ArrayList::new));
        }

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



    private void setJcjg(StringBuilder jcjgName, String memo, Jcjg addJcjg) {
        addJcjg.setJcid(GenerateId.generateId());
        addJcjg.setJc("true");
        addJcjg.setCxjc("false");
        //前端页面显示位置[1-检查 2-检查结果]
        addJcjg.setXm("1");
        addJcjg.setMkdm("105");
        addJcjg.setMkmc("单位信息查询");
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

    private void getDbjg(Dwxxcx query) {
        String[] spstr = null;
        if(!StringUtils.isEmpty(query.getDbjg())){
            spstr = query.getDbjg().split(",");
        }
        query.setDbjgs(spstr);
    }




    /**
     * 抽出方法
     * @param jcjgName
     * @param memo
     * @return
     */
    private void getJCJGVO(StringBuilder jcjgName, String memo,Dwxxcx addJcjg) {
        addJcjg.setJcr("当前登录人");
        addJcjg.setJcrq(new Date());
        addJcjg.setJcjg(jcjgName.toString());
        addJcjg.setMemo(memo);
    }

    @Override
    public R selectJcjgByDwid(Dwxxcx query) {
        List<Dwxxcx> jcjgList = dwXxMapper.selectJcjgByDwid(query);
        return R.ok().put("data",jcjgList);
    }

    @Override
    public R getZJByDwid(Dwxxcx query) {
        Integer page = query.getOffset() == null ? 1 : Integer.valueOf(query.getOffset());
        Integer rows = query.getLimit() == null ? 10 : Integer.valueOf(query.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Dwxxcx> zjByDwid = dwXxMapper.getZJByDwid(query);
        PageInfo<Dwxxcx> pageInfo = new PageInfo<>(zjByDwid);
        long total = pageInfo.getTotal();
        return R.ok().put("rows",zjByDwid).put("total",total);





    }
}
