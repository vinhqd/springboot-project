package com.example.controller.web;

import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    private IProductService productService;

    @GetMapping("/")
    public String homePage(Model model) {
//        Sort sort = Sort.by(Sort.Direction.ASC, "")
//        model.addAttribute("sliderList", );
        return "web/home";
    }


}
