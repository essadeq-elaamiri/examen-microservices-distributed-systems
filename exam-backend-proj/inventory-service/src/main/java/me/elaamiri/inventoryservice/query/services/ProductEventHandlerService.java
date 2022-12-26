package me.elaamiri.inventoryservice.query.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.elaamiri.events.productEvents.ProductCreatedEvent;
import me.elaamiri.events.productEvents.ProductDeletedEvent;
import me.elaamiri.events.productEvents.ProductUpdatedEvent;
import me.elaamiri.exceptions.EntryNotFoundException;
import me.elaamiri.inventoryservice.query.entities.Category;
import me.elaamiri.inventoryservice.query.entities.Product;
import me.elaamiri.inventoryservice.query.repositories.CategoryRepository;
import me.elaamiri.inventoryservice.query.repositories.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ProductEventHandlerService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @EventHandler
    public void on(ProductCreatedEvent event){
        Product product = Product.builder()
                .id(event.getId())
                .nom(event.getNom())
                .categoryId(event.getCategoryId())
                .productStatus(event.getProductStatus())
                .prix(event.getPrix())
                .quentiteStocke(event.getQuentiteStocke())
                .build();
        // find Category
        Category category = categoryRepository.findById(event.getCategoryId()).orElseThrow(()-> new EntryNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductUpdatedEvent event){
        Product product = productRepository.findById(event.getId())
                .orElseThrow(()-> new EntryNotFoundException("Product Not Found"));
        product.setNom(event.getNom());
        product.setProductStatus(event.getProductStatus());
        product.setCategoryId(event.getCategoryId());
        product.setPrix(event.getPrix());
        product.setQuentiteStocke(event.getQuentiteStocke());

        Category category = categoryRepository.findById(event.getCategoryId()).orElseThrow(()-> new EntryNotFoundException("Category not found"));
        product.setCategory(category);
//        product.setId(event.getId());
        productRepository.save(product);

    }

    @EventHandler
    public void on(ProductDeletedEvent event){
        Product product = productRepository.findById(event.getId())
                .orElseThrow(()-> new EntryNotFoundException("Product Not Found"));
        productRepository.delete(product);
    }
}
