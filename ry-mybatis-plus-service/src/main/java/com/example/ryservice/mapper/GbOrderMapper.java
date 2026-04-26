package com.example.ryservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ryservice.entity.GbOrderEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * `gb_order` 数据访问接口。
 */
public interface GbOrderMapper extends BaseMapper<GbOrderEntity> {

    /**
     * 按瑞云来源单号查询已存在工单。
     *
     * @param promoter 瑞云来源单号
     * @return 已存在的工单，未命中时返回 `null`
     */
    @Select("select * from gb_order where promoter = #{promoter} limit 1")
    GbOrderEntity selectByPromoter(@Param("promoter") String promoter);
}
