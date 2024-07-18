package com.urbana.desafio.utils;


import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.api.dtos.UserInsertDTO;
import com.urbana.desafio.domain.entities.BoardingPassType;
import com.urbana.desafio.domain.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserFactory {

    public static User createUser(Long id, String name, String email, String password, Set<BoardingPassType> boardingPassTypes) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setBoardingPassTypes(boardingPassTypes);
        return user;
    }

    public static Set<BoardingPassType> createBoardingPassTypes(BoardingPassType... boardingPassTypes) {
        Set<BoardingPassType> types = new HashSet<>();
        for (BoardingPassType type : boardingPassTypes) {
            types.add(type);
        }
        return types;
    }

    public static BoardingPassType createBoardingPassType1() {
        return new BoardingPassType(1L, "Economy");
    }

    public static BoardingPassType createBoardingPassType2() {
        return new BoardingPassType(2L, "Business");
    }

    public static Set<BoardingPassType> createDefaultBoardingPassTypes() {
        Set<BoardingPassType> types = new HashSet<>();
        types.add(createBoardingPassType1());
        types.add(createBoardingPassType2());
        return types;
    }

    public static User createUser1() {
        Set<BoardingPassType> boardingPassTypes = createDefaultBoardingPassTypes();
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setBoardingPassTypes(boardingPassTypes);
        return user;
    }

    public static User createUser2() {
        Set<BoardingPassType> boardingPassTypes = createDefaultBoardingPassTypes();
        User user = new User();
        user.setId(2L);
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("password456");
        user.setBoardingPassTypes(boardingPassTypes);
        return user;
    }

    public static UserDTO createUserDTO1() {
        return new UserDTO(createUser1());
    }

    public static UserDTO createUserDTO2() {
        return new UserDTO(createUser2());
    }

    public static UserInsertDTO createUserInsertDTO(Long id, String name, String email, String password, Set<BoardingPassType> boardingPassTypes) {
        return new UserInsertDTO(id, name, email, password, boardingPassTypes);
    }

    public static UserInsertDTO createDefaultUserInsertDTO() {
        Set<BoardingPassType> defaultBoardingPassTypes = createDefaultBoardingPassTypes();
        return new UserInsertDTO(1L, "Default User", "default@example.com", "defaultPassword", defaultBoardingPassTypes);
    }
}
