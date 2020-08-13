package com.domluxstore.service;

import com.domluxstore.converter.domain.user.UserPutDto;
import com.domluxstore.domain.User;
import com.domluxstore.exception.ConflictException;
import com.domluxstore.repository.UserRepository;
import com.domluxstore.service.map.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user,String role) {

        saveVerificationData(user);

        user.addRole(role);
        user.setRegistrationDate(LocalDateTime.now());
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User update(String id,UserPutDto userPutDto){

        User user = findById(id);

        updateVerificationData(user,userPutDto);

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
    public void delete(User user) {
        userRepository.delete(user);
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

    private void updateVerificationData(User user, UserPutDto userPutDto){

        User user1 = finByEmail(userPutDto.getEmail());

        Map<String, String> erros = new HashMap<>();

        if (user1!=null && !user.getId().equals(user1.getId())){
            erros.put("email","The email is already being used");
        }

        User user2 = finByPhone(userPutDto.getPhone());

        if (user2!=null && !user.getId().equals(user2.getId())){
            erros.put("phone","The phone number is already being used");
        }

        if (!erros.isEmpty()){
            throw new ConflictException("Information conflits",erros);
        }
    }

    private void saveVerificationData(User user){

        Map<String, String> erros = new HashMap<>();

        User user1 = finByEmail(user.getEmail());

        if (user1!=null){
            erros.put("email","The email is already being used");
        }

        User user2 = finByPhone(user.getPhone());

        if (user2!=null){
            erros.put("phone","The phone number is already being used");
        }

        if (!erros.isEmpty()){

            throw new ConflictException("Information conflits",erros);
        }
    }
}
