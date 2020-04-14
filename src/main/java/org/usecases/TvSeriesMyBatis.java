package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.interceptors.LoggedInvocation;
import org.mybatis.dao.TvseriesMapper;
import org.mybatis.model.Tvseries;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class TvSeriesMyBatis {
    @Inject
    private TvseriesMapper tvseriesMapper;

    @Getter
    private List<Tvseries> allTvSeries;

    @Getter @Setter
    private Tvseries tvSeriesToCreate = new Tvseries();

    @PostConstruct
    public void init() {
        this.loadAllTeams();
    }

    private void loadAllTeams() {
        this.allTvSeries = tvseriesMapper.selectAll();
    }

    @Transactional
    public String createTvSeries() {
        tvseriesMapper.insert(tvSeriesToCreate);
        return "/myBatis/tvSeries?faces-redirect=true";
    }
}