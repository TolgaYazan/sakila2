package com.uniyaz.film.domain;

import com.uniyaz.common.domain.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Table(name = "film")
@Entity
@Audited
public class Film extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return title;
    }
}