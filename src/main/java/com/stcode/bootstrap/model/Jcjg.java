package com.stcode.bootstrap.model;

import lombok.Data;

import java.util.Date;

/**
 * 检查结果domain
 */
@Data
public class Jcjg {

    //检查ID
    private String jcid;
    //单位ID
    private String dwid;
    //检查人
    private String jcr;
    //检查日期
    private Date jcrq;
    //检查
    private String jc;
    //重新检查
    private String cxjc;
    //检查结果
    private String jcjg;
    //备注
    private String memo;
    //目录 检查 重新检查
    private String xm;
    //业务序号
    private String ywxh;
    //模块代码
    private String mkdm;
    //模块名称
    private String mkmc;
    //个人id
    private String grid;

    //检查审核人
    private String jcshr;
    //检查审核日期
    private Date jcshrq;
    //检查审核
    private String jcsh;
    //检查审核结果
    private String jcshjg;
    //检查审核备注
    private String jcshbz;
    //重新检查结果
    private String cxjcjg;
    //重新检查前端页面使用
    private String cxxm;
    //重新检查日期
    private Date cxjcrq;
    //重新检查人
    private String cxjcr;
    //重新检查备注
    private String cxmemo;

}
