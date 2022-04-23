package org.calebe.quarkus.panache.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.calebe.quarkus.jdbc.Artist;
import org.calebe.quarkus.panache.model.Book;
import org.calebe.quarkus.panache.model.Language;
import org.calebe.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BookRepositoryTest {

    @Inject
    ArtistRepository artistRepository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindABook()  {
        
        long count = Book.count();//Uses the method count from Book to count all the books
        int listAllSize =  Book.listAll().size();//Here uses the method listAll and the size of the list to get the count
        assertEquals(count, listAllSize);//Test

        //Creates an Artist
        artistRepository.count();
        Artist artist = new Artist("artist random name", "artist random bio");
        artistRepository.persist(artist);
        
        //Creates a Publisher
        Publisher publisher = new Publisher("publisher name");//Why there was no need to persist publisher or artist before? Didn't underdant that
        publisher.persist();//Não estava contando os livros, por isso persisti o publisher


        Book book = new Book();
        book.title = "title of the book";
        book.nbOfPages = 500;
        book.language = Language.ENGLISH;
        book.price = new BigDecimal(10);
        book.isbn = "isnb";

        //Sets the relationships
        book.publisher = publisher;
        book.artist = artist;

        Book.persist(book); //Here the book got added
        assertNotNull(book.id);//An id was created

        assertEquals(count + 1, Book.count());//1 Book added

        Book book2 = Book.findById(book.id);
        assertNotNull(book2);
    }

    
}