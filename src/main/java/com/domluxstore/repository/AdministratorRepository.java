package com.domluxstore.repository;

import com.domluxstore.domain.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator,String>{

    Page<Administrator> findAllByUser_PhoneContaining(String phone,Pageable pageable);

    Page<Administrator> findAllByUser_EmailContaining(String email,Pageable pageable);

    Page<Administrator> findAllByUser_NameContaining(String name,Pageable pageable);

    Page<Administrator> findAll(Pageable pageable);
}
