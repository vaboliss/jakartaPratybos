package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.model.Actor;
import org.mybatis.dao.ActorMapper;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
@Model
public class ActorsMyBatis {
    @Inject
    private ActorMapper actorMapper;
    @Getter
    @Setter
    private Actor actorToCreate = new Actor();

    @Getter
    private List<Actor> allActors;

    @PostConstruct
    public void init() {
        loadAllActors();
    }

    public void loadAllActors() {
        this.allActors = actorMapper.selectAll();
    }
    @Transactional
    public String createActor() throws Exception {
        this.actorMapper.insert(actorToCreate);
        return "actors?faces-redirect=true";
    }
}
