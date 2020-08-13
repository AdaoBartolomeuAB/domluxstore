package com.domluxstore.repository;

import com.domluxstore.domain.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard,String> {
}
