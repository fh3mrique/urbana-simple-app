package com.urbana.desafio.api.controllers;

import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserResources {

    private UserService service;

    public UserResources(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> users = service.findAll();
        return  ResponseEntity.ok().body(users);
    }
}
