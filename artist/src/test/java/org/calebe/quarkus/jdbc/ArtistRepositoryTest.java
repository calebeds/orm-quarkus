package org.calebe.quarkus.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;



@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository repository;

    @Test
    public void shouldCreateAndFindArtist() throws SQLException {
        Artist artist = new Artist("name", "bio");

        repository.persist(artist);
        Assertions.assertNotNull(artist.getId());

        repository.findById(artist.getId());
        assertEquals("name", artist.getName());
    }

}