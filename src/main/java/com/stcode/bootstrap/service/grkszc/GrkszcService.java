package com.stcode.bootstrap.service.grkszc;


import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;

import java.util.Map;

public interface GrkszcService {

    /**
     * 根据条件查询  本类需要改
     * @param query
     * @return
     */
    R search(Grkszc query);


    /**
     * 新增检查结果jcjg
     */
    R insertJcjg(Grkszc query);

    /**
     * 新增检查结果jcjg
     */
    R restartCheckJcjg(Grkszc query);



    /**
     * 查询缴费凭证 个人详情信息
     */
    R getGRDetailInfo(Grkszc query);


    /**
     * 查询 基本养老保险缴费账户转移联系函
     * @param query
     * @return
     */
    R getGrlxhInfo(Grkszc query);



    /**
     * 信息表 参保人基本信息
     * @param query
     * @return
     */
    R getGrxxbInfo(Grkszc query);




}
