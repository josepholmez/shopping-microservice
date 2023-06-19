package com.olmez.coremicro.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olmez.coremicro.model.User;
import com.olmez.coremicro.repositories.UserRepository;
import com.olmez.coremicro.services.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository userRepository;

    private User user;

    @Test
    void testGetUsers() {
        user = new User("First", "Last", "uname", "email");
        when(userRepository.findAll()).thenReturn(List.of(user));

        var users = service.getUsers();
        assertThat(users).isNotEmpty();
        assertThat(users.get(0)).isEqualTo(user);
    }

}
