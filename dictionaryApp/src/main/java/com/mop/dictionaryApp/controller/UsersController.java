package com.mop.dictionaryApp.controller;

import com.mop.dictionaryApp.model.Users;
import com.mop.dictionaryApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService userService;

    // Create a user
    @PostMapping
    public Users createUser(@RequestParam String username) {
        return userService.createUser(username);
    }
    
    // TODO: Get a list of users

    // TODO: Update a given User's id

    // TODO: Delete a given User id
}
