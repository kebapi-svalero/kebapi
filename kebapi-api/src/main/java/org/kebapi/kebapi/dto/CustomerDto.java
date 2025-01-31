package org.kebapi.kebapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

  private String id;

  @NotNull(message = "The name field is required")
  @Size(min = 2, message = "The name must have at least 2 characters")
  private String name;

  @NotNull(message = "The email field is required")
  @Email(message = "Invalid email format")
  private String email;

  @NotNull(message = "The phone field is required")
  @Pattern(regexp = "\\d{10,15}", message = "The phone number must be between 10 and 15 digits")
  private String phone;

  @NotNull(message = "The password field is required")
  @Size(min = 6, message = "The password must have at least 6 characters")
  private String password;
}
