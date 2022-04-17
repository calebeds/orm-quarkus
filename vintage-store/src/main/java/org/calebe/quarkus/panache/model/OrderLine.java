package org.calebe.quarkus.panache.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "t_purchase_order_line")
public class OrderLine extends PanacheEntity {
    
    @ManyToOne
    @JoinColumn(name = "item_fk")
    public Item item;
    @Column(nullable = false)
    public Integer quantity;
    @ManyToOne
    @JoinColumn(name = "purchase_order_fk")
    public PurchaseOrder purchaseOrder;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();
}
