package com.stcode.bootstrap.service;


import com.stcode.bootstrap.domain.Dwxxcx;
import com.stcode.bootstrap.utils.R;
import com.stcode.bootstrap.utils.ServerResponse;

public interface DwxxcxService {


    /**
     * 查询参保单位信息
     * @return
     */
    R getCbdwxx(Dwxxcx dwxxcx);

    /**
     * 更新检查jcid
     * @param dwxxcx
     */
    void updateByDwid(Dwxxcx dwxxcx);

    /**
     * 查询检查结果
     */
    ServerResponse selectJcjgByDwid(String dwid);

    /**
     * 查询三险单位增减信息
     * @param dwid
     * @return
     */
    ServerResponse getZJByDwid(String dwid);

    /**
     * 全部导出
     */

}
