package com.example.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

    @GetMapping("/admin")
    public String homePage() {
        return "admin/home";
    }

}
