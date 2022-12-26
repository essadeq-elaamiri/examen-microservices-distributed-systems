package me.elaamiri.commands.categoryCommands;

import lombok.Getter;
import me.elaamiri.commands.BaseCommand;

public class CreateCategoryCommand extends BaseCommand<String> {

    @Getter
    private String nom;

    @Getter
    private String description;

    public CreateCategoryCommand(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
