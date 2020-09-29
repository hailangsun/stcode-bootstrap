package com.stcode.bootstrap.service.common;

import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.utils.ServerResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commonService")
public class CommonServiceImpl implements ICommonService {


    @Resource
    private DmMxMapper dmMxMapper;

    @Override
    public ServerResponse<List<DmMx>> getDmmxList(String dmm) {
        List<DmMx> dmmxs = dmMxMapper.getDmMxListByDmm(dmm);
        return ServerResponse.createBySuccess(dmmxs);
    }
}
