package com.domluxstore.domain;

import com.domluxstore.domain.enumeration.AdministratorType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class Administrator {

    @Id
    private String id;

    private AdministratorType administratorType;

    @ToString.Exclude
    private User user;
}
