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
public class Client {

    @Id
    private String id;

    private ShoppingCart shoppingCart;

    private User user;

    private List<CreditCard> creditCard;

    private List<Order> orders;
}
