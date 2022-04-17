package org.calebe.quarkus.panache.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "t_items") //So here we see a way of creating a super class and a bd
@Inheritance(strategy = InheritanceType.JOINED)//Inheritance type
public class Item extends PanacheEntity{

    //Os métodos serem públicos is kind of weird haha
    @Column(length = 100, nullable = false)
    public String title;
    @Column(length = 3000)
    public String description;
    @Column(nullable = false)
    public BigDecimal price;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();
}
