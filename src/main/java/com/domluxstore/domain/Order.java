package com.domluxstore.domain;

import com.domluxstore.domain.enumeration.OrderState;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class Order {

    private String id;

    private Long total;

    private LocalDateTime dateOrder;

    private LocalDateTime deliveryDate;

    private Long quantity;

    private OrderState orderState;

    private Client client;

    private ShoppingCart shoppingCart;

    private List<ItemOrder> itemOrderList;



}
