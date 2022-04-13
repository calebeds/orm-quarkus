package org.calebe.quarkus.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CustomerRepository {

    @Inject
    EntityManager em;

    public void persist(Customer customer) {
        em.persist(customer);
    }

    public Customer findById(long id) {
        return em.find(Customer.class, id);
    }
}