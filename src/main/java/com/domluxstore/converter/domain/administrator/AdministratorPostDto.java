package com.domluxstore.converter.domain.administrator;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AdministratorPostDto {

    @NotBlank(message = "The filed name is required")
    private String name;

    @NotBlank(message = "The filed phone is required")
    private String phone;

    @Email(message = "Insure tha is a email")
    private String email;

    @NotBlank(message = "The filed password is required")
    private String password;
}
