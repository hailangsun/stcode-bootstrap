package com.stcode.bootstrap.common;

import java.math.BigDecimal;

public class BigDecimalUtils {


    /**
     * BigDecimal的加法运算封装
     * @param b1
     * @param bn
     * @return
     */
    public static BigDecimal add(BigDecimal b1, BigDecimal... bn) {
        if (null == b1) {
            b1 = BigDecimal.ZERO;
        }
        if (null != bn) {
            for (BigDecimal b : bn) {
                b1 = b1.add(null == b ? BigDecimal.ZERO : b);
            }
        }
        return b1;
    }




}