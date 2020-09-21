package com.stcode.bootstrap.service.grkszc;


import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;

import java.util.Map;

public interface GrkszcService {

    /**
     * 根据条件查询  本类需要改
     * @param grkszc
     * @return
     */
    R search(Grkszc grkszc);


    /**
     * 新增检查结果jcjg
     */
    R insertJcjg(Grkszc grkszc);

    /**
     * 新增检查结果jcjg
     */
    R restartCheckJcjg(Grkszc grkszc);


}
