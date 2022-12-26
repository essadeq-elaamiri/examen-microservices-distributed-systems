package me.elaamiri.events.productEvents;

import lombok.Getter;
import me.elaamiri.enumerations.ProductStatus;
import me.elaamiri.events.BaseEvent;

public class ProductCreatedEvent extends BaseEvent<String> {
    @Getter
    private String nom;
    @Getter
    private double prix;
    @Getter
    private int quentiteStocke;
    @Getter
    private ProductStatus productStatus;

    @Getter
    private String categoryId;

    public ProductCreatedEvent(String id, String nom, double prix, int quentiteStocke, ProductStatus productStatus, String categoryId) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.quentiteStocke = quentiteStocke;
        this.productStatus = productStatus;
        this.categoryId = categoryId;
    }
}
