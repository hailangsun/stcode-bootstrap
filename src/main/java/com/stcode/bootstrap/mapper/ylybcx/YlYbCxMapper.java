package com.stcode.bootstrap.mapper.ylybcx;

import com.stcode.bootstrap.commonquery.ylybcx.YlybcxQuery;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.domain.DmMxExample;
import com.stcode.bootstrap.model.Grxx;
import com.stcode.bootstrap.model.Jcjg;
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
     * @param query
     * @return
     */
    List<Ylybcx> getYlybzfDetail(Ylybcx query);

    /**
     * 查询 个人信息 - 养老月报外支付人员详细情况
     */
    List<Ylybcx>getGRDetailInfo(Ylybcx grDetailInfo);

    /**
     * 获取默认页面回显值
     * @return
     */
    YlybcxQuery getQuery();

    /**
     * 获取一级页面总数
     */
   String getTotal(Ylybcx ylybcx);


    /**
     * 获取全部没有检查的
     * @param query
     * @return
     */
    List<Ylybcx> getNoAllcheck(Ylybcx query);

    /**
     * 获取二级页面总数
     */
    String getDetailTotal(Ylybcx ylybcx);

    /**
     * 获取二级页面全部未检查的
     * @param query
     * @return
     */
    List<Ylybcx> getDetailNoCheck(Ylybcx query);
}