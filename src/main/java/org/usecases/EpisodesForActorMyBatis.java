package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.dao.ActorEpisodesMapper;
import org.mybatis.dao.ActorMapper;
import org.mybatis.dao.EpisodesMapper;
import org.mybatis.model.Actor;
import org.mybatis.model.ActorEpisodes;
import org.mybatis.model.Episodes;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
@Model
public class EpisodesForActorMyBatis {

    @Inject
    private ActorEpisodesMapper actorsEpisodeMapper;
    @Inject
    private EpisodesMapper episodesMapper;

    @Getter
    @Setter
    private Actor actor;

    @Getter
    @Setter
    private List<Episodes> episodesList;

    @Getter
    @Setter
    private List<Episodes> availableEpisodes;
    @Getter
    @Setter
    private Integer actorId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        actorId = Integer.parseInt(requestParameters.get("actorId"));
        loadActors();
    }

    private void loadActors() {
        availableEpisodes = episodesMapper.selectAll();
        episodesList = actorsEpisodeMapper.getEpisodes(actorId);
        availableEpisodes.removeAll(episodesList);
    }

    @Transactional
    public String Add(Episodes episode) throws Exception {
        ActorEpisodes actorEpisodes = new ActorEpisodes();
        actorEpisodes.setActorId(actorId);
        actorEpisodes.setEpisodeId(episode.getId());
        actorsEpisodeMapper.insert(actorEpisodes);
        return "episodesForActor?faces-redirect=true&actorId=" + this.actorId;
    }

    @Transactional
    public String remove(Episodes episode) throws Exception {
        ActorEpisodes actorEpisodes = new ActorEpisodes();
        actorEpisodes.setActorId(actorId);
        actorEpisodes.setEpisodeId(episode.getId());
        actorsEpisodeMapper.delete(actorEpisodes);
        return "episodesForActor?faces-redirect=true&actorId="+ this.actorId;
    }
}
