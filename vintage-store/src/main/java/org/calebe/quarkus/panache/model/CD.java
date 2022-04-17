package org.calebe.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CD extends Item {
    @Column(name = "music_company")
    public String musicCompany;
    public String genre;
}
