package com.example.validation;

import com.example.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserValidate {

    public boolean fullNameValid(String fullName) {
        if (fullName.length() > 100 || fullName == null || fullName.trim() == "")
            return false;
        return true;
    }

    public boolean userNameNameValid(String userName) {
        if (userName.length() > 26 || userName.length() < 5 || userName == null || userName.contains(" "))
            return false;
        return true;
    }

    public boolean passwordValid(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            return false;
        }
        return true;
    }

}
