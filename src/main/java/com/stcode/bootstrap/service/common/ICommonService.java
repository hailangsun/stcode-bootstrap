package com.stcode.bootstrap.service.common;

import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.utils.ServerResponse;

import java.util.List;

public interface ICommonService {
    /**
     * 根据代码类别id获取对应的代码项
     * @param dmm 对应dm_mx表里的dmlbid字段
     * @return  List<DmMx>
     */
    ServerResponse<List<DmMx>> getDmmxList(String dmm);
}
