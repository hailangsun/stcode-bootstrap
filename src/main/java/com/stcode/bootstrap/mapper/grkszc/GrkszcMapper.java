package com.stcode.bootstrap.mapper.grkszc;

import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.model.Grxx;
import com.stcode.bootstrap.model.Jgylybcx;
import com.stcode.bootstrap.model.Ylybcx;

import java.util.List;
import java.util.Map;

public interface GrkszcMapper {

    /**
     * 查询
     * @param grkszc
     * @return
     */
    List<Grkszc> getGrkszc(Grkszc grkszc);

    /**
     * 查询 基本养老参保缴费凭证
     */
    List<Grkszc> getGRDetailInfo(Grkszc grkszc);


    /**
     * 查询 基本养老保险缴费账户转移联系函
     * @param grkszc
     * @return
     */
    List<Grkszc> getGrlxhInfo(Grkszc grkszc);


    /**
     * 信息表 参保人基本信息
     */
    List<Grkszc> getGrxxbInfo(Grkszc grkszc);

    /**
     * 信息表 查询 历年缴费及个人账户记账信息
     * @param grkszc
     * @return
     */
    List<Grkszc> getSearchXxb(Grkszc grkszc);


    /**
     * 获取全部没有检查的
     * @param query
     * @return
     */
    List<Grkszc> getNoAllcheck(Grkszc query);


}