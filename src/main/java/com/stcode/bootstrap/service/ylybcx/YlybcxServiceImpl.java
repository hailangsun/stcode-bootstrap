package com.stcode.bootstrap.service.ylybcx;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.common.BigDecimalUtils;
import com.stcode.bootstrap.commonquery.ylybcx.YlybcxQuery;
import com.stcode.bootstrap.mapper.DwXxMapper;
import com.stcode.bootstrap.mapper.jcjg.JcjgMapper;
import com.stcode.bootstrap.mapper.ylybcx.YlYbCxMapper;
import com.stcode.bootstrap.model.Grxx;
import com.stcode.bootstrap.model.Jcjg;
import com.stcode.bootstrap.model.JcjgVo;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.GenerateId;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

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
        String mainTotal = ylYbCxMapper.getTotal(ylybcx);
        if("".equals(mainTotal) || mainTotal == null){
            mainTotal = "0.00";
        }
        if(ylybcxList.size() > 0){
            ylybcxList.get(0).setMainTotal(mainTotal);
        }
        return R.ok().put("rows",ylybcxList).put("total",total);
    }

    @Override
    public R getYlybzfDetail(Ylybcx query) {
        Integer page = query.getOffset() == null ? 1 : Integer.valueOf(query.getOffset());
        Integer rows = query.getLimit() == null ? 10 : Integer.valueOf(query.getLimit());
        PageHelper.offsetPage(page,rows);
        List<Ylybcx> ylybcxList = ylYbCxMapper.getYlybzfDetail(query);
        PageInfo<Ylybcx> fxXxPageInfo = new PageInfo<>(ylybcxList);
        long total = fxXxPageInfo.getTotal();
        String detailTotal = ylYbCxMapper.getDetailTotal(query);
        BigDecimal currentDetailTotal = BigDecimal.ZERO;
        for (Ylybcx yl:ylybcxList) {
            currentDetailTotal = BigDecimalUtils.add(currentDetailTotal,new BigDecimal(yl.getZfje()));
        }
        if(ylybcxList.size() > 0){
            ylybcxList.get(0).setCurrentDetailTotal(currentDetailTotal.toString());
            ylybcxList.get(0).setDetailTotal(detailTotal);
        }
        return R.ok().put("rows",ylybcxList).put("total",total);
    }

    @Override
    @Transactional
    public R insertJcjg(Ylybcx ylybcx) {
        //个人id
        List<JcjgVo>  grids = ylybcx.getJcjgs();
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
        List<Jcjg> addBatch = new ArrayList<>();

        if("5".equals(ylybcx.getCheckFlag())){//没有选择，默认全部检查
            List<Ylybcx> detailNoCheck = ylYbCxMapper.getDetailNoCheck(ylybcx);
            for (Ylybcx yl: detailNoCheck) {
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
        addJcjg.setMkdm("1");
        addJcjg.setMkmc("职工养老月报外支付查询");
        getJCJGVO(jcjgName, memo, addJcjg);
    }

    @Override
    @Transactional
    public R allcheck(Ylybcx query) {
        //查询全部未检查的
        List<Ylybcx> noAllcheck = ylYbCxMapper.getNoAllcheck(query);
        int num = getAddBatchNum(query, noAllcheck);
        return R.ok().put("num",num);
    }

    /**
     * 批量新增检查
     * @param query
     * @param noAllcheck
     * @return
     */
    private int getAddBatchNum(Ylybcx query, List<Ylybcx> noAllcheck) {
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

        List<Jcjg> addBatch = new ArrayList<>(noAllcheck.size());
        for(int i = 0; i< noAllcheck.size(); i++){
            Jcjg addJcjg = new Jcjg();
            addJcjg.setGrid(noAllcheck.get(i).getGrid());
            addJcjg.setDwid(noAllcheck.get(i).getDwid());
            setJcjg(jcjgName, memo, addJcjg);
            addBatch.add(addJcjg);
        }
        int num = 0;
        if(noAllcheck.size() > 0){
            num = jcjgMapper.addJcjgBatch(addBatch);
        }
        return num;
    }

    @Override
    public R getRandomNum(Ylybcx query) {
        String[] spstr = null;
        if(!StringUtils.isEmpty(query.getDbjg())){
            spstr = query.getDbjg().split(",");
        }
        query.setDbjgs(spstr);
        List<Ylybcx> ylybcxList = ylYbCxMapper.getYlybcx(query);
        List<Ylybcx> result = new ArrayList<>(Integer.valueOf(query.getRandomnum()));
        if(ylybcxList.size() > 0){
            //洗牌打乱次序
            Collections.shuffle(ylybcxList);
            result = ylybcxList.subList(0, Integer.valueOf(query.getRandomnum()));
            ylybcxList = null;
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i<result.size();i++){
                sb.append(result.get(i).getDwid()).append(",");
            }

            return R.ok().put("data",result).put("total",result.size()).put("randomdwids",sb.toString().substring(0, sb.length() - 1));
        }
        return R.ok();
    }

    @Override
    public R randomCheck(Ylybcx query) {
        //查询全部未检查的
        String[] randomids = null;
        if(!StringUtils.isEmpty(query.getRandomdwids())){
            randomids = query.getRandomdwids().split(",");
            query.setRandomids(randomids);
        }
        List<Ylybcx> noAllcheck = ylYbCxMapper.getNoAllcheck(query);
        int num = getAddBatchNum(query, noAllcheck);
        return R.ok().put("num",num);
    }

    @Override
    public R getGRDetailInfo(Ylybcx grDetailInfo) {
        List<Grxx> grDetailList = ylYbCxMapper.getGRDetailInfo(grDetailInfo);
        if(grDetailList.size() > 0){
            return R.ok().put("data",grDetailList.get(0));
        }
        return R.ok();
    }

    @Override
    public YlybcxQuery getQuery() {
        return ylYbCxMapper.getQuery();
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
