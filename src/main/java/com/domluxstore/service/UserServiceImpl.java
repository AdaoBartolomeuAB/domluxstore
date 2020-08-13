package com.domluxstore.service;

import com.domluxstore.converter.domain.user.UserPutDto;
import com.domluxstore.domain.User;
import com.domluxstore.repository.UserRepository;
import com.domluxstore.service.map.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user,String role) {
        user.addRole(role);
        user.setRegistrationDate(LocalDateTime.now());
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }


    @Override
    public User update(String id,UserPutDto userPutDto){

        User user = findById(id);

        if (userPutDto.getEmail()!=null){
            user.setEmail(userPutDto.getEmail());
        }

        if (userPutDto.getName()!=null){
            user.setName(userPutDto.getName());
        }

        if (userPutDto.getPhone()!=null){
            user.setPhone(userPutDto.getPhone());
        }

        if (userPutDto.getPassword()!=null){
            user.setPassword(userPutDto.getPhone());
        }
        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User finByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public User finByPhone(String phone) { return userRepository.findByPhone(phone);
    }
}
