//package com.Ramaldanov.webcatalog.service;
//
//import com.Ramaldanov.webcatalog.entity.UsersFile;
//import com.Ramaldanov.webcatalog.exceptions.FileNameNotFoundException;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class FileServiceTest {
//    @Autowired
//    private FileService fileService;
//
//    @Test
//    void loadFileByFileName() throws FileNameNotFoundException {
//        UsersFile file = fileService.loadFileByFileName("Kotlin_lab4-2.pdf");
//        assertEquals("http://localhost:8080/files/Kotlin_lab4-2.pdf", file.getURI());
//        assertEquals("administrator", file.getAuthor());
//        assertEquals("admin", file.getCreatorUsername());
//    }
//
//    @Test
//    void saveFile() throws FileNameNotFoundException {
//        UsersFile file = fileService.loadFileByFileName("Kotlin_lab4-2.pdf");
//        assertFalse(fileService.saveFile(file));
//        file.setFileName("TEST");
//        assertTrue(fileService.saveFile(file));
//        assertEquals("administrator", file.getAuthor());
//        assertEquals("admin", file.getCreatorUsername());
//    }
//
//    @Test
//    void deleteFile() {
//        assertFalse(fileService.deleteFile(100L));
//    }
//
//    @Test
//    void loadAll() {
//        List<UsersFile> files = fileService.loadAll();
//        assertEquals(1, files.size());
//    }
//
//    @Test
//    void getFileById() {
//        UsersFile file = fileService.getFileById(7L);
//        assertEquals("http://localhost:8080/files/Kotlin_lab4-2.pdf", file.getURI());
//        assertEquals("administrator", file.getAuthor());
//        assertEquals("admin", file.getCreatorUsername());
//    }
//
//    @Test
//    void updateData() {
//        UsersFile file = null;
//        List<String> newKW = new ArrayList<>();
//        newKW.add("KEYWORD");
//
//        fileService.updateData(7L, "NEW AUTHOR",1000, newKW);
//
//        file = fileService.getFileById(7L);
//
//        assertEquals("NEW AUTHOR", file.getAuthor());
//        assertEquals(1000, file.getYear());
//    }
//
//    @Test
//    void foundFiles() {
//        List<String> newKW = new ArrayList<>();
//        newKW.add("KEYWORD");
//        List<UsersFile> files = fileService.foundFiles("NEW AUTHOR", 1000, newKW);
//        assertEquals(1, files.size());
//    }
//}