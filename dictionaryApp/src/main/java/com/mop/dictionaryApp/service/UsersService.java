package com.mop.dictionaryApp.service;

import com.mop.dictionaryApp.exception.ApiException;
import com.mop.dictionaryApp.exception.ErrorCode;
import com.mop.dictionaryApp.model.Glossary;
import com.mop.dictionaryApp.model.Users;
import com.mop.dictionaryApp.repository.GlossaryRepository;
import com.mop.dictionaryApp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private GlossaryRepository glossaryRepository;

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

    public Users updateUsername(Integer userId, String newUsername) {
        if (newUsername == null || newUsername.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        // Fetch the user by ID
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        // Update the username
        user.setUsername(newUsername);

        // Save and return the updated user
        return userRepository.save(user);
    }

    // TODO: Get a list of users
    public List<Map<String, Object>> getAllUserIdsAndUsernames() {
        List<Users> users = userRepository.findAll();

        // Use explicit map creation to avoid type inference issues
        return users.stream()
                .map(user -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", user.getId());
                    map.put("username", user.getUsername());
                    return map;
                })
                .collect(Collectors.toList());
    }

    // TODO: Delete a given User id
    public String deleteUserAndRelatedData(Integer userId) {
        // Fetch the user by ID
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        // Delete all glossaries related to the user
        Optional<Glossary> OptionalGlossary = glossaryRepository.findByUserId(userId);
        OptionalGlossary.ifPresent(glossary -> {
            glossaryRepository.delete(glossary);});
        // Finally, delete the user
        userRepository.delete(user);

        return "User deleted successfully";
    }
}
