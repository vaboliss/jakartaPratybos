package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.dao.ActorEpisodesMapper;
import org.mybatis.dao.ActorMapper;
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
public class ActorsForEpisodeMyBatis {

    @Inject
    private ActorEpisodesMapper actorsEpisodeMapper;
    @Inject
    private ActorMapper actorMapper;

    @Getter
    @Setter
    private Episodes episode;

    @Getter
    @Setter
    private List<Actor> actorsThatActs;

    @Getter
    @Setter
    private List<Actor> availableActors;
    @Getter
    @Setter
    private Integer episodeId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        episodeId = Integer.parseInt(requestParameters.get("episodeId"));
        loadActors();
    }

    private void loadActors() {
        availableActors = actorMapper.selectAll();
        actorsThatActs = actorsEpisodeMapper.getActors(episodeId);
        availableActors.removeAll(actorsThatActs);
    }

    @Transactional
    public String Add(Actor actor) throws Exception {
        ActorEpisodes actorEpisodes = new ActorEpisodes();
        actorEpisodes.setActorId(actor.getId());
        actorEpisodes.setEpisodeId(this.episodeId);
        actorsEpisodeMapper.insert(actorEpisodes);
        return "actorsForEpisodes?faces-redirect=true&episodeId=" + this.episodeId;
    }

    @Transactional
    public String remove(Actor actor) throws Exception {
        ActorEpisodes actorEpisodes = new ActorEpisodes();
        actorEpisodes.setActorId(actor.getId());
        actorEpisodes.setEpisodeId(this.episodeId);
        actorsEpisodeMapper.delete(actorEpisodes);
        return "actorsForEpisodes?faces-redirect=true&episodeId="+ this.episodeId;
    }
}
