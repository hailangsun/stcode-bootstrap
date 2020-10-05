package com.stcode.bootstrap.commonquery.ylybcx;

import com.stcode.bootstrap.domain.User;
import lombok.Data;

/**
 * 监督检查-日常检查-职工养老月报外支付查询 查询query
 */
@Data
public class YlybcxQuery {

    //代码明细id
    private String dmmxid;
    //汇总日期起
    private String hzdatefrom;
    //汇总日期止
    private String hzdateto;
    //月报生成日期起
    private String ybdatefrom;
    //月报生成日期止
    private String ybdateto;

}
