package com.urbana.desafio.api.dtos;

import com.urbana.desafio.domain.entities.User;
public record UserInsertUpdateDTO(Long id, String name, String email, String password) {
    public UserInsertUpdateDTO(User entity){
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getPassword());
    }
}
