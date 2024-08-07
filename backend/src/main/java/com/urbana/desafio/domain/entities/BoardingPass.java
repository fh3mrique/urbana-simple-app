package com.urbana.desafio.domain.entities;

import com.urbana.desafio.domain.enums.TypeBoardingPass;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_boarding_pass")
public class BoardingPass {

    @Id
    private Long id;

    private Long number;

    private Boolean status = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Boarding pass type cannot be null")
    private TypeBoardingPass typeBoardingPass;

    @ManyToMany(mappedBy = "boardingPassTypes")
    private Set<User> users = new HashSet<>();

    public BoardingPass() {

    }

    public BoardingPass(Long id, Long number, Boolean status, TypeBoardingPass typeBoardingPass) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.typeBoardingPass = typeBoardingPass;
    }

    public void active(){
        setStatus(true);
    }
    public void inactive(){
        setStatus(false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TypeBoardingPass getTypeBoardingPass() {
        return typeBoardingPass;
    }

    public void setTypeBoardingPass(TypeBoardingPass typeBoardingPass) {
        this.typeBoardingPass = typeBoardingPass;
    }


}
