package com.stcode.bootstrap.mapper.dwxxcx;

import com.stcode.bootstrap.model.Dwxxcx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DwXxMapper {
    /**
     * 根据条件查询
     * @param dwxxcx
     * @return
     */
    List<Dwxxcx> getCbdwxx(Dwxxcx dwxxcx);

    /**
     * 动态获取查询检查信息列表，用于前端页面展示
     * @param jcids
     * @return
     */
    List<String> queryMXMC(@Param("jcids") String[] jcids);

    /**
     * 更新检查结果
     * @param dwid
     * @param jcid
     */
    void updateByDwid(@Param("dwid") String dwid, @Param("jcid") String jcid);

    /**
     * 新增检查结果表，此表为中间表，便于维护
     * @param dwxxcx
     */
    void insertJcjg(Dwxxcx dwxxcx);

    /**
     * 根据单位ID查询检查结果
     */
    List<Dwxxcx> selectJcjgByDwid(Dwxxcx query);

    /**
     * 根据JCJGID 更新数据,点击从新检查
     */
    void updateJCJGByjcid(Dwxxcx upjcjg);

    /**
     * 查询三险单位增减信息
     * @param query
     * @return
     */
    List<Dwxxcx> getZJByDwid(Dwxxcx query);

    /**
     * 获取全部没有检查的
     * @param query
     * @return
     */
    List<Dwxxcx> getNoAllcheck(Dwxxcx query);
}