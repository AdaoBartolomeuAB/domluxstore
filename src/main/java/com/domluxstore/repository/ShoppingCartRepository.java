package com.domluxstore.repository;

import com.domluxstore.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart,String> {
}
