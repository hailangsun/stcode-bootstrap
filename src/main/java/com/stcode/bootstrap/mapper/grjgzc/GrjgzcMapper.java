package com.stcode.bootstrap.mapper.grjgzc;

import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.model.Grkszc;

import java.util.List;

public interface GrjgzcMapper {

    /**
     * 查询
     * @return
     */
    List<Grjgzc> search(Grjgzc query);

    /**
     * 点击缴费凭证 查询基本养老参保缴费凭证
     */
    List<Grjgzc> getGRDetailInfo(Grjgzc query);

    /**
     * 点击联系函 基本养老转移接续联系函
     * @param query
     * @return
     */
    List<Grjgzc> getGrlxhInfo(Grjgzc query);

    /**
     * 点点击联系函 职业年金联系函
     * @param query
     * @return
     */
    List<Grjgzc> getGrlxhnjInfo(Grjgzc query);


    /**
     * 信息表 参保人基本信息
     */
    List<Grjgzc> getGrxxbInfo(Grjgzc query);

    /**
     * 信息表 查询 历年缴费及个人账户记账信息
     * @param query
     * @return
     */
    List<Grjgzc> getSearchXxb(Grjgzc query);


    /**
     * 查询信息表 标签页 养老信息表
     * @param query
     * @return
     */
    List<Grjgzc> xxbyl(Grjgzc query);


    /**
     * 信息表 职业年金(企业年金)关系转移接续信息表
     * @param query
     * @return
     */
    List<Grjgzc> grxxbnjInfo(Grjgzc query);

    /**
     * 获取全部没有检查的
     * @param query
     * @return
     */
    List<Grjgzc> getNoAllcheck(Grjgzc query);

}
