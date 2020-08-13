package com.domluxstore.converter.domain.administrator;

import lombok.Data;

@Data
public class AdministratorPutDto {

    private String name;

    private String phone;

    private String email;

    private String password;
}
