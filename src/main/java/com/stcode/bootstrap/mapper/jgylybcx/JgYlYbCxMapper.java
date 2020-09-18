package com.stcode.bootstrap.mapper.jgylybcx;

import com.stcode.bootstrap.model.Ylybcx;

import java.util.List;
import java.util.Map;

public interface JgYlYbCxMapper {

    /**
     * 根据多条件查询-机关事业养老保险月报外支付明细
     * @param ylybcx
     * @return
     */
    List<Ylybcx> getJgYlybcx(Ylybcx ylybcx);


    /**
     * 根据页面个人名称查询  - 机关事业养老保险基金补支明细
     * @return
     */
    List<Ylybcx> getJgYlybzfDetail(Map jgylybcxMap);

}