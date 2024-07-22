package com.urbana.desafio.utils;


import com.urbana.desafio.api.dtos.UserDTO;
import com.urbana.desafio.api.dtos.UserInsertUpdateDTO;
import com.urbana.desafio.domain.entities.BoardingPass;
import com.urbana.desafio.domain.entities.User;
import com.urbana.desafio.domain.enums.TypeBoardingPass;

import java.util.HashSet;
import java.util.Set;

public class UserFactory {

    public static User createUser(Long id, String name, String email, String password, Set<BoardingPass> boardingPassTypes) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public static Set<BoardingPass> createBoardingPassTypes(BoardingPass... boardingPassTypes) {
        Set<BoardingPass> types = new HashSet<>();
        for (BoardingPass type : boardingPassTypes) {
            types.add(type);
        }
        return types;
    }

    public static BoardingPass createBoardingPassType1() {
        return new BoardingPass(1L, 24534l, true, TypeBoardingPass.COMUM);
    }

    public static BoardingPass createBoardingPassType2() {

        return new BoardingPass(2L, 24534l, true, TypeBoardingPass.ESTUDANTE);
    }

    public static Set<BoardingPass> createDefaultBoardingPassTypes() {
        Set<BoardingPass> types = new HashSet<>();
        types.add(createBoardingPassType1());
        types.add(createBoardingPassType2());
        return types;
    }

    public static User createUser1() {
        Set<BoardingPass> boardingPassTypes = createDefaultBoardingPassTypes();
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        return user;
    }

    public static User createUser2() {
        Set<BoardingPass> boardingPassTypes = createDefaultBoardingPassTypes();
        User user = new User();
        user.setId(2L);
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("password456");
        return user;
    }

    public static UserInsertUpdateDTO createUserInsertUpdateDTO() {
        return new UserInsertUpdateDTO(1L, "Default User", "default@example.com", "password123");
    }

    public static UserDTO createUserDTO1() {
        return new UserDTO(createUser1());
    }

    public static UserDTO createUserDTO2() {
        return new UserDTO(createUser2());
    }

   /* public static UserInsertUpdateDTO createUserInsertDTO(Long id, String name, String email, String password, Set<BoardingPass> boardingPassTypes) {
        return new UserInsertUpdateDTO(1, 'arthur', 'er@gmail', "");
    }*/

    public static UserInsertUpdateDTO createDefaultUserInsertDTO() {
        Set<BoardingPass> defaultBoardingPassTypes = createDefaultBoardingPassTypes();
        return new UserInsertUpdateDTO(1L, "Default User", "default@example.com", "defaultPassword");
    }
}
