package com.example.blogapi.services.impl;

import com.example.blogapi.entities.Role;
import com.example.blogapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl {
    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Long id){
        return this.roleRepository.findById(id).orElse(null);
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

}
