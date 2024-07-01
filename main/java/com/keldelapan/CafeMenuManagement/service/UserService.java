package com.keldelapan.CafeMenuManagement.service;

import com.keldelapan.CafeMenuManagement.entity.UserCafe;
import com.keldelapan.CafeMenuManagement.exceptions.UserExistException;
import com.keldelapan.CafeMenuManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<UserCafe> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(UserCafe user) throws UserExistException {

        if (userRepository.existsById(user.getUsername())){
            throw new UserExistException("User already exist: "+ user.getUsername());
        }
        userRepository.save(user);

    }

    public UserCafe getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

}
