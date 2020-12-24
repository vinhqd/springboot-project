package com.example.controller.admin;

import com.example.dto.CategoryDTO;
import com.example.service.ICategoryService;
import com.example.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String categoryPage(Model model) {
        String data = JsonUtil.listToJson(categoryService.findAll());
        model.addAttribute("categories", data);
        return "/admin/category/category";
    }

    @GetMapping("/add")
    public String addCategoryPage(Model model, @RequestParam(value = "id", required = false) Long id) {
        CategoryDTO categoryDTO;
        if (id != null) {
            categoryDTO = categoryService.findOneById(id);
        } else categoryDTO = new CategoryDTO();
        model.addAttribute("category", categoryDTO);
        return "/admin/category/add";
    }

    @PostMapping ("/add")
    public String saveCategory(Model model, @ModelAttribute CategoryDTO categoryDTO) {
        CategoryDTO result = categoryService.save(categoryDTO);
        if (categoryDTO == null) {
            return "redirect:/admin/category?errorSystem";
        }
        return "redirect:/admin/category?success";
    }

    @GetMapping("/delete")
    public String deleteCategories(@RequestParam long [] ids){
        categoryService.delete(ids);
        return "redirect:/admin/category?success";
    }

}
