package com.mop.dictionaryApp.controller;

import com.mop.dictionaryApp.model.Users;
import com.mop.dictionaryApp.repository.GlossaryRepository;
import com.mop.dictionaryApp.service.UsersService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService userService;
    @Autowired
    private GlossaryRepository glossaryRepository;

    // Create a user
    @PostMapping
    public Users createUser(@RequestParam String username) {
        return userService.createUser(username);
    }
    @PutMapping("/{userId}/update-username")
    public ResponseEntity<Users> updateUsername( @RequestParam String newUsername,@PathVariable Integer userId) {
        Users updatedUser = userService.updateUsername(userId, newUsername);
        return ResponseEntity.ok(updatedUser);
    }
    
    @GetMapping("/all-ids-usernames")
    public List<Map<String, Object>> getAllUserIdsAndUsernames() {
        return userService.getAllUserIdsAndUsernames();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        String message = userService.deleteUserAndRelatedData(userId);
        return ResponseEntity.ok(message);
    }

}
