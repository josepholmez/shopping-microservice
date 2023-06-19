package com.olmez.coremicro.services;

import java.util.List;

import com.olmez.coremicro.model.PasswordWrapper;
import com.olmez.coremicro.model.User;

public interface UserService {

    List<User> getUsers();

    boolean addUser(User user);

    User getUserById(Long id);

    boolean deleteUser(Long id);

    User updateUser(Long existingUserId, User givenUser);

    User getUserByUsername(String username);

    User updateUser(User givenUser);

    User getCurrentUser();

    boolean updateUserPassword(PasswordWrapper passWrapper);
}
