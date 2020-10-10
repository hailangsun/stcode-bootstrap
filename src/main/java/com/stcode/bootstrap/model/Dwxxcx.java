package com.stcode.bootstrap.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 1.2.17  监督检查-日常检查-单位信息查询
 * [dwxxcx -> 单位信息查询]
 */
@Data
public class Dwxxcx extends Jcjg{

    //经代办机构
    private String dbjg;
    private String[] dbjgs;

    //险种
    private String xz;
    //统一社会信用代码
    private String xydm;
    //单位名称
    private String dwmc;
    //单位类型
    private String dwlx;
    //日期
    private String dateFrom;
    private String dateTo;
    //机关事业标识
    private String jgsybs;
    //增加方式
    private String zjfs;
    //缴费类型
    private String jftj;
    //隶属关系
    private String lsgx;
    //行业性质(代码
    private String hydm;
    //经办人
    private String jbr;
    //重点关注
    private String zdgz;
    //检查类型
    private String jclx;
    //所属区县
    private String ssqx;




    //目录
    private String xm;

    private String[] dmids;

    //
    private String dmmxid;
    // 代码类别ID
    private String dmlbid;
    //
    private String mxmc;


    //三险单位增减信息
    //单位全称
    private String dwdm;
    //缴费经(代)办机构
    private String jfqx;
    //经办机构
    private String zfqx;
    //社保登记号
    private String djzh;
    //增减日期
    private String zjrq;
    //养老
    private String yl;
    //失业
    private String sy;
    //工伤
    private String gs;
    //机关养老
    private String jgyl;


    //分页
    private String offset;
    private String limit;

    //检查结果ID
    private String[] formIds;
    //个人ID
    private String[] grIds;
    //公司id
    private String[] dwids;
    //接收个人ID和单位id
    List<JcjgVo> jcjgs;
    /**
     * 主页导出用
     */
    private String mainoffset;
    private String mainlimit;
    /**
     * 二级页面细单导出用
     */
    private String detailoffset;
    private String detaillimit;
    //是否全部检查，用于判断是本页检查还是全部检查
    private String checkFlag;
    //是否分页导出
    private String isAllExprot;

}
