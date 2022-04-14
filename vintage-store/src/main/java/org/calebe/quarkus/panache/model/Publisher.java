package org.calebe.quarkus.panache.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "t_publishers")
public class Publisher extends PanacheEntity { //Interesting that the attributes can be public
    //Aqui usamos panache
    @Column(length = 50, nullable = false)
    public String name;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();

    public Publisher() {

    }

    public Publisher(String name) {
        this.name = name;
    }

   
}