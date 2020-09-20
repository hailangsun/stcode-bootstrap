package com.stcode.bootstrap.common.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Option
{
    /**
     * 名称
     * @return
     */
    String name();

    /**
     * 代码，key
     * @return
     */
    String code() default "";
}
