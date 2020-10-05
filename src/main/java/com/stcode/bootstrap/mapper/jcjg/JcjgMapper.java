package com.stcode.bootstrap.mapper.jcjg;

import com.stcode.bootstrap.model.Jcjg;
import com.stcode.bootstrap.model.Ylybcx;
import com.stcode.bootstrap.utils.R;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JcjgMapper {
    /**
     * 检查确定，根据个人ID批量生成检查结果
     */
    void updateJcjg(Jcjg jcjg);

    /**
     * 新增JCJG检查结果
     */
    void insertJcjg(Jcjg jcjg);

    /**
     * 动态获取查询检查信息列表，用于前端页面展示
     * @param jcids
     * @return
     */
    List<String> queryMXMC(@Param("jcids")String[] jcids);


    /**
     * 批量插入
     * @param addBatch
     * @return
     */
    int addJcjgBatch(List<Jcjg> addBatch);
}