package com.urbana.desafio.domain.entities;

import com.urbana.desafio.domain.enums.TypeBoardingPass;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_boarding_pass")
public class BoardingPass {

    @Id
    private Long id;

    private Long number;

    private Boolean status = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    private TypeBoardingPass typeBoardingPass;

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
