package org.calebe.quarkus.panache.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import javax.persistence.EntityNotFoundException;

import org.calebe.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PublisherRepositoryTest {

    @Test
    @TestTransaction
    public void shouldCreateAndFindAPublisher() throws SQLException {

        long count = Publisher.count();
        int listAllSize =  Publisher.listAll().size();
        assertEquals(count, listAllSize);

        Publisher publisher = new Publisher("name");

        Publisher.persist(publisher);
        assertNotNull(publisher.id);

        assertEquals(count + 1, Publisher.count());//Same idea

        publisher = Publisher.findById(publisher.id);
        publisher = Publisher.findByName(publisher.name).orElseThrow(EntityNotFoundException::new);
        assertEquals("name", publisher.name);
        assertTrue(Publisher.findContainName("name").size() >= 1);
        Publisher.deleteById(publisher.id);
        assertEquals(count, Publisher.count());
    }

    
}