package com.domluxstore.repository;

import com.domluxstore.domain.Administrator;
import com.domluxstore.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client,String>{

    Page<Client> findAllByUser_PhoneContaining(String phone,Pageable pageable);

    Page<Client> findAllByUser_EmailContaining(String email,Pageable pageable);

    Page<Client> findAllByUser_NameContaining(String name,Pageable pageable);

    Page<Client> findAll(Pageable pageable);
}
