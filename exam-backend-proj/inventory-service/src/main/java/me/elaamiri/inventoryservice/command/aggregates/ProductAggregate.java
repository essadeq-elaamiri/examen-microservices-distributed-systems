package me.elaamiri.inventoryservice.command.aggregates;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.elaamiri.commands.productCommands.CreateProductCommand;
import me.elaamiri.commands.productCommands.DeleteProductCommand;
import me.elaamiri.commands.productCommands.UpdateProductCommand;
import me.elaamiri.enumerations.ProductStatus;
import me.elaamiri.events.productEvents.ProductCreatedEvent;
import me.elaamiri.events.productEvents.ProductDeletedEvent;
import me.elaamiri.events.productEvents.ProductUpdatedEvent;
import me.elaamiri.exceptions.EntryNotFoundException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate @Slf4j
public class ProductAggregate {
    @AggregateIdentifier
    @Getter
    private  String id;
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

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        // validations
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getPrix(),
                command.getQuentiteStocke(),
                command.getProductStatus(),
                command.getCategoryId()
        ));
    }

    @EventSourcingHandler

    public void on(ProductCreatedEvent event){
        this.id = event.getId();
        this.productStatus = event.getProductStatus();
        this.nom = event.getNom();
        this.prix = event.getPrix();
        this.quentiteStocke = event.getQuentiteStocke();
        this.categoryId = event.getCategoryId();
    }


    @CommandHandler
    public void handle(UpdateProductCommand command){

        if(command.getId() == null || command.getId().isBlank()) throw new EntryNotFoundException("Product Not Found.");
        // validations
        AggregateLifecycle.apply(new ProductUpdatedEvent(
                command.getId(),
                command.getNom(),
                command.getPrix(),
                command.getQuentiteStocke(),
                command.getProductStatus(),
                command.getCategoryId()
        ));
    }

    @EventSourcingHandler

    public void on(ProductUpdatedEvent event){
        this.id = event.getId();
        this.productStatus = event.getProductStatus();
        this.nom = event.getNom();
        this.prix = event.getPrix();
        this.quentiteStocke = event.getQuentiteStocke();
        this.categoryId = event.getCategoryId();
    }

    @CommandHandler
    public void handle(DeleteProductCommand command){
        if(command.getId() == null || command.getId().isBlank()) throw new EntryNotFoundException("Product Not Found.");
        // validations
        AggregateLifecycle.apply(new ProductDeletedEvent(
                command.getId()));
    }

    @EventSourcingHandler
    public void on(ProductDeletedEvent event){
        log.warn("Deleting Product: "+ event.getId());
    }


}
