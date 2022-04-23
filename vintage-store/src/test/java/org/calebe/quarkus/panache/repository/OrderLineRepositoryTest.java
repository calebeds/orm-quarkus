package org.calebe.quarkus.panache.repository;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.inject.Inject;

import org.calebe.quarkus.jdbc.Artist;
import org.calebe.quarkus.jpa.Customer;
import org.calebe.quarkus.panache.model.Book;
import org.calebe.quarkus.panache.model.Language;
import org.calebe.quarkus.panache.model.OrderLine;
import org.calebe.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class OrderLineRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    ArtistRepository artistRepository;


    @Test
    @TestTransaction
    public void shouldCreateAndFindAOrderLine() throws SQLException {
        //Creates an Artist
        Artist artist = new Artist("artist name", "artist bio");
        
        //Creates a Publisher
        Publisher publisher = new Publisher("publisher name");//Why there was no need to persist publisher or artist before? Didn't underdant that
        //Creates a Book
        Book book = new Book();
        book.title = "title of the book";
        book.nbOfPages = 500;
        book.language = Language.ENGLISH;
        book.price = new BigDecimal(10);
        book.isbn = "isnb";

        //Sets the relationships
        book.publisher = publisher;
        book.artist = artist;

        Book.persist(book);

        //Creates a customer
        Customer customer = new Customer("customer first name", "customer last name", "customer email");
        customerRepository.persist(customer);

        //Creates an order line
        OrderLine orderLine = new OrderLine();
        orderLine.item = book;
        orderLine.quantity = 2;

        OrderLine.persist(orderLine);

        assertNotNull(orderLine.id);//Id created automatically


    }

    
}