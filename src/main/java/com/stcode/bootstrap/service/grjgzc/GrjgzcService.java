package com.stcode.bootstrap.service.grjgzc;

import com.stcode.bootstrap.model.Grjgzc;
import com.stcode.bootstrap.model.Grkszc;
import com.stcode.bootstrap.utils.R;

/**
 * 1.2.27监督检查-日常检查-机关事业单位转出人员信息查询
 */
public interface GrjgzcService {

    /**
     * 查询主页表格信息
     *
     * @param query
     * @return
     */
    R search(Grjgzc query);


    /**
     * 查询缴费凭证 个人详情信息
     */
    R getGRDetailInfo(Grjgzc query);


    /**
     * 点击联系函 基本养老转移接续联系函
     * @param query
     * @return
     */
    R getGrlxhInfo(Grjgzc query);

    /**
     * 点击联系函 职业年金联系函
     * @param query
     * @return
     */
    R getGrlxhnjInfo(Grjgzc query);

    /**
     * 信息表 参保人基本信息
     * @param query
     * @return
     */
    R getGrxxbInfo(Grjgzc query);

    /**
     * 信息表 查询 历年缴费及个人账户记账信息
     * @param query
     * @return
     */
    R searchXxb(Grjgzc query);

    /**
     * 查询信息表 标签页 养老信息表
     * @param query
     * @return
     */
    R xxbyl(Grjgzc query);



    /**
     * 信息表 职业年金(企业年金)关系转移接续信息表
     * @param query
     * @return
     */
    R grxxbnjInfo(Grjgzc query);


    /**
     * 新增检查结果jcjg
     */
    R insertJcjg(Grjgzc query);

    /**
     * 新增检查结果jcjg
     */
    R restartCheckJcjg(Grjgzc query);


    /**
     * 检查审核，生成检查审核结果
     * @param query
     * @return
     */
    R examineCheckJcjg(Grjgzc query);
}
