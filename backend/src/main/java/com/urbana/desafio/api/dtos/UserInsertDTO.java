package com.urbana.desafio.api.dtos;

import com.urbana.desafio.domain.entities.BoardingPass;
import com.urbana.desafio.domain.entities.User;

import java.util.Set;

public record UserInsertDTO(Long id, String name, String email, String password, Set<BoardingPass> boardingPassTypes) {

    public UserInsertDTO(User entity){
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getPassword(), entity.getBoardingPassTypes());
    }

}
