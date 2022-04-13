package org.calebe.quarkus.panache;

import java.time.Instant;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Publisher extends PanacheEntity { //Interesting that the attributes can be public
    //Aqui usamos panache
    public String name;
    public Instant createdDate = Instant.now();

    public Publisher() {

    }

    public Publisher(String name) {
        this.name = name;
    }

   
}