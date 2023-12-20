//package com.Ramaldanov.webcatalog.repository;
//
//import com.Ramaldanov.webcatalog.entity.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class UserRepositoryTest {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void findUserByUsernameTest() {
//        User user = userRepository.findByUsername("asd");
//        assertEquals("ROLE_USER", user.getRole());
//        assertEquals("asd", user.getUsername());
//    }
//}