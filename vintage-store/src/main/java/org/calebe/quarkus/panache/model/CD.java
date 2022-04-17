package org.calebe.quarkus.panache.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class CD extends Item {
    @Column(name = "music_company")
    public String musicCompany;
    public String genre;
    @OneToMany(mappedBy = "cd")//So, it won't create a new table, because now the attribute cd of the other table is the one that will be choose the relation manytone, but here is onetomany. 
    public List<Track> tracks = new ArrayList<>();
}
