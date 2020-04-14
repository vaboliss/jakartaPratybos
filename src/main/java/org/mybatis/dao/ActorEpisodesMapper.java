package org.mybatis.dao;

import org.mybatis.cdi.Mapper;
import org.mybatis.model.Actor;
import org.mybatis.model.ActorEpisodes;
import org.mybatis.model.Episodes;

import java.util.List;
@Mapper
public interface ActorEpisodesMapper {

    List<Actor> getActors(Integer episodeId);
    List<Episodes> getEpisodes(Integer actorId);
    int insert(ActorEpisodes record);
    int delete(ActorEpisodes record);
}
