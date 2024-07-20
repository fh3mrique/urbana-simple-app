package com.urbana.desafio.domain.entities;

import com.urbana.desafio.domain.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    @ManyToMany
    @JoinTable
            (
                    name = "tb_user_passtype",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "passtype_id")
            )
    private Set<BoardingPass> boardingPassTypes = new HashSet<>();

    public User (){

    }
    public User(Long id, String name, String email, String password, Set<BoardingPass> boardingPassTypes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.boardingPassTypes = boardingPassTypes;
    }

    public User(String email, String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
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



    public void setPassword(String password) {
        this.password = password;
    }

    // O BoardingPassType não deve ter setter, pois a inclusão de
    // um catão deve ser feito por um endpoit
    public Set<BoardingPass> getBoardingPassTypes() {
        return boardingPassTypes;
    }

    public void setBoardingPassTypes(Set<BoardingPass> boardingPassTypes) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == userRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;

    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
