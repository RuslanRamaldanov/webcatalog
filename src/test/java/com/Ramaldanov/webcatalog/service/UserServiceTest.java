//package com.Ramaldanov.webcatalog.service;
//
//import com.Ramaldanov.webcatalog.entity.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class UserServiceTest {
//    @Autowired
//    private UserService userService;
//
//    @Test
//    void loadUserByUsername() {
//        User user = (User) userService.loadUserByUsername("asd");
//        assertEquals("ROLE_USER", user.getRole());
//    }
//
//    @Test
//    void saveUser() {
//        User user = new User();
//        user.setRole("ROLE_USER");
//        user.setPassword("qwerty");
//        user.setUsername("test_username");
//        userService.saveUser(user);
//        user = (User) userService.loadUserByUsername("test_username");
//        assertEquals("ROLE_USER", user.getRole());
//
//    }
//
//    @Test
//    void deleteUser() {
//        User user = (User) userService.loadUserByUsername("test_username");
//        userService.deleteUser(user.getId());
//        try {
//            userService.loadUserByUsername("test_username");
//        }
//        catch (UsernameNotFoundException exc) {
//            assertTrue(true);
//        }
//    }
//}