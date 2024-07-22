package com.urbana.desafio.services;

import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.api.dtos.UserInsertUpdateDTO;
import com.urbana.desafio.domain.entities.User;
import com.urbana.desafio.repositories.BoardingPassRepository;
import com.urbana.desafio.repositories.UserRepository;
import com.urbana.desafio.services.exceptions.DatabaseExceptions;
import com.urbana.desafio.services.exceptions.ResourcesNotFoundExceptions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    private UserRepository repository;

    private BoardingPassRepository boardingPassTypeRepository;

    public UserService(UserRepository repository, BoardingPassRepository boardingPassTypeRepository) {
        this.repository = repository;
        this.boardingPassTypeRepository = boardingPassTypeRepository;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();

        return users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public UserInsertUpdateDTO insert(UserInsertUpdateDTO dto) {
        User entity = new User();

        copyDTOtoEnrity(dto, entity);

        entity = repository.save(entity);

        return new UserInsertUpdateDTO(entity);
    }

    @Transactional
    public UserInsertUpdateDTO update(Long id, UserInsertUpdateDTO dto) {
        try {
            User entity = repository.getReferenceById(id);

            copyDTOtoEnrity(dto, entity);

            entity = repository.save(entity);

            return new UserInsertUpdateDTO(entity);
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

    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow( () -> new ResourcesNotFoundExceptions("Entidade não encontrada"));
        return new UserDTO(entity);
    }

    private void copyDTOtoEnrity(UserInsertUpdateDTO dto, User entity) {
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
    }


}
