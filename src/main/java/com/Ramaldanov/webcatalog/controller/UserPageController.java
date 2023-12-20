package com.Ramaldanov.webcatalog.controller;

import com.Ramaldanov.webcatalog.entity.UsersFile;
import com.Ramaldanov.webcatalog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserPageController {
    private final FileService fileService;

    @Autowired
    public UserPageController(FileService fileService) {
        this.fileService = fileService;
    }
    @GetMapping("/user/{username}")
    public String showUserPage(@PathVariable("username") String username,
                               Model model) {
        List<UsersFile> usersFiles = fileService.loadAll();
        usersFiles.removeIf(file -> !file.getCreatorUsername().equals(username));
        model.addAttribute("filesAttributes", usersFiles);
        model.addAttribute("adminRole", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return "userpage";
    }
}
