package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Episode;
import org.entities.TvSeries;
import org.interceptors.LoggedInvocation;
import org.persistence.EpisodeDAO;
import org.persistence.TvSeriesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class EpisodesForTvSeries {

    @Inject
    private TvSeriesDAO tvSeriesDAO;

    @Inject
    private EpisodeDAO episodeDAO;

    @Getter @Setter
    private Episode episodeToCreate = new Episode();

    @Getter @Setter
    private TvSeries tvSeries = new TvSeries();

    @PostConstruct
    public void init()
    {
        Map<String,String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer tvSeriesId = Integer.parseInt(requestParameters.get("tvSeriesId"));
        this.tvSeries = tvSeriesDAO.findOne(tvSeriesId);

    }
    @Transactional
    public String createEpisode() throws Exception {
        episodeToCreate.setTvSeries(this.tvSeries);
        episodeDAO.persist(episodeToCreate);
        return "episodes?faces-redirect=true&tvSeriesId="+this.tvSeries.getId();
    }
}
