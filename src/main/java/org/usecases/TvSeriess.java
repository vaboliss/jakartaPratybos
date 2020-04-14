package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.TvSeries;
import org.persistence.TvSeriesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class TvSeriess implements Serializable {

    @Inject
    private TvSeriesDAO tvSeriesDAO;

    @Getter
    @Setter
    private TvSeries tvSeriesToCreate = new TvSeries();
    @Getter
    private List<TvSeries> allTvSeries;

    @PostConstruct
    public void init() {
        loadAllTvSeries();
    }

    public void loadAllTvSeries() {
        this.allTvSeries = tvSeriesDAO.loadAll();
    }

    public List<TvSeries> getAllTvSeries() {
        return allTvSeries;
    }

    @Transactional
    public String createTvSeries() throws Exception {
        this.tvSeriesDAO.persist(tvSeriesToCreate);
        return "index?faces-redirect=true";
    }
    @Transactional
    public TvSeries getTvSeriesToCreate() {
        return tvSeriesToCreate;
    }

    public void SetTvSeriesToCreate(TvSeries tvSeries) {
        this.tvSeriesToCreate = tvSeriesToCreate;
    }

}
