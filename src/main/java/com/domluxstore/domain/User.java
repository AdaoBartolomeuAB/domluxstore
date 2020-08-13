package com.domluxstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class User {

    @Id
    private String id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String password;

    private LocalDateTime registrationDate = LocalDateTime.now();

    @ToString.Exclude
    private Administrator administrator;

    @ToString.Exclude
    private List<Address> addresses;

    @ToString.Exclude
    private Client client;

    private Set<String> role =new HashSet<>();

    public void addRole(String papel){
        role.add(papel);
    }
}
