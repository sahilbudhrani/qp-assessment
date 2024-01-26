package com.qp.groceryappapis.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotEmpty
    @Size(min = 3, message = "Name must be mininum of 3 characters")
    private String name;
    @Email(message = "Invalid Email")
    private String email;
    @NotEmpty(message = "Item name cannot be empty")
    private String address;
    @AssertTrue(message = "Bill should be paid")
    private boolean billPaid;
    @NotEmpty(message = "Item name cannot be empty")
    private Map<String, Integer> items;
}
