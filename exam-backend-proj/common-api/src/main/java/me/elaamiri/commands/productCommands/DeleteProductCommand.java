package me.elaamiri.commands.productCommands;

import me.elaamiri.commands.BaseCommand;
import me.elaamiri.events.BaseEvent;

public class DeleteProductCommand extends BaseCommand<String> {
    public DeleteProductCommand(String id) {
        super(id);
    }
}
