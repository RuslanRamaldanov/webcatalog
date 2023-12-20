package com.Ramaldanov.webcatalog.controller;

import com.Ramaldanov.webcatalog.entity.UsersFile;
import com.Ramaldanov.webcatalog.service.FileService;
import com.Ramaldanov.webcatalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class UpdateArticleController {
    private final FileService fileService;
    private final UserService userService;


    @Autowired
    public UpdateArticleController(FileService fileService,
                                   UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping("/update/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        UsersFile file = fileService.getFileById(id);
        String creatorUsername = userService.getCurrentUsername();
        if((file != null)) {
            if(creatorUsername.equals(file.getCreatorUsername()) || userService.hasAdminRole())
                model.addAttribute("file", file);
            else
                return "error-page";
        }
        return "update";
    }

    @PostMapping("/update")
    public String updateFileInformation(@RequestParam("id") Long id,
                                        @RequestParam("newYear") Integer newYear,
                                        @RequestParam("newAuthor") String newAuthor,
                                        @RequestParam("newKeyWords") String newKeyWords,
                                        @RequestParam("newArticleName") String newArticleName) {
        fileService.updateData(id,
                newAuthor,
                newYear,
                new ArrayList<String>(Arrays.asList(newKeyWords.split(";"))),
                newArticleName);

        return "redirect:/main";
    }
}
