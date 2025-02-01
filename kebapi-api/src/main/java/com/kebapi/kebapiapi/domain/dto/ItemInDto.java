package com.kebapi.kebapiapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemInDto {
    private long id;
    private String name;
    private String description;
    private Float price;
    private Boolean isVegetarian;
}