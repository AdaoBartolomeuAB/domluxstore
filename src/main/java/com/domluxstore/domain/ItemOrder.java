package com.domluxstore.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class ItemOrder {

    @Id
    private String id;

    private Long quantity;

    private Order order;

    private List<Product> product;
}
