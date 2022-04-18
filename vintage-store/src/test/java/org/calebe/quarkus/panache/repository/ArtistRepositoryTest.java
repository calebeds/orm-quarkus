package org.calebe.quarkus.panache.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import javax.inject.Inject;

import org.calebe.quarkus.jdbc.Artist;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;



@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository repository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindArtist() throws SQLException {

        long count = repository.count();
        int listAllSize =  repository.listAll().size();
        assertEquals(count, listAllSize);//Check if the sizes match
        assertEquals(repository.listAllArtistsSorted().size(), listAllSize);//Check if the sizes match

        Artist artist = new Artist("name", "bio");

        repository.persist(artist);
        assertNotNull(artist.getId());

        assertEquals(count + 1, repository.count());//Adicionou um artista, veja se adicionou no bd

        repository.findById(artist.getId());
        assertEquals("name", artist.getName());

        repository.deleteById(artist.getId());//Deletado :)
        assertEquals(count, repository.count());//It must return to the initial count
    }

}