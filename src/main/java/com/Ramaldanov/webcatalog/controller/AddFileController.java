package com.Ramaldanov.webcatalog.controller;

import com.Ramaldanov.webcatalog.entity.UsersFile;
import com.Ramaldanov.webcatalog.exceptions.FileNameNotFoundException;
import com.Ramaldanov.webcatalog.exceptions.StorageFileNotFoundException;
import com.Ramaldanov.webcatalog.service.FileService;
import com.Ramaldanov.webcatalog.service.UserService;
import com.Ramaldanov.webcatalog.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;


@Controller
public class AddFileController {
    private final StorageService storageService;
    private final FileService fileService;
    private final UserService userService;

    @Autowired
    public AddFileController(StorageService storageService,
                             FileService fileService,
                             UserService userService) {
        this.storageService = storageService;
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping("/addfile")
    public String showAddFilePage() {
        return "addfile";
    }

    @PostMapping("/addfile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("author") String author,
                                   @RequestParam("keyWords") String keyWords,
                                   @RequestParam("year") Integer year,
                                   @RequestParam("articleName") String articleName) throws FileNameNotFoundException {

        UsersFile usersFile = new UsersFile();

        usersFile.setFileName(file.getOriginalFilename());
        usersFile.setAuthor(author);
        usersFile.setYear(year);
        usersFile.setCreatorUsername(userService.getCurrentUsername());
        usersFile.setKeyWords(new ArrayList<String>(Arrays.asList(keyWords.split(";"))));
        usersFile.setArticleName(articleName);
        usersFile.setPathToFile(storageService.load(file.getOriginalFilename()).toString());

        if (!fileService.isExist(usersFile)) {
            storageService.store(file);
            Path path = storageService.load(file.getOriginalFilename());
            String finalURI = MvcUriComponentsBuilder
                    .fromMethodName(AddFileController.class, "serveFile",
                            path
                                    .getFileName()
                                    .toString())
                    .build()
                    .toUri()
                    .toString();
            usersFile.setURI(finalURI);
            fileService.saveFile(usersFile);
        } else
            System.out.println("FILE ALREADY EXIST");

        return "redirect:/main";
    }

    @PostMapping("/addfile/delete")
    public String deleteFile(@RequestParam("idToDelete") Long id,
                             @RequestParam("pathToDelete") String path) {
        if (fileService.deleteFile(id)) {
            storageService.deleteByPath(path);
        }
        return "redirect:/main";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        if (file == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.internalServerError().build();
    }
}
