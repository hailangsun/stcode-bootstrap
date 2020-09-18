package com.stcode.bootstrap.service.jgylybcx;


import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;

import java.util.Map;

public interface JgylybcxService {

    /**
     * 根据多条件查询-机关事业养老保险月报外支付明细
     * @param ylybcx
     * @return
     */
    R search(Ylybcx ylybcx);

    /**
     * 根据条件查询 - 人员待遇明细页面-机关事业养老保险月报外支付明细
     * @param
     * @return
     */
    R getJgYlybzfDetail(Map jgylybcxMap);
}
