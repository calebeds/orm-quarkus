package org.calebe.quarkus.panache.repository;

import javax.enterprise.context.ApplicationScoped;

import org.calebe.quarkus.jdbc.Artist;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {
    
}
