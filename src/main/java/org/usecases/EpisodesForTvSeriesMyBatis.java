package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.model.Episodes;
import org.mybatis.model.Tvseries;
import org.interceptors.LoggedInvocation;
import org.mybatis.dao.EpisodesMapper;
import org.mybatis.dao.TvseriesMapper;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class EpisodesForTvSeriesMyBatis {

        @Inject
        private TvseriesMapper tvseriesMapper;

        @Inject
        private EpisodesMapper episodesMapper;

        @Getter
        @Setter
        private Episodes episodeToCreate = new Episodes();


        @Getter @Setter
        private List<Episodes> episodesList = new ArrayList<Episodes>();

        @Getter @Setter
        private Integer tvSeriesId;

        @PostConstruct
        public void init()
        {
            Map<String,String> requestParameters =
                    FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            tvSeriesId = Integer.parseInt(requestParameters.get("tvSeriesId"));
            this.episodesList = episodesMapper.selectByTvSeriesId(tvSeriesId);
        }
        @Transactional
        public String createEpisode() throws Exception {
            episodeToCreate.setTvseriesId(tvSeriesId);
            episodeToCreate.setNumber(1);
            episodesMapper.insert(episodeToCreate);
            return "episodes?faces-redirect=true&tvSeriesId="+this.tvSeriesId;
        }
    }

