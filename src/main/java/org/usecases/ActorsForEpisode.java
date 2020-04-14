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
public class ActorsForEpisode {

    @Inject
    private EpisodeDAO episodeDAO;

    @Inject
    private ActorDAO actorDAO;

    @Getter @Setter
    private Episode episode;

    @Getter @Setter
    private List<Actor> actorsThatActs;

    @Getter @Setter
    private List<Actor> availableActors;

    @PostConstruct
    public void init()
    {
        Map<String,String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer episodeId = Integer.parseInt(requestParameters.get("episodeId"));
        this.episode = episodeDAO.findOne(episodeId);
        this.actorsThatActs = this.episode.getActors();
        loadActors();
    }

    private void loadActors() {

        availableActors = actorDAO.loadAll();
        availableActors.removeAll(episode.getActors());
    }

    @Transactional
    public String Add(Actor actor) throws Exception {
        episode.getActors().add(actor);
        episodeDAO.merge(episode);
        return "actorsForEpisode?faces-redirect=true&episodeId="+this.episode.getId();
    }
    @Transactional
    public String remove(Actor actor) throws Exception {
        episode.getActors().remove(actor);
        episodeDAO.merge(episode);
        return "actorsForEpisode?faces-redirect=true&episodeId="+this.episode.getId();
    }
}
