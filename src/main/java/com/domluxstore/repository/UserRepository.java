package com.domluxstore.repository;

import com.domluxstore.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

    User findByEmail(String email);

    User findByPhone(String phone);
}
