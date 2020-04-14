package org.persistence;

import org.entities.Episode;
import org.entities.TvSeries;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class TvSeriesDAO {
    @Inject
    private EntityManager em;

    public List<TvSeries> loadAll() {
        return em.createNamedQuery("TvSeries.findAll", TvSeries.class).getResultList();
    }

    public void persist(TvSeries tvSeries) throws Exception{
        try {
            this.em.persist(tvSeries);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public TvSeries findOne(Integer id){
        return em.find(TvSeries.class,id);
    }
}