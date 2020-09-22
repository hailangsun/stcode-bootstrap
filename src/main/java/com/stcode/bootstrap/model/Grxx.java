package com.stcode.bootstrap.model;

import lombok.Data;

/**
 * 个人信息类 - 通用类，后面的类继承即可
 *
 */
@Data
public class Grxx {

    //个人ID
    private String grid;
    //统一社会信用代码 [组织机构代码]
    private String dwdm;
    //单位名称
    private String dwmc;
    //个人姓名
    private String grname;
    //性别
    private String xb;

    //公民身份证号码:
    private String bzhm;
    //电脑序号
    private String bxh;
    //人员身份[职工身份  (dmm=0206)]
    private String grsf;
    //发放地点
    private String ffdd;
    //离退休金

    //

}
