package org.calebe.quarkus.panache.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.calebe.quarkus.jpa.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public List<Customer> listAllDans() {
        return list("firstName = 'Dan'", Sort.by("lastName"));
    }
}