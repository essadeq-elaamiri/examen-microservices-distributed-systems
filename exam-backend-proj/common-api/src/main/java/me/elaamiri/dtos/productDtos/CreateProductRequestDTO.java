package me.elaamiri.dtos.productDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.elaamiri.enumerations.ProductStatus;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreateProductRequestDTO {

    private String nom;

    private double prix;

    private int quentiteStocke;

    private ProductStatus productStatus;


    private String categoryId;

}
