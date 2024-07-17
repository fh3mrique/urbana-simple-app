package com.urbana.desafio.service;


import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.domain.entities.BoardingPassType;
import com.urbana.desafio.domain.entities.User;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServicesTests {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

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


}
