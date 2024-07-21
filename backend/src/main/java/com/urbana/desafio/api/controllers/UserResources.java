package com.urbana.desafio.api.controllers;

import com.urbana.desafio.api.dtos.BoardingPassDTO;
import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.api.dtos.UserInsertUpdateDTO;
import com.urbana.desafio.services.BoardingPassService;
import com.urbana.desafio.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*")
public class UserResources {

    private UserService service;

    private BoardingPassService boardingPassService;

    public UserResources(UserService service, BoardingPassService boardingPassService) {
        this.service = service;
        this.boardingPassService = boardingPassService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> users = service.findAll();
        return  ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<UserInsertUpdateDTO> insert(@RequestBody UserInsertUpdateDTO dto){

        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserInsertUpdateDTO> update(@PathVariable Long id, @RequestBody UserInsertUpdateDTO dto) {

        dto = service.update(id, dto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    //ESPECIAIS
    @PutMapping("/{userId}/{boardingPassId}/activate")
    public ResponseEntity<Void> activateBoardingPass(@PathVariable Long userId, @PathVariable Long boardingPassId) {
        boardingPassService.activate(userId, boardingPassId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/{boardingPassId}/deactivate")
    public ResponseEntity<Void> deactivateBoardingPass(@PathVariable Long userId, @PathVariable Long boardingPassId) {
        boardingPassService.deactivate(userId, boardingPassId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/boarding-passes")
    public ResponseEntity<BoardingPassDTO> addBoardingPassToUser(@PathVariable Long userId, @RequestBody BoardingPassDTO dto) {
        BoardingPassDTO boardingPass = boardingPassService.addBoardingPassToUser(userId, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(boardingPass.id()).toUri();
        return ResponseEntity.created(uri).body(boardingPass);
    }

    @GetMapping("/{userId}/boarding-passes")
    public ResponseEntity<List<BoardingPassDTO>> findBoardingPassesByUser(@PathVariable Long userId) {
        List<BoardingPassDTO> boardingPasses = boardingPassService.findBoardingPassesByUser(userId);
        return ResponseEntity.ok().body(boardingPasses);
    }

    @DeleteMapping("/{userId}/boarding-passes/{boardingPassId}")
    public ResponseEntity<Void> deleteBoardingPassFromUser(@PathVariable Long userId, @PathVariable Long boardingPassId) {
        boardingPassService.deleteBoardingPassFromUser(userId, boardingPassId);
        return ResponseEntity.noContent().build();
    }
}
