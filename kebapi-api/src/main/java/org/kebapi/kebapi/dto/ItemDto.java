package org.kebapi.kebapi.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

  private String id;

  @NotNull(message = "The name field is required")
  private String name;

  @NotNull(message = "The description field is required")
  private String description;

  @Min(value = 0, message = "The price must be greater than or equal to 0")
  @NotNull(message = "The price field is required")
  private float price;

  private boolean vegetarian;
}
