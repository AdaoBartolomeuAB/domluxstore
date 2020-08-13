package com.domluxstore.service.map;

import com.domluxstore.converter.domain.administrator.AdministratorPutDto;
import com.domluxstore.domain.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AdministratorService {

    Administrator save(Administrator administrator);

    Administrator update(String id,AdministratorPutDto administratorPutDto);

    Administrator findById(String id);

    Page<Administrator> listAll(Pageable pageable);

    Page<Administrator> findAllByPhone(String phone,Pageable pageable);

    Page<Administrator> findAllByEmail(String email,Pageable pageable);

    Page<Administrator> findAllByName(String name,Pageable pageable);

    Page<Administrator> filterSerach(Map<String,String> filtros, Pageable pageable);
}
