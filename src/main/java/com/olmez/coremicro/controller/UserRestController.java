package com.olmez.coremicro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.coremicro.model.PasswordWrapper;
import com.olmez.coremicro.model.User;
import com.olmez.coremicro.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200") // This allows to talk to port:5000 (ui-backend)
public class UserRestController {

    @Autowired
    private UserService userService;

    // GET all
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    // CREATE
    @PostMapping()
    public ResponseEntity<Boolean> addUser(@RequestBody User user) {
        boolean res = userService.addUser(user);
        return (res) ? new ResponseEntity<>(HttpStatus.CREATED) : ResponseEntity.badRequest().body(res);
    }

    // GET By Id using @PathVariable
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByIdPath(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok(updated);
    }

    // UPDATE
    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user == null || user.getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        User updated = userService.updateUser(user.getId(), user);
        return ResponseEntity.ok(updated);
    }

    // DELETE using @PathVariable
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long id) {
        boolean res = userService.deleteUser(id);
        return ResponseEntity.ok(res);
    }

    // GET By username
    @GetMapping("/username")
    public ResponseEntity<User> getUserByUsername(@RequestParam("username") String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    // UPDATE Password
    @PutMapping("/pass")
    public ResponseEntity<Boolean> updatePassword(@RequestBody PasswordWrapper passWrapper) {
        boolean res = userService.updateUserPassword(passWrapper);
        return ResponseEntity.ok(res);
    }

}
