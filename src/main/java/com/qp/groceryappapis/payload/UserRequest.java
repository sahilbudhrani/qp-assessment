package com.qp.groceryappapis.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotEmpty
    @Size(min = 3, message = "Name must be mininum of 3 characters")
    private String name;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 3, message = "password must be mininum of 3 characters")
    private String password;
    @NotNull(message = "Minimum 1 role is required")
    private List<Integer> roles;
}
