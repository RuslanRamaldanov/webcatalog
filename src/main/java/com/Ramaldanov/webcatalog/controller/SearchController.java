package com.Ramaldanov.webcatalog.controller;

import com.Ramaldanov.webcatalog.entity.UsersFile;
import com.Ramaldanov.webcatalog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SearchController {
    private final FileService fileService;

    @Autowired
    public SearchController(FileService fileService) {
        this.fileService = fileService;
    }
    @GetMapping("/search")
    public String showSearchPage(@RequestParam(value = "author", required = false) String author,
                              @RequestParam(value = "year", required = false) Integer year,
                              @RequestParam(value = "keyWords", required = false) String keyWords,
                              @RequestParam(value = "articleName", required = false) String articleName,
                              Model model) {
        List<UsersFile> foundedFiles = new ArrayList<>();
        try {
            foundedFiles = fileService.foundFiles(author,
                    year, Arrays.asList(keyWords.split(";")), articleName);
        } catch (NullPointerException exc) {
            System.out.println(exc.getMessage());
        }
        model.addAttribute("foundedFiles", foundedFiles);
        return "search";
    }
}
