package org.kebapi.kebapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInDto {
  private long id;
  private String name;
  private String email;
  private String phone;
  private String password;
}