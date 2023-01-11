package com.uniyaz.filmactor.domain;

import com.uniyaz.actor.domain.Actor;
import com.uniyaz.common.domain.BaseEntity;
import com.uniyaz.film.domain.Film;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Table(name = "film_actor")
@Entity
@Audited
public class FilmActor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_actor_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    @ForeignKey(name = "fk_film_actor_film")
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    @ForeignKey(name = "fk_film_actor_actor")
    private Actor actor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "FilmActor{" +
                "id=" + id +
                ", film=" + film +
                ", actor=" + actor +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}