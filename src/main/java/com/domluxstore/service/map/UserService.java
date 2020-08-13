package com.domluxstore.service.map;


import com.domluxstore.converter.domain.user.UserPutDto;
import com.domluxstore.domain.User;

public interface UserService {

    User save(User user,String role);

    User update(String id, UserPutDto userPutDto);

    User findById(String id);

    User finByEmail(String email);

    User finByPhone(String phone);
}
