package com.qp.groceryappapis.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Integer id;
    @NotEmpty(message = "Item name cannot be empty")
    private String name;
    @NotNull
    @Min(value = 1, message = "Item price should be greater than 0")
    private Integer price;
    @NotNull
    @Min(value = 1, message = "Item inventory should be greater than 0")
    private Integer availableInventory;
}
