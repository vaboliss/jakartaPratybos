package org.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "TvSeries.findAll", query = "select a from TvSeries as a")
})

@Table(name = "TVSERIES")
@Getter @Setter
public class TvSeries implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "tvSeries")
    private List<Episode> episodes = new ArrayList<>();


    public TvSeries(){ }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TvSeries tvSeries = (TvSeries) o;
        return Objects.equals(id, tvSeries.id) &&
                Objects.equals(name, tvSeries.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

