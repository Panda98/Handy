package com.handy.support.mapper;

import com.handy.support.entity.View;
import com.handy.support.entity.ViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ViewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    int countByExample(ViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    int deleteByExample(ViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer viewId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    int insert(View record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    int insertSelective(View record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    List<View> selectByExample(ViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    View selectByPrimaryKey(Integer viewId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") View record, @Param("example") ViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") View record, @Param("example") ViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(View record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(View record);
}