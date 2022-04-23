package org.calebe.quarkus.panache.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.calebe.quarkus.panache.model.Book;

@Path("api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(TxType.SUPPORTS)
public class BookResource {

    @GET
    @Path("{id}")
    public Book findBookById(@PathParam("id") long id) {
        return (Book) Book.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }
    
    @GET
    public List<Book> listAllBooks() {
        return Book.listAll();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistBook(Book book, @Context UriInfo uriInfo) {
        Book.persist(book);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(book.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteBook(@PathParam("id") long id) {
        Book.deleteById(id);
    }
}
