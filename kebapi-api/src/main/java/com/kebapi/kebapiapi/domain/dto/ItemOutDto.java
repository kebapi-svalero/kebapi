package com.kebapi.kebapiapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemOutDto {
    private long id;
    private String name;
    private String description;
    private Float price;
    private Boolean isVegetarian;
}