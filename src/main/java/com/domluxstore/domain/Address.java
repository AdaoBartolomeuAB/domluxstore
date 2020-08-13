package com.domluxstore.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class Address {

    @Id
    private String id;

    private String latitude;

    private String longitude;

    private User user;
}
