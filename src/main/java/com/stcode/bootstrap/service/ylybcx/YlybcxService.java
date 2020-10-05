package com.stcode.bootstrap.service.ylybcx;

import com.stcode.bootstrap.commonquery.ylybcx.YlybcxQuery;
import com.stcode.bootstrap.model.Grxx;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;

import java.util.List;
import java.util.Map;

public interface YlybcxService {

    /**
     * 根据多条件查询-职工养老月报外支付查询
     * @param ylybcx
     * @return
     */
    R getYlybcx(Ylybcx ylybcx);

    /**
     * 根据条件查询 - 职工养老月报外支付人员明细
     * @param
     * @return
     */
    R getYlybzfDetail(Ylybcx query);

    /**
     * 新增检查结果jcjg
     */
    R insertJcjg(Ylybcx ylybcx);

    /**
     * 全部检查
     * @param query
     * @return
     */
    R allcheck(Ylybcx query);

    /**
     * 获取可随机抽取的数量
     * @param query
     * @return
     */
    R getRandomNum(Ylybcx query);

    /**
     * 随机抽取检查
     * @param query
     * @return
     */
    R randomCheck(Ylybcx query);


    /**
     * 查询 个人信息 - 养老月报外支付人员详细情况
     */
    R getGRDetailInfo(Ylybcx grDetailInfo);


    /**
     * 获取默认页面回显值
     * @return
     */
    YlybcxQuery getQuery();


}
