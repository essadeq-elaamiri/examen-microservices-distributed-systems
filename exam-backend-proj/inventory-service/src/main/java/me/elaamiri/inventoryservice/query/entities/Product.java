package me.elaamiri.inventoryservice.query.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.elaamiri.enumerations.ProductStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {

    @Id
    private String id;

    private String nom;

    private double prix;

    private int quentiteStocke;

    private ProductStatus productStatus;


    private String categoryId;

    @ManyToOne
    private Category category;
}
