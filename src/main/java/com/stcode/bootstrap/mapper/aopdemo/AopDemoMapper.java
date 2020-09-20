package com.stcode.bootstrap.mapper.aopdemo;

import com.stcode.bootstrap.domain.DictDomain;
import com.stcode.bootstrap.domain.User;
import com.stcode.bootstrap.model.Grkszc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AopDemoMapper {

    /**
     * 预留后续删除
     *
     */
    List<User> search();

   DictDomain byTypeAndCode(@Param("dictType")String dictType,@Param("key") String key);
}