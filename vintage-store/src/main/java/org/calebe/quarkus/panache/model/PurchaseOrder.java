package org.calebe.quarkus.panache.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calebe.quarkus.jpa.Customer;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "t_purchase_orders")
public class PurchaseOrder extends PanacheEntity {
    @Column(name = "purchase_order_date", nullable = false)
    public LocalDate date = LocalDate.now();

    @OneToMany(mappedBy = "purchaseOrder")//It points out to the object which will be the fk on the other class. THis is done to prevent the creation of another table with both ids.
    public List<OrderLine> orderLines = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_fk")
    public Customer customer;

    @Column(name = "created_date")
    public Instant createdDate = Instant.now();

    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
        orderLine.purchaseOrder = this;
    }
}
