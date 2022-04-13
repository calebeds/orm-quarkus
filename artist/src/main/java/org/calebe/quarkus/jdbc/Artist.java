package org.calebe.quarkus.jdbc;

import java.time.Instant;

public class Artist {
    //Aqui usamos POJO Plain Old Java Object
    private long id;
    private String name;
    private String bio;
    private Instant createdDate = Instant.now();


    public Artist() {
        
    }


    public Artist(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getBio() {
        return bio;
    }


    public void setBio(String bio) {
        this.bio = bio;
    }


    public Instant getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    
}