package com.example.delicias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String showIndexPage() {
        return "index"; // Devuelve el nombre de la plantilla Thymeleaf (sin la extensión .html)
    }
}