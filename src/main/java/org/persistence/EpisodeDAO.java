package org.persistence;

import org.entities.Actor;
import org.entities.Episode;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class EpisodeDAO {

    @Inject
    private EntityManager em;

    public void persist(Episode episode) throws Exception{
        try {
            this.em.persist(episode);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void merge(Episode episode){
        this.em.merge(episode);
    }

    public Episode findOne(Integer id){
        return em.find(Episode.class, id);
    }

    public List<Episode> loadAll() {
        return em.createNamedQuery("Episodes.findAll", Episode.class).getResultList();
    }

}
