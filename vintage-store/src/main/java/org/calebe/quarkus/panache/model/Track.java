package org.calebe.quarkus.panache.model;

import java.time.Duration;
import java.time.Instant;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "t_tracks")
public class Track extends PanacheEntity {
    @Column(nullable = false)
    public String title;
    @Column(nullable = false)
    public Duration duration;
    @Column(name = "created_date",nullable = false)
    public Instant createdDate = Instant.now();
    @JsonbTransient//Ele faz com que esse campo não seja serializado em json
    @ManyToOne
    @JoinColumn(name = "cd_fk")//SO, the JoinColumn only works on many to one, cause here or in other table outside both it will be the the column with the ids of the other column
    public CD cd;
}
