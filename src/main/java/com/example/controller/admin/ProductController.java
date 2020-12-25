package com.example.controller.admin;

import com.example.dto.AbstractDTO;
import com.example.dto.ProductDTO;
import com.example.service.IProductService;
import com.example.utils.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public String productPage(Model model, @RequestParam(required = false) Map<String, String> params) {
        ProductDTO result = (ProductDTO) PageableUtil.pageable(new ProductDTO(), params, productService.count());
        Pageable pageable = PageRequest.of(result.getCurrentPage() - 1, result.getLimit());
        result.setResults(productService.findAll(pageable));
        model.addAttribute("model", result);
        return "/admin/products/product";
    }

    @GetMapping("/add")
    public String addProductPage(@RequestParam(required = false) Integer id, Model model) {

        return "/admin/products/add";
    }

}
