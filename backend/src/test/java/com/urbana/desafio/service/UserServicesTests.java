package com.urbana.desafio.service;


import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.api.dtos.UserInsertDTO;
import com.urbana.desafio.domain.entities.BoardingPassType;
import com.urbana.desafio.domain.entities.User;
import com.urbana.desafio.domain.repositories.BoardingPassTypeRepository;
import com.urbana.desafio.domain.repositories.UserRepository;
import com.urbana.desafio.services.UserService;
import com.urbana.desafio.utils.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServicesTests {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private BoardingPassTypeRepository boardingPassTypeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnAllUsersAsUserDTOs() {
        BoardingPassType type1 = new BoardingPassType(1L, "COMUM");
        BoardingPassType type2 = new BoardingPassType(2L, "ESTUDANTE");

        Set<BoardingPassType> boardingPassTypes = UserFactory.createBoardingPassTypes(type1, type2);

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
        BoardingPassType type1 = UserFactory.createBoardingPassType1();
        BoardingPassType type2 = UserFactory.createBoardingPassType2();

        Set<BoardingPassType> boardingPassTypes = UserFactory.createBoardingPassTypes(type1, type2);

        UserInsertDTO userInsertDTO = new UserInsertDTO(null, "John Doe", "john.doe@example.com", "password123", boardingPassTypes);

        User userEntity = UserFactory.createUser(null, "John Doe", "john.doe@example.com", "password123", boardingPassTypes);

        // Configurar os mocks
        when(boardingPassTypeRepository.findById(type1.getId())).thenReturn(Optional.of(type1));
        when(boardingPassTypeRepository.findById(type2.getId())).thenReturn(Optional.of(type2));
        when(repository.save(any(User.class))).thenReturn(userEntity);

        UserInsertDTO result = service.insert(userInsertDTO);

        assertEquals(userInsertDTO.name(), result.name());
        assertEquals(userInsertDTO.email(), result.email());
        assertEquals(userInsertDTO.password(), result.password());
/*
        assertEquals(userInsertDTO.boardingPassTypes().size(), result.boardingPassTypes().size());
*/
    }


}
