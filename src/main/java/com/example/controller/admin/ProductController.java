package com.example.controller.admin;

import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public String productPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/admin/products/product";
    }

}
