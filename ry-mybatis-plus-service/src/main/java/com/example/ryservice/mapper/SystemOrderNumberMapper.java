package com.example.ryservice.mapper;

import com.example.ryservice.entity.SystemOrderNumberEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 系统单号规则数据访问接口。
 */
public interface SystemOrderNumberMapper {

    /**
     * 根据系列编码锁定并查询单号规则。
     *
     * @param number 系列编码
     * @return 单号规则
     */
    @Select("select "
            + "FId as id, "
            + "FPrefix as prefix, "
            + "FSuffix as suffix, "
            + "FNumber as number, "
            + "FType as type, "
            + "FDigit as digit, "
            + "FFormat as format, "
            + "FStartnumber as startNumber, "
            + "FCurrentnumber as currentNumber, "
            + "FIscircle as isCircle, "
            + "FRandomsetting as randomSetting "
            + "from system_order_number "
            + "where FNumber = #{number} "
            + "limit 1 for update")
    SystemOrderNumberEntity selectByNumber(@Param("number") String number);

    /**
     * 更新当前流水号。
     *
     * @param id 单号规则主键
     * @param currentNumber 新的当前流水号
     * @return 受影响行数
     */
    @Update("update system_order_number "
            + "set FCurrentnumber = #{currentNumber} "
            + "where FId = #{id}")
    int updateCurrentNumber(@Param("id") Long id, @Param("currentNumber") Long currentNumber);
}
