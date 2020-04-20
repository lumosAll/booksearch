package com.example.booksearch.service.impl;

import com.example.booksearch.entity.User;
import com.example.booksearch.exceptions.UserException;
import com.example.booksearch.repository.UserRepository;
import com.example.booksearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 유저 관리
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean create(String id, String name, String password) {
        if(userRepository.findById(id).isPresent()) {
            throw new UserException("ID_EXIST");
        }
        User user = new User(id, name,passwordEncoder.encode(password));
        userRepository.save(user);

        return true;
    }

    @Override
    public User get(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(String id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean login(String id, String password) {
        User user = userRepository.findById(id).orElse(null);
        if(user==null) {
            throw new UserException("UNAUTHORIZED");
        }

        if(passwordEncoder.matches(password,user.getPassword())) {
            return true;
        }

        throw new UserException("UNAUTHORIZED");
    }
}
