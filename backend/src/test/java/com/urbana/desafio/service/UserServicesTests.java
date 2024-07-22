package com.urbana.desafio.service;


import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.api.dtos.UserInsertUpdateDTO;
import com.urbana.desafio.domain.entities.BoardingPass;
import com.urbana.desafio.domain.entities.User;
import com.urbana.desafio.repositories.BoardingPassRepository;
import com.urbana.desafio.repositories.UserRepository;
import com.urbana.desafio.services.UserService;
import com.urbana.desafio.services.exceptions.DatabaseExceptions;
import com.urbana.desafio.services.exceptions.ResourcesNotFoundExceptions;
import com.urbana.desafio.utils.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServicesTests {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private BoardingPassRepository boardingPassTypeRepository;

    private Long idExistente;
    private Long idNaoExistente;
    private Long idDependente;

    private UserInsertUpdateDTO dto;
    private BoardingPass boardingPass;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = 1L;
        idNaoExistente = 1000L;
        idDependente = 4L;

        /*dto = Factory.criarProdutoDTO();*/
        dto = UserFactory.createDefaultUserInsertDTO();
        boardingPass = UserFactory.createBoardingPassType1();
    }

    @Test
    public void shouldReturnAllUsersAsUserDTOs() {
        BoardingPass type1 = boardingPass;
        BoardingPass type2 = boardingPass;

        Set<BoardingPass> boardingPassTypes = UserFactory.createBoardingPassTypes(type1, type2);

        User user1 = UserFactory.createUser(1L, "John Doe", "john.doe@example.com", "password123", boardingPassTypes);
        User user2 = UserFactory.createUser(2L, "Jane Doe", "jane.doe@example.com", "password456", boardingPassTypes);

        when(repository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDTO> users = service.findAll();

        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).name());
        assertEquals("Jane Doe", users.get(1).name());
    }

    @Test
    public void shouldInsertUserAndReturnUserInsertDTO() {
        BoardingPass type1 = UserFactory.createBoardingPassType1();
        BoardingPass type2 = UserFactory.createBoardingPassType2();

        Set<BoardingPass> boardingPassTypes = UserFactory.createBoardingPassTypes(type1, type2);

        UserInsertUpdateDTO userInsertDTO = new UserInsertUpdateDTO( 1l, "John Doe", "john.doe@example.com", "password123");

        User userEntity = UserFactory.createUser(null, "John Doe", "john.doe@example.com", "password123", boardingPassTypes);

        when(boardingPassTypeRepository.findById(type1.getId())).thenReturn(Optional.of(type1));
        when(boardingPassTypeRepository.findById(type2.getId())).thenReturn(Optional.of(type2));
        when(repository.save(any(User.class))).thenReturn(userEntity);

        UserInsertUpdateDTO result = service.insert(userInsertDTO);

        assertEquals(userInsertDTO.name(), result.name());
        assertEquals(userInsertDTO.email(), result.email());
        assertEquals(userInsertDTO.password(), result.password());
/*
        assertEquals(userInsertDTO.boardingPassTypes().size(), result.boardingPassTypes().size());
*/
    }

    @Test
    public void deleteShouldFazerNadaWhenIdExistir() {
        Assertions.assertDoesNotThrow(()->{
            repository.deleteById(idExistente);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(idExistente);
    }

    @Test
    public void deleteShouldLancaDatabaseExceptionWhenIdForDependente() {
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(idDependente);

        assertThrows(DatabaseExceptions.class, () -> {
            service.delete(idDependente);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(idDependente);
    }

    @Test
    public void shouldThrowResourcesNotFoundExceptionsWhenDeletingNonExistentId() {
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(idNaoExistente);

        assertThrows(ResourcesNotFoundExceptions.class, () -> {
            service.delete(idNaoExistente);
        });
    }

}
