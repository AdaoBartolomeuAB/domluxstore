package com.domluxstore.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class Product {

    private String id;

    private String photoUrl;

    private String name;

    private String category;

    private String brand;

    private String description;

    private Long quantity;

    private Long weight;

    private Long height;

    private List<ItemOrder> itemOrders;
}
