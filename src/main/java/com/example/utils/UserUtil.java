package com.example.utils;

import com.example.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public static List<RoleEntity> setUserRole(RoleEntity roleEntity) {
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleEntity);
        return roles;
    }

}
