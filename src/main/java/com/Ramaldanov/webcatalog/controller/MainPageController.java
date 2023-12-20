package com.Ramaldanov.webcatalog.controller;

import com.Ramaldanov.webcatalog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class MainPageController {
    private final FileService fileService;

    @Autowired
    public MainPageController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        model.addAttribute("filesAttributes", fileService.loadAll());
        model.addAttribute("adminRole", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return "main";
    }
}
