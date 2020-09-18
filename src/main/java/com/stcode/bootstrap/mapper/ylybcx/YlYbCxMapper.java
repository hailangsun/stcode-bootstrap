package com.stcode.bootstrap.mapper.ylybcx;

import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.domain.DmMxExample;
import com.stcode.bootstrap.model.Grxx;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface YlYbCxMapper {

    /**
     * 根据多条件查询-职工养老月报外支付查询
     * @param ylybcx
     * @return
     */
    List<Ylybcx> getYlybcx(Ylybcx ylybcx);

    /**
     * 根据条件查询 - 职工养老月报外支付人员明细
     * @param ylybcxMap
     * @return
     */
    List<Ylybcx> getYlybzfDetail(Map ylybcxMap);

    /**
     * 查询 个人信息 - 养老月报外支付人员详细情况
     */
    List<Grxx>getGRDetailInfo(Ylybcx grDetailInfo);
}