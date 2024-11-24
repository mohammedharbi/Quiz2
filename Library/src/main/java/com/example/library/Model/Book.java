package com.example.library.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    @NotEmpty(message = "ID is empty")
    @Size(min = 2, message = "ID should be at least 2")
    private String ID;

    @NotEmpty(message = "name is empty")
    @Size(min = 4,message = "name should be at least 2 letters")
    private String name;

    @NotNull(message = "number of pages is empty")
    @Positive
    @Min(value = 2,message = "number of pages should be at least 1 page")
    private Integer number_of_pages;

    @NotNull(message = "price  is empty")
    @Positive
    private Integer price;

    @Pattern(regexp = "^(novel|academic)")
    private String category;

    private Boolean isAvailable;
}
