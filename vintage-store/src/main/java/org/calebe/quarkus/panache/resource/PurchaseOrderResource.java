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

import org.calebe.quarkus.panache.model.PurchaseOrder;

@Path("api/purchaseorder")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(TxType.SUPPORTS)
public class PurchaseOrderResource {

    @GET
    @Path("{id}")
    public PurchaseOrder findPurchaseOrderById(@PathParam("id") long id) {
        return (PurchaseOrder) PurchaseOrder.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }
    
    @GET
    public List<PurchaseOrder> listAllPurchaseOrders() {
        return PurchaseOrder.listAll();
    }

    @Transactional(TxType.REQUIRED)
    @POST
    public Response persistPurchaseOrder(PurchaseOrder purchaseOrder, @Context UriInfo uriInfo) {
        PurchaseOrder.persist(purchaseOrder);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(purchaseOrder.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deletePurchaseOrder(@PathParam("id") long id) {
        PurchaseOrder.deleteById(id);
    }
}
