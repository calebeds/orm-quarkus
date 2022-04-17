package org.calebe.quarkus.panache.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calebe.quarkus.jdbc.Artist;

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
    //A chave estrangeira estará na própria nesta table ao fazer o Join, como serão muitas dessa classe, poderá haver um id de artistas para várias rows de items por exemplo
    @ManyToOne
    @JoinColumn(name = "artist_fk")//É Coluna do Join so to speak. O método permanece o mesmo, o que muda é o nome da coluna que antes era artist_id. Apenas explicita que é uma chave estrangeira. No Jointable as chaves ficariam em outra table para realizar o JOin. SErá que é assim que funciona o manytomany? 
    public Artist artist;
}
