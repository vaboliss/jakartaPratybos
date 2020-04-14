package org.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.model.Actor;
import org.mybatis.cdi.Mapper;
import org.mybatis.model.Episodes;

import java.util.List;

@Mapper
public interface ActorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ACTOR
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ACTOR
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    int insert(Actor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ACTOR
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    Actor selectByPrimaryKey(Integer id);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ACTOR
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    List<Actor> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ACTOR
     *
     * @mbg.generated Wed Apr 08 19:11:35 EEST 2020
     */
    List<Episodes> selectEpisodesForActor(Integer id);

    void setEpisodes(List<Episodes> episodesList);
    int updateByPrimaryKey(Actor record);
}