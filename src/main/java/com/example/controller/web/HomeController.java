package com.example.controller.web;

import com.example.service.IBrandService;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IBrandService brandService;

    @GetMapping("/")
    public String homePage(Model model) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(0, 4, sort);
        model.addAttribute("sliderList", productService.findAll(pageable));
        model.addAttribute("brands", brandService.findAll());
        return "web/home";
    }

}
