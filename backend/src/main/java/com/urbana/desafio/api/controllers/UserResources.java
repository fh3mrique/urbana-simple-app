package com.urbana.desafio.api.controllers;

import com.urbana.desafio.api.dtos.BoardingPassDTO;
import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.api.dtos.UserInsertUpdateDTO;
import com.urbana.desafio.services.BoardingPassService;
import com.urbana.desafio.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/users", produces = "application/json")
@CrossOrigin(origins = "*")
@Tag(name = "RECURSOS DE USUÁRIOS")
public class UserResources {

    private UserService service;

    private BoardingPassService boardingPassService;

    public UserResources(UserService service, BoardingPassService boardingPassService) {
        this.service = service;
        this.boardingPassService = boardingPassService;
    }

    @Operation(summary = "Realiza uma busca por todos os usuários cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornou todos os usuários com sucesso."),
            @ApiResponse(responseCode = "500", description = "Servidor fora do ar ou indisponivel.")
    })
    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> users = service.findAll();
        return  ResponseEntity.ok().body(users);
    }

    @PostMapping
    @Operation(summary = "INSERE UM NOVO USUÁRIO NA API", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inseriu um novo usuário com sucesso."),
            @ApiResponse(responseCode = "500", description = "Servidor fora do ar ou indisponivel.")
    })
    public ResponseEntity<UserInsertUpdateDTO> insert(
            @Parameter( name = "Corpo", description = "ID usuário que será atualizado", required = true) @Valid @RequestBody UserInsertUpdateDTO dto){

        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "ATUALIZA UM USUÁRIO EXISTENTE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserInsertUpdateDTO> update(
            @Parameter(description = "ID usuário que será atualizado", required = true, example = "1") @Valid @PathVariable Long id,
            @Parameter(description = "Corpo do usuário que será atualizado", required = true) @RequestBody UserInsertUpdateDTO dto) {

        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {

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
