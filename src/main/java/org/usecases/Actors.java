package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Actor;
import org.persistence.ActorDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Actors {
    @Inject
    private ActorDAO actorDAO;

    @Getter
    @Setter
    private Actor actorToCreate = new Actor();
    @Getter
    private List<Actor> allActors;

    @Getter
    private List<Actor> get2Actors;
    @PostConstruct
    public void init() {
        loadAllActors();
    }

    public void loadAllActors() {
        this.allActors = actorDAO.loadAll();
    }

    @Transactional
    public String createActor() throws Exception {
        this.actorDAO.persist(actorToCreate);
        return "index?faces-redirect=true";
    }
}

