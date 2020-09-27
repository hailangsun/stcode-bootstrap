package com.stcode.bootstrap.mapper.grcbzbzy;

import com.stcode.bootstrap.model.Grcbzbzy;
import com.stcode.bootstrap.model.Grjgzc;

import java.util.List;

public interface GrcbzbzyMapper {

    /**
     * 查询
     * @param query
     * @return
     */
    List<Grcbzbzy> search(Grcbzbzy query);


    /**
     * 点击姓名 弹出显示居保职保间转移详细信息
     * @param query
     * @return
     */
    List<Grcbzbzy> getGRDetailInfo(Grcbzbzy query);
}