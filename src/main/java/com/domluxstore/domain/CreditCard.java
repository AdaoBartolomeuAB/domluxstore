package com.domluxstore.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class CreditCard {

    private String id;

    private String number;

    private Long totalValuer = 100000000L;

    private Client client;
}
