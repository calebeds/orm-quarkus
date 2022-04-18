package org.calebe.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.calebe.quarkus.jpa.Customer;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository repository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindACustomer() {
        long count = repository.count();
        int listAllSize =  repository.listAll().size();
        assertEquals(count, listAllSize);
        assertTrue(repository.listAllDans().size() <= repository.count());//Testing custom query method


        Customer customer = new Customer("first name", "last name", "email");

        repository.persist(customer);
        assertNotNull(customer.getId());

        assertEquals(count + 1, repository.count());

        customer = repository.findById(customer.getId());
        assertEquals("first name", customer.getFirstName());

        repository.deleteById(customer.getId());
        assertEquals(count, repository.count());

    }

}