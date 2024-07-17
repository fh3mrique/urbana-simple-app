package com.urbana.desafio.domain.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;


    @ManyToMany
    @JoinTable
            (
                    name = "tb_user_passtype",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "passtype_id")
            )
    private Set<BoardingPassType> boardingPassTypes = new HashSet<>();

    public User (){

    }
    public User(Long id, String name, String email, String password, Set<BoardingPassType> boardingPassTypes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.boardingPassTypes = boardingPassTypes;
    }

    //Escolhir não usar Lombok para garantir o que o projeto rode em todas as
    // IDE´s sem a necessidade de fazer outras configuração.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // O BoardingPassType não deve ter setter, pois a inclusão de
    // um catão deve ser feito por um endpoit
    public Set<BoardingPassType> getBoardingPassTypes() {
        return boardingPassTypes;
    }

    public void setBoardingPassTypes(Set<BoardingPassType> boardingPassTypes) {
    }
}
