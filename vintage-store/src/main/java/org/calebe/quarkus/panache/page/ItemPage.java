package org.calebe.quarkus.panache.page;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.calebe.quarkus.panache.model.Book;
import org.calebe.quarkus.panache.model.CD;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@Path("/page/items")
@Produces(MediaType.TEXT_HTML)
public class ItemPage {
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance book(Book book);
        public static native TemplateInstance books(List<Book> books);
        public static native TemplateInstance cd(CD cd);
        public static native TemplateInstance cds(List<CD> cds);
    }

    @GET
    @Path("/books/{id}")
    public TemplateInstance showBookById(@PathParam("id") long id) {
        return Templates.book(Book.findById(id));
    }

    @GET
    @Path("/books")
    public TemplateInstance showAllBooks
        (
            @QueryParam("query") String query,//Parameteres will be passed on the url
            @QueryParam("sort") @DefaultValue("id") String sort,  
            @QueryParam("page") @DefaultValue("0") Integer pageIndex, 
            @QueryParam("size") @DefaultValue("1000") Integer pageSize
        ) {
        return Templates.books(Book.find(query, Sort.by(sort)).page(pageIndex, pageSize)
            .list())
            .data("query", query)
            .data("sort", sort)
            .data("pageIndex", pageIndex)
            .data("pageSize", pageSize);
    }

    @GET
    @Path("/cds/{id}")
    public TemplateInstance showCDById(@PathParam("id") long id) {
        return Templates.cd(CD.findById(id));
    }

    @GET
    @Path("/cds")
    public TemplateInstance showAllCDs() {
        return Templates.cds(CD.listAll());
    }

}
