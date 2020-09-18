package com.stcode.bootstrap.service.ylybcx;

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
    R getYlybzfDetail(Map ylybcxMap);

    /**
     * 新增检查结果jcjg
     */
    R insertJcjg(Ylybcx ylybcx);


    /**
     * 查询 个人信息 - 养老月报外支付人员详细情况
     */
    R getGRDetailInfo(Ylybcx grDetailInfo);
}
