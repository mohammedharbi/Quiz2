package com.example.library.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "ID is empty")
    @Size(min = 2, message = "ID should be at least 2")
    private String ID;

    @NotEmpty(message = "name is empty")
    @Size(min = 4,message = "name should be at least 2 letters")
    private String name;

    @NotNull(message = "age is empty")
    @Min(value = 12, message = "age must be above or equal 12")
    private int age;

    @NotNull(message = "balance is empty")
    @Positive
    private int balance;

    @Pattern(regexp = "^(customer|libraian)")
    private String role;

}
