package com.example.controller.admin;

import com.example.dto.BrandDTO;
import com.example.service.IBrandService;
import com.example.utils.FileUtil;
import com.example.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/admin/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @Autowired
    private FileUtil fileUtil;

    @GetMapping
    public String brandPage(Model model) {
        String data = JsonUtil.listToJson(brandService.findAll());
        model.addAttribute("brands", data);
        return "admin/brand/brand";
    }

    @GetMapping("/add")
    public String addPage(Model model, @RequestParam(name = "id", required = false) Long id) {
        if (id != null) model.addAttribute("brand", brandService.findOneById(id));
        else model.addAttribute("brand", new BrandDTO());
        return "/admin/brand/add";
    }

    @PostMapping("/add")
    public String postBrand(Model model, BrandDTO brandDTO, HttpServletRequest request) {
        if (brandDTO.getBase64() != null && !brandDTO.getBase64().equals("")) {
            byte[] bytes = Base64.getDecoder().decode(brandDTO.getBase64().split(",")[1]);
            try {
                brandDTO.setImage(fileUtil.uploadFile(bytes, brandDTO.getFileName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BrandDTO dto = brandService.save(brandDTO);
        if (dto == null) {
            return "redirect:/admin/brand/add?errorSystem";
        }
        return "redirect:/admin/brand?success";
    }

    @GetMapping("/delete")
    public String deleteBrands(long[] ids) {
        brandService.delete(ids);
        return "redirect:/admin/brand";
    }

}
