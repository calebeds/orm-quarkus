package org.calebe.quarkus.panache.resource;

import java.util.List;

import javax.transaction.Transactional;
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

import org.calebe.quarkus.panache.model.CD;

@Path("api/cds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CDResource {

    @GET
    @Path("{id}")
    public CD findCDById(@PathParam("id") long id) {
        return (CD) CD.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }
    
    @GET
    public List<CD> listAllCDs() {
        return CD.listAll();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistCD(CD cd, @Context UriInfo uriInfo) {
        CD.persist(cd);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(cd.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteCD(@PathParam("id") long id) {
        CD.deleteById(id);
    }
}
