package com.example.ryservice.mapper;

import com.example.ryservice.entity.SystemDictionaryEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统字典数据访问接口。
 */
public interface SystemDictionaryMapper {

    /**
     * 根据父级字典编码查询子级字典列表。
     *
     * @param parentNumber 父级字典编码
     * @return 子级字典列表
     */
    @Select("select mainTable.FId as id, mainTable.FName as name, mainTable.FNumber as number, mainTable.FVal as val "
            + "from system_dictionary mainTable "
            + "left join system_dictionary parent on parent.FId = mainTable.FParentID "
            + "where mainTable.FIsDelete = 0 and parent.FNumber = #{parentNumber} "
            + "order by mainTable.FIndex desc, mainTable.FID desc")
    List<SystemDictionaryEntity> selectByParentNumber(@Param("parentNumber") String parentNumber);

    /**
     * 根据字典编码查询单条字典记录。
     *
     * @param number 字典编码
     * @return 字典记录，未命中时返回 `null`
     */
    @Select("select FId as id, FName as name, FNumber as number, FVal as val "
            + "from system_dictionary "
            + "where FIsDelete = 0 and FNumber = #{number} "
            + "limit 1")
    SystemDictionaryEntity selectByNumber(@Param("number") String number);
}
