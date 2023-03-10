package me.elaamiri.inventoryservice.query.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Category {

    @Id
    private String id;

    private String nom;

    private String description;

    @OneToMany(mappedBy = "category")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Product> products;
}
