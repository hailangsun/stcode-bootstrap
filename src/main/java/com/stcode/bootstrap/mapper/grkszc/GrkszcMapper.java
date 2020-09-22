package com.stcode.bootstrap.mapper.grkszc;

import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.model.Grxx;
import com.stcode.bootstrap.model.Ylybcx;

import java.util.List;
import java.util.Map;

public interface GrkszcMapper {

    /**
     * 预留后续删除
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

}