package com.urbana.desafio.api.controllers;

import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.api.dtos.UserInsertDTO;
import com.urbana.desafio.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<UserInsertDTO> insert(@RequestBody UserInsertDTO dto){

        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserInsertDTO> update(@PathVariable Long id, @RequestBody UserInsertDTO dto) {

        dto = service.update(id, dto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
