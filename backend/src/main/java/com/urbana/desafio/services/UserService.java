package com.urbana.desafio.services;

import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.api.dtos.UserInsertDTO;
import com.urbana.desafio.domain.entities.BoardingPassType;
import com.urbana.desafio.domain.entities.User;
import com.urbana.desafio.domain.repositories.BoardingPassTypeRepository;
import com.urbana.desafio.domain.repositories.UserRepository;
import com.urbana.desafio.services.exceptions.DatabaseExceptions;
import com.urbana.desafio.services.exceptions.ResourcesNotFoundExceptions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private UserRepository repository;

    private BoardingPassTypeRepository boardingPassTypeRepository;

    public UserService(UserRepository repository, BoardingPassTypeRepository boardingPassTypeRepository) {
        this.repository = repository;
        this.boardingPassTypeRepository = boardingPassTypeRepository;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();

        return users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public UserInsertDTO insert(UserInsertDTO dto) {
        User entity = new User();

        copyDTOtoEnrity(dto, entity);

        entity = repository.save(entity);

        return new UserInsertDTO(entity);
    }

    @Transactional
    public UserInsertDTO update(Long id, UserInsertDTO dto) {
        try {
            User entity = repository.getReferenceById(id);

            copyDTOtoEnrity(dto, entity);

            entity = repository.save(entity);

            return new UserInsertDTO(entity);
        }
        catch (EntityNotFoundException e)
        {
            throw new ResourcesNotFoundExceptions("Id não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourcesNotFoundExceptions("id não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseExceptions("Violação Integridade do banco ");
        }
    }

    private void copyDTOtoEnrity(UserInsertDTO dto, User entity) {

        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());

        entity.getBoardingPassTypes().clear();

        for (BoardingPassType catDTO : dto.boardingPassTypes()) {
            BoardingPassType cat = boardingPassTypeRepository.findById(catDTO.getId())
                    .orElseThrow(() -> new RuntimeException("BoardingPassType not found"));
            entity.getBoardingPassTypes().add(cat);
        }
    }

}
