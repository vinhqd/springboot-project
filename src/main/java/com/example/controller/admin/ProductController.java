package com.example.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @GetMapping
    public String productPage(Model model) {

        return "/admin/products/product";
    }

}
