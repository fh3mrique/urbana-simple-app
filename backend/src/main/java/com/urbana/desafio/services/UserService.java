package com.urbana.desafio.services;

import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.domain.entities.User;
import com.urbana.desafio.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();

        return users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }
}
