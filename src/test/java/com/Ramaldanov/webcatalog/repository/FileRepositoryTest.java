//package com.Ramaldanov.webcatalog.repository;
//
//import com.Ramaldanov.webcatalog.entity.UsersFile;
//import com.Ramaldanov.webcatalog.exceptions.FileNameNotFoundException;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//
//class FileRepositoryTest {
//
//    @Autowired
//    private FileRepository fileRepository;
//
//    @Test
//    public void findByFileNameTest() throws FileNameNotFoundException {
//        UsersFile file = fileRepository.findByFileName("TEST");
//        assertEquals(7, file.getId());
//        assertEquals("TEST", file.getFileName());
//        assertEquals(1000, file.getYear());
//        assertEquals("NEW AUTHOR", file.getAuthor());
//    }
//}