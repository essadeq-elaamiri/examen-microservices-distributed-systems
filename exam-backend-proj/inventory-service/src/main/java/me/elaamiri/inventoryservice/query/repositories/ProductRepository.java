package me.elaamiri.inventoryservice.query.repositories;

import me.elaamiri.inventoryservice.query.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
