package me.elaamiri.commands;


import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<IDType> {
    @TargetAggregateIdentifier
    @Getter
    private IDType id;

    public BaseCommand(IDType id) {
        this.id = id;
    }
}
