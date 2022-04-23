package org.calebe.quarkus.panache.resource;

import java.util.List;

import javax.inject.Inject;
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

import org.calebe.quarkus.jpa.Customer;
import org.calebe.quarkus.panache.repository.CustomerRepository;

@Path("api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(TxType.SUPPORTS)
public class CustomerResource {
    
    @Inject
    CustomerRepository repository;

    @GET
    @Path("{id}")
    public Customer findCustomerById(@PathParam("id") long id) {
        return repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }
    
    @GET
    public List<Customer> listAllCustomers() {
        return repository.listAll();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistCustomer(Customer customer, @Context UriInfo uriInfo) {
        repository.persist(customer);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(customer.getId()));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") long id) {
        repository.deleteById(id);
    }
}
