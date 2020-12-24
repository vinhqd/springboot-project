package com.example.controller.auth;

import com.example.dto.UserDTO;
import com.example.service.IUserService;
import com.example.validation.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    private UserValidate userValidate;

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new UserDTO());
        return "auth/register";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute UserDTO userDTO, Model model) {
        if (!userValidate.fullNameValid(userDTO.getFullName()))
            return "redirect:/register?fullNameIncorrect";
        if (!userValidate.userNameNameValid(userDTO.getUserName()))
            return "redirect:/register?userNameIncorrect";
        if (!userValidate.passwordValid(userDTO)) {
            return "redirect:/register?confirmPasswordIncorrect";
        }
        if (userService.save(userDTO) == null) {
            return "redirect:/register?userExist";
        }
        return "redirect:/login";
    }

}
