package me.elaamiri.inventoryservice.command.controllers;

import lombok.AllArgsConstructor;
import me.elaamiri.commands.categoryCommands.CreateCategoryCommand;
import me.elaamiri.commands.categoryCommands.DeleteCategoryCommand;
import me.elaamiri.commands.categoryCommands.UpdateCategoryCommand;
import me.elaamiri.dtos.CategoryDtos.CreateCategoryRequestDTO;
import me.elaamiri.dtos.CategoryDtos.UpdateCategoryRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/commands/category")
public class CategoryCommandController {
    private CommandGateway commandGateway;

    @PostMapping("/create")
    public CompletableFuture<String> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
        CompletableFuture<String> response = commandGateway.send(new CreateCategoryCommand(
                UUID.randomUUID().toString(),
                createCategoryRequestDTO.getNom(),
                createCategoryRequestDTO.getDescription()
        ));
        return  response;
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> updateCategory(@RequestBody UpdateCategoryRequestDTO updateCategoryRequestDTO, @PathVariable String id){
        CompletableFuture<String> response = commandGateway.send(new UpdateCategoryCommand(
                id, // entered ID
                updateCategoryRequestDTO.getNom(),
                updateCategoryRequestDTO.getDescription()));
        return  response;
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> deleteCategory(@PathVariable String id){
        CompletableFuture<String> response = commandGateway.send(new DeleteCategoryCommand(
                id// entered ID
        ));
        return  response;
    }


    //@ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR
        );

        return responseEntity;
    }
}
