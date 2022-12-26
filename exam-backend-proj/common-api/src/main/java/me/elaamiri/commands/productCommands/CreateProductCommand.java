package me.elaamiri.commands.productCommands;

import lombok.Getter;
import me.elaamiri.commands.BaseCommand;
import me.elaamiri.enumerations.ProductStatus;
import me.elaamiri.events.BaseEvent;


public class CreateProductCommand extends BaseCommand<String> {

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

    public CreateProductCommand(String id, String nom, double prix, int quentiteStocke, ProductStatus productStatus, String categoryId) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.quentiteStocke = quentiteStocke;
        this.productStatus = productStatus;
        this.categoryId = categoryId;
    }
}
