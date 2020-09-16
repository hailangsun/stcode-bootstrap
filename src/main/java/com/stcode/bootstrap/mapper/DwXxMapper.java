package com.stcode.bootstrap.mapper;


import java.util.List;


import com.stcode.bootstrap.domain.DwXx;
import com.stcode.bootstrap.domain.DwXxExample;
import com.stcode.bootstrap.domain.Dwxxcx;
import com.stcode.bootstrap.export.DwxxcxExport;
import org.apache.ibatis.annotations.Param;

public interface DwXxMapper {
    long countByExample(DwXxExample example);

    int deleteByExample(DwXxExample example);

    int insert(DwXx record);

    int insertSelective(DwXx record);

    List<DwXx> selectByExample(DwXxExample example);

    int updateByExampleSelective(@Param("record") DwXx record, @Param("example") DwXxExample example);

    int updateByExample(@Param("record") DwXx record, @Param("example") DwXxExample example);

    /**
     * 根据条件查询
     * @param dwxxcx
     * @return
     */
    List<DwXx> getCbdwxx(Dwxxcx dwxxcx);

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
    List<Dwxxcx> selectJcjgByDwid(@Param("dwid") String dwid);

    /**
     * 根据JCJGID 更新数据,点击从新检查
     */
    void updateJCJGByjcid(Dwxxcx upjcjg);

    /**
     * 查询三险单位增减信息
     * @param dwid
     * @return
     */
    List<Dwxxcx> getZJByDwid(@Param("dwid") String dwid);

    /**
     * 导出查询所有
     */
    int getAllNum();

    List<DwXx> getAllExprot();

}