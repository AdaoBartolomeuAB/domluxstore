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
public class ShoppingCart {

    @Id
    private String id;

    private Long quantity;

    private Client client;

    private Product product;
}
