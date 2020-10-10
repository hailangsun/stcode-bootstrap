package com.stcode.bootstrap.service.dwxxcx;


import com.stcode.bootstrap.model.Dwxxcx;
import com.stcode.bootstrap.utils.R;
import com.stcode.bootstrap.utils.ServerResponse;

public interface DwxxcxService {


    /**
     * 查询参保单位信息
     * @return
     */
    R search(Dwxxcx dwxxcx);

    /**
     * 新增检查结果jcjg
     */
    R insertJcjg(Dwxxcx query);

    /**
     * 更新检查结果
     * @param query
     * @return
     */
    R updateJcjg(Dwxxcx query);


    /**
     * 查询检查结果
     */
    R selectJcjgByDwid(Dwxxcx query);

    /**
     * 查询三险单位增减信息
     * @param query
     * @return
     */
    R getZJByDwid(Dwxxcx query);

}
