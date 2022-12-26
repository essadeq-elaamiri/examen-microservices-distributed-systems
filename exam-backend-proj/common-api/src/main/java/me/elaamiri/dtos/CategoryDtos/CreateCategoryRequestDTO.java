package me.elaamiri.dtos.CategoryDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateCategoryRequestDTO {

    private String nom;


    private String description;
}
