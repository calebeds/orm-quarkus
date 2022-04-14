package org.calebe.quarkus.panache.repository;

import javax.enterprise.context.ApplicationScoped;

import org.calebe.quarkus.jpa.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    
}
