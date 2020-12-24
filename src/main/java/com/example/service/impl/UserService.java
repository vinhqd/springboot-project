package com.example.service.impl;

import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.constants.SystemConstant;
import com.example.converter.ModelConverter;
import com.example.dto.UserDTO;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.IUserService;
import com.example.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelConverter modelConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(userDTO.getUserName(), SystemConstant.ACTIVE_STATUS);
        if (userEntity != null) return null;
        RoleEntity roleEntity = roleRepository.findOneByCode(SystemConstant.USER_ROLE);
        userDTO.setStatus(SystemConstant.ACTIVE_STATUS);
        userEntity = modelConverter.toEntity(userDTO, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(UserUtil.setUserRole(roleEntity));
        userRepository.save(userEntity);
        return modelConverter.toDTO(
                userRepository.findOneByUserNameAndStatus(userDTO.getUserName(), SystemConstant.ACTIVE_STATUS),
                UserDTO.class);
    }


}
