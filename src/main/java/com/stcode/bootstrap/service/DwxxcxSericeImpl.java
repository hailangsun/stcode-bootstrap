package com.stcode.bootstrap.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stcode.bootstrap.domain.DwXx;
import com.stcode.bootstrap.domain.Dwxxcx;
import com.stcode.bootstrap.mapper.DwXxMapper;
import com.stcode.bootstrap.utils.GenerateId;
import com.stcode.bootstrap.utils.R;
import com.stcode.bootstrap.utils.ServerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.thymeleaf.expression.Maps;


import javax.annotation.Resource;
import java.beans.Transient;
import java.util.*;

@Service("dwxxcxService")
public class DwxxcxSericeImpl implements DwxxcxService {

    @Resource
    DwXxMapper dwXxMapper;

//    @Override
//    public ServerResponse getCbdwxx(Dwxxcx dwxxcx) {
//        DwXxExample dwXxExample = new DwXxExample();
//        if(!StringUtils.isEmpty(dwxxcx.getDbjg())){
//            List<String> flagIn = new ArrayList<>();
//            String[] spstr = dwxxcx.getDbjg().split(",");
//            for (int i = 0; i < spstr.length; i++) {
//                flagIn.add(spstr[i]);
//            }
//
//            dwXxExample.createCriteria().andFlagIn(flagIn);
//        }
//        List<DwXx> dwXxes = dwXxMapper.selectByExample(dwXxExample);
//        return ServerResponse.createBySuccess(dwXxes);
//    }


    @Override
    public R getCbdwxx(Dwxxcx dwxxcx) {
        String[] spstr = null;
        if(!StringUtils.isEmpty(dwxxcx.getDbjg())){
            spstr = dwxxcx.getDbjg().split(",");
        }
        dwxxcx.setDbjgs(spstr);
        Integer page = dwxxcx.getOffset() == null ? 1 : Integer.valueOf(dwxxcx.getOffset());
        Integer rows = dwxxcx.getLimit() == null ? 10 : Integer.valueOf(dwxxcx.getLimit());
        PageHelper.offsetPage(page,rows);
        List<DwXx> dwXxes = dwXxMapper.getCbdwxx(dwxxcx);

        PageInfo<DwXx> fxXxPageInfo = new PageInfo<>(dwXxes);
        long total = fxXxPageInfo.getTotal();
//        Map<Object, Object> result = new HashMap<>();
//        result.put("rows",dwXxes);
//        result.put("total",total);
        return R.ok().put("rows",dwXxes).put("total",total);
//        return ServerResponse.createBySuccess(result);
    }


    @Override
    @Transactional
    public void updateByDwid(Dwxxcx dwxxcx) {
        //单位id
        String [] dwids = dwxxcx.getDmids();
        //检查结果id
        String[] jcids = dwxxcx.getFormIds();
        //查询检查结果中文
        List<String> mxmcs = dwXxMapper.queryMXMC(jcids);
        StringBuilder jcjgName = new StringBuilder();
        for (String mxmc:mxmcs ) {
            jcjgName.append(mxmc).append(",");
        }
        //备注
        String memo = dwxxcx.getMemo();
        String uuid = null;
        for (String dwid: dwids) {
            uuid = GenerateId.generateId();
            //查询更新
            List<Dwxxcx> isexistJcjg = dwXxMapper.selectJcjgByDwid(dwid);
            if(isexistJcjg.size() > 0){
                if(isexistJcjg.size() > 1){
                    for (Dwxxcx jcjgt: isexistJcjg) {
                        //更新重新检查
                        if("true".equals(jcjgt.getCxjc())){
                            getJCJGVO(jcjgName, memo,jcjgt);
                            dwXxMapper.updateJCJGByjcid(jcjgt);
                        }else {
                            jcjgt.setCxjc("true");
                            dwXxMapper.updateJCJGByjcid(jcjgt);
                        }
                    }
                }
                if(isexistJcjg.size() == 1){
                    //新增重新检查
                    Dwxxcx addJcjg = new Dwxxcx();
                    addJcjg.setJcid(uuid);
                    addJcjg.setCxjc("true");
                    addJcjg.setJc("true");
                    addJcjg.setDwid(dwid);
                    addJcjg.setXm("2");
                    getJCJGVO(jcjgName, memo,addJcjg);
                    dwXxMapper.insertJcjg(addJcjg);

                    //更新结果
                    Dwxxcx setJcjg = isexistJcjg.get(0);
                    setJcjg.setCxjc("true");
                    dwXxMapper.updateJCJGByjcid(setJcjg);
                }
            }else {
                //更新检查ID
//                dwXxMapper.updateByDwid(dwid,uuid);
                //新增检查结果表
                Dwxxcx addJcjg = new Dwxxcx();
                addJcjg.setJcid(uuid);
                addJcjg.setJc("true");
                addJcjg.setCxjc("false");
                addJcjg.setDwid(dwid);
                addJcjg.setXm("1");
                getJCJGVO(jcjgName, memo,addJcjg);
                dwXxMapper.insertJcjg(addJcjg);
            }
        }
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
    public ServerResponse selectJcjgByDwid(String dwid) {
        List<Dwxxcx> jcjgList = dwXxMapper.selectJcjgByDwid(dwid);
        return ServerResponse.createBySuccess(jcjgList);
    }

    @Override
    public ServerResponse getZJByDwid(String dwid) {
        List<Dwxxcx> zjByDwid = dwXxMapper.getZJByDwid(dwid);
        return ServerResponse.createBySuccess(zjByDwid);
    }
}
