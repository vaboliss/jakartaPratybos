package org.persistence;

import org.entities.Actor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ActorDAO {
    @Inject
    private EntityManager em;

    public List<Actor> loadAll() {
        return em.createNamedQuery("Actors.findAll", Actor.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Actor actor){
        this.em.persist(actor);
    }

    public Actor findOne(Integer id){
        return em.find(Actor.class, id);
    }

    public void merge(Actor actor)
    {
        this.em.merge(actor);
    }
}