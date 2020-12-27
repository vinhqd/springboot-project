package com.example.controller.admin;

import com.example.dto.ProductDTO;
import com.example.service.IBrandService;
import com.example.service.ICategoryService;
import com.example.service.IProductService;
import com.example.utils.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String productPage(Model model, @RequestParam(required = false) Map<String, String> params) {
        ProductDTO result = (ProductDTO) PageableUtil.pageable(new ProductDTO(), params, productService.count());
        Pageable pageable = PageRequest.of(result.getCurrentPage() - 1, result.getLimit());
        if (params.get("q") == null || params.get("q").equals("")) {
            result.setResults(productService.findAll(pageable));
        } else {
            result.setResults(productService.findByName(params.get("q").toLowerCase()));
        }
        model.addAttribute("model", result);
        return "/admin/products/product";
    }

    @GetMapping("/add")
    public String addProductPage(@RequestParam(required = false) Long id, Model model) {
        ProductDTO product = new ProductDTO();
        if (id != null) product = productService.findOneById(id);
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", product);
        return "/admin/products/add";
    }

    @PostMapping("/add")
    public String addProduct(ProductDTO productDTO) {
        ProductDTO dto = productService.save(productDTO);
        if (dto == null) return "redirect:/admin/product/add?errorSystem";
        return "redirect:/admin/product/detail?success&id=" + dto.getId();
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam long[] ids) {
        productService.delete(ids);
        return "redirect:/admin/product?success";
    }

    @GetMapping("/detail")
    public String detailPage(Model model, @RequestParam(required = false) long id, @RequestParam(required = false) String success){
        ProductDTO product = productService.findOneById(id);
        if (success != null) {
            model.addAttribute("message", "Thành công");
        }
        model.addAttribute("product", product);
        return "admin/products/detail";
    }

}
