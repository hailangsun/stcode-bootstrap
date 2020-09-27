package com.stcode.bootstrap.service.grcbzbzy;

import com.stcode.bootstrap.model.Grcbzbzy;
import com.stcode.bootstrap.utils.R;

/**
 * 1.2.28监督检查-日常检查-城保和职保转移人员信息查询
 */
public interface GrcbzbzyService {
    /**
     * 查询主页表格信息
     *
     * @param query
     * @return
     */
    R search(Grcbzbzy query);

    /**
     * 点击姓名 弹出显示居保职保间转移详细信息
     */
    R getGRDetailInfo(Grcbzbzy query);
}
