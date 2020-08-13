package com.domluxstore.repository;

import com.domluxstore.domain.ItemOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderRepository extends CrudRepository<ItemOrder,String> {
}
