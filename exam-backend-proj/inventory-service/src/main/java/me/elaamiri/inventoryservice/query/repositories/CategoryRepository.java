package me.elaamiri.inventoryservice.query.repositories;

import me.elaamiri.inventoryservice.query.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
