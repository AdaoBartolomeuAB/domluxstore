package com.domluxstore.converter.domain.cliente;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ClientePostDto {

    @NotBlank(message = "The filed name is required")
    private String name;

    @Email()
    private String email;

    @NotBlank(message = "The filed phone is required")
    private String phone;

    @NotBlank(message = "The filed password is required")
    private String password;
}
