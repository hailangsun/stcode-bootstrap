package com.stcode.bootstrap.service.jgylybcx;


import com.stcode.bootstrap.model.Jgylybcx;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;

import java.util.Map;

public interface JgylybcxService {

    /**
     * 根据多条件查询-机关事业养老保险月报外支付明细
     * @param query
     * @return
     */
    R search(Jgylybcx query);

    /**
     * 根据条件查询 - 人员待遇明细页面-机关事业养老保险月报外支付明细
     * @param
     * @return
     */
    R getJgYlybzfDetail(Jgylybcx query);

    /**
     * 新增检查结果jcjg
     */
    R insertJcjg(Jgylybcx query);

    /**
     * 更新检查结果
     * @param query
     * @return
     */
    R updateJcjg(Jgylybcx query);

    /**
     * 审核
     * @param query
     * @return
     */
    R shJcjg(Jgylybcx query);
}
