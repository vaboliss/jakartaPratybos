package org.mybatis.dao;

import org.mybatis.cdi.Mapper;
import org.mybatis.model.Tvseries;

import java.util.List;

@Mapper
public interface TvseriesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TVSERIES
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TVSERIES
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    int insert(Tvseries record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TVSERIES
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    Tvseries selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TVSERIES
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    List<Tvseries> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TVSERIES
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    int updateByPrimaryKey(Tvseries record);
}