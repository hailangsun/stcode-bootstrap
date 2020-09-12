package com.stcode.bootstrap.mapper;

import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.domain.DmMxExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DmMxMapper {
    long countByExample(DmMxExample example);

    int deleteByExample(DmMxExample example);

    int insert(DmMx record);

    int insertSelective(DmMx record);

    List<DmMx> selectByExample(DmMxExample example);

    int updateByExampleSelective(@Param("record") DmMx record, @Param("example") DmMxExample example);

    int updateByExample(@Param("record") DmMx record, @Param("example") DmMxExample example);

    List<DmMx> getDmMxListByDmm(@Param("dmm") String dmm);
}