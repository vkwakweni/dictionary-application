package com.mop.dictionaryApp.service;

import com.mop.dictionaryApp.model.Users;
import com.mop.dictionaryApp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepository;

    // Create a user
    public Users createUser(String username) {
        Users user = new Users();
        user.setUsername(username);
        userRepository.save(user);
        return user;
    }

    // Find a user
    public Optional<Users> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    // TODO: Get a list of users

    // TODO: Update a given User's id

    // TODO: Delete a given User id
}
