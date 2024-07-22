package com.urbana.desafio.services;

import com.urbana.desafio.api.dtos.BoardingPassDTO;
import com.urbana.desafio.domain.entities.BoardingPass;
import com.urbana.desafio.domain.entities.User;
import com.urbana.desafio.repositories.BoardingPassRepository;
import com.urbana.desafio.repositories.UserRepository;
import com.urbana.desafio.services.exceptions.ResourcesNotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardingPassService {

    private BoardingPassRepository repository;
    private UserRepository userRepository;

    public BoardingPassService(BoardingPassRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void activate(Long userId, Long boardingPassId) {
        BoardingPass boardingPass = findBoardingPass(userId, boardingPassId);
        boardingPass.setStatus(true);
    }

    @Transactional
    public void deactivate(Long userId, Long boardingPassId) {
        BoardingPass boardingPass = findBoardingPass(userId, boardingPassId);
        boardingPass.setStatus(false);
    }

    private BoardingPass findBoardingPass(Long userId, Long boardingPassId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourcesNotFoundExceptions("User not found"));
        return user.getBoardingPassTypes().stream()
                .filter(bp -> bp.getId().equals(boardingPassId))
                .findFirst()
                .orElseThrow(() -> new ResourcesNotFoundExceptions("BoardingPass not found"));
    }

    @Transactional
    public BoardingPassDTO addBoardingPassToUser(Long userId, BoardingPassDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundExceptions("User not found"));
        BoardingPass boardingPass = new BoardingPass();
        copyDtoToEntity(dto, boardingPass);
        boardingPass = repository.save(boardingPass);
        user.getBoardingPassTypes().add(boardingPass);
        userRepository.save(user);
        return new BoardingPassDTO(boardingPass);
    }

    @Transactional(readOnly = true)
    public List<BoardingPassDTO> findBoardingPassesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundExceptions("User not found"));
        return user.getBoardingPassTypes().stream().map(BoardingPassDTO::new).collect(Collectors.toList());
    }

    public void deleteBoardingPassFromUser(Long userId, Long boardingPassId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<BoardingPass> boardingPassOpt = repository.findById(boardingPassId);

        if (userOpt.isPresent() && boardingPassOpt.isPresent()) {
            User user = userOpt.get();
            BoardingPass boardingPass = boardingPassOpt.get();

            user.getBoardingPassTypes().remove(boardingPass);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário ou cartão de embarque não encontrado.");
        }
    }

    private void copyDtoToEntity(BoardingPassDTO dto, BoardingPass entity) {
        entity.setId(dto.id());
        entity.setNumber(dto.number());
        entity.setStatus(dto.status());
        entity.setTypeBoardingPass(dto.typeBoardingPass());
    }


}
