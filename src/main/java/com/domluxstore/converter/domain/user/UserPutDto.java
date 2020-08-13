package com.domluxstore.converter.domain.user;

import lombok.Data;

@Data
public class UserPutDto {

    private String name;

    private String email;

    private String phone;

    private String password;

}
