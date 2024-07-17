package com.urbana.desafio.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_boarding-pass")
public class BoardingPassType {

    @Id
    private Long id;
    private String typeBoard;

    public BoardingPassType(Long id, String typeBoard) {
        this.id = id;
        this.typeBoard = typeBoard;
    }

    public BoardingPassType(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeBoard() {
        return typeBoard;
    }

    public void setTypeBoard(String typeBoard) {
        this.typeBoard = typeBoard;
    }
}
