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

import org.calebe.quarkus.panache.model.OrderLine;

@Path("api/orderline")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(TxType.SUPPORTS)
public class OrderLineResource {

    @GET
    @Path("{id}")
    public OrderLine findOrderLineById(@PathParam("id") long id) {
        return (OrderLine) OrderLine.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }
    
    @GET
    public List<OrderLine> listAllOrderLines() {
        return OrderLine.listAll();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistOrderLine(OrderLine orderLine, @Context UriInfo uriInfo) {
        OrderLine.persist(orderLine);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(orderLine.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteOrderLine(@PathParam("id") long id) {
        OrderLine.deleteById(id);
    }
}
