package org.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Episodes.findAll", query = "select a from Episode as a")
})
@Table(name = "EPISODES")
@Getter @Setter
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int number;

    @ManyToOne
    @JoinColumn(name = "TVSERIES_ID")
    private TvSeries tvSeries;

    @ManyToMany
    @JoinTable(
            name = "ACTOR_EPISODE",
            joinColumns = @JoinColumn(name = "EPISODE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACTOR_ID"))
    List<Actor> actors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode = (Episode) o;
        return Objects.equals(id, episode.id) &&
                Objects.equals(name, episode.name);
    }

}