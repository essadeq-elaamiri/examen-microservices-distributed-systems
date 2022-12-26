package me.elaamiri.inventoryservice.command.controllers;

import lombok.AllArgsConstructor;
import me.elaamiri.commands.productCommands.CreateProductCommand;
import me.elaamiri.commands.productCommands.DeleteProductCommand;
import me.elaamiri.commands.productCommands.UpdateProductCommand;
import me.elaamiri.dtos.productDtos.CreateProductRequestDTO;
import me.elaamiri.dtos.productDtos.UpdateProductRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/commands/product")
public class ProductCommandController {
    private CommandGateway commandGateway;

    @PostMapping("/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO){
        CompletableFuture<String> response = commandGateway.send(new CreateProductCommand(
                UUID.randomUUID().toString(),
                createProductRequestDTO.getNom(),
                createProductRequestDTO.getPrix(),
                createProductRequestDTO.getQuentiteStocke(),
                createProductRequestDTO.getProductStatus(),
                createProductRequestDTO.getCategoryId()
        ));
        return  response;
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> updateProduct(@RequestBody UpdateProductRequestDTO updateProductRequestDTO, @PathVariable String id){
        CompletableFuture<String> response = commandGateway.send(new UpdateProductCommand(
                id, // entered ID
                updateProductRequestDTO.getNom(),
                updateProductRequestDTO.getPrix(),
                updateProductRequestDTO.getQuentiteStocke(),
                updateProductRequestDTO.getProductStatus(),
                updateProductRequestDTO.getCategoryId()
        ));
        return  response;
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> deleteProduct(@PathVariable String id){
        CompletableFuture<String> response = commandGateway.send(new DeleteProductCommand(
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
