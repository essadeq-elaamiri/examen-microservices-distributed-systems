package me.elaamiri.inventoryservice.command.aggregates;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.elaamiri.commands.categoryCommands.CreateCategoryCommand;
import me.elaamiri.commands.categoryCommands.DeleteCategoryCommand;
import me.elaamiri.commands.categoryCommands.UpdateCategoryCommand;
import me.elaamiri.events.categoryEvents.CategoryCreatedEvent;
import me.elaamiri.events.categoryEvents.CategoryDeletedEvent;
import me.elaamiri.events.categoryEvents.CategoryUpdatedEvent;
import me.elaamiri.exceptions.EntryNotFoundException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate @Slf4j
public class CategoryAggregate {

    @AggregateIdentifier
    @Getter
    private String id;
    @Getter
    private String nom;

    @Getter
    private String description;

    public CategoryAggregate() {
    }

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        // validations
        AggregateLifecycle.apply(new CategoryCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getDescription()
        ));
    }

    @EventSourcingHandler

    public void on(CategoryCreatedEvent event){
        this.id = event.getId();
        this.nom = event.getNom();
        this.description=event.getDescription();
    }


    @CommandHandler
    public void handle(UpdateCategoryCommand command){

        if(command.getId() == null || command.getId().isBlank()) throw new EntryNotFoundException("Category Not Found.");
        // validations
        AggregateLifecycle.apply(new CategoryUpdatedEvent(
                command.getId(),
                command.getNom(),
                command.getDescription()
        ));
    }

    @EventSourcingHandler

    public void on(CategoryUpdatedEvent event){
        this.id = event.getId();
        this.description = event.getDescription();
        this.nom = event.getNom();

    }

    @CommandHandler
    public void handle(DeleteCategoryCommand command){
        if(command.getId() == null || command.getId().isBlank()) throw new EntryNotFoundException("Category Not Found.");
        // validations
        AggregateLifecycle.apply(new CategoryDeletedEvent(
                command.getId()));
    }

    @EventSourcingHandler
    public void on(CategoryDeletedEvent event){
        log.warn("Deleting Category: "+ event.getId());
    }

}
