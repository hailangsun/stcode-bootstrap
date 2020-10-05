package com.stcode.bootstrap.domain;

import com.stcode.bootstrap.common.annotation.Dict;
import lombok.Data;

@Data
public class User {
    @Dict(dictCode = "sex")
    private String sex;
    private Integer id;
    private String name;

}