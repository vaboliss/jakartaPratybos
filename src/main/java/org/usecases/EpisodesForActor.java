package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Actor;
import org.entities.Episode;
import org.interceptors.LoggedInvocation;
import org.persistence.ActorDAO;
import org.persistence.EpisodeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

@Model
public class EpisodesForActor {

    @Inject
    private EpisodeDAO episodeDAO;

    @Inject
    private ActorDAO actorDAO;

    @Getter @Setter
    private Actor actor;

    @Getter @Setter
    private List<Episode> actedEpisodes;

    @Getter @Setter
    private List<Episode> availableEpisodes;

    @PostConstruct
    @LoggedInvocation
    public void init()
    {
        Map<String,String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer actorId = Integer.parseInt(requestParameters.get("actorId"));
        this.actor = actorDAO.findOne(actorId);
        this.actedEpisodes = this.actor.getActedEpisodes();
        loadActors();
    }

    private void loadActors() {
        availableEpisodes = episodeDAO.loadAll();
        availableEpisodes.removeAll(this.actor.getActedEpisodes());
    }

    @Transactional
    public String Add(Episode episode) throws Exception {
        episode.getActors().add(this.actor);
        episodeDAO.merge(episode);
        return "episodesForActor?faces-redirect=true&actorId="+this.actor.getId();
    }
    @Transactional
    public String remove(Episode episode) throws Exception {
        episode.getActors().remove(this.actor);
        episodeDAO.merge(episode);
        return "episodesForActor?faces-redirect=true&actorId="+this.actor.getId();
    }
}
