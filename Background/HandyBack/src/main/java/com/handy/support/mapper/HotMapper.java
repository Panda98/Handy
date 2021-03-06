package com.handy.support.mapper;

import com.handy.support.entity.Hot;
import com.handy.support.entity.HotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HotMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot
     *
     * @mbggenerated
     */
    int countByExample(HotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot
     *
     * @mbggenerated
     */
    int deleteByExample(HotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot
     *
     * @mbggenerated
     */
    int insert(Hot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot
     *
     * @mbggenerated
     */
    int insertSelective(Hot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot
     *
     * @mbggenerated
     */
    List<Hot> selectByExample(HotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Hot record, @Param("example") HotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Hot record, @Param("example") HotExample example);
}