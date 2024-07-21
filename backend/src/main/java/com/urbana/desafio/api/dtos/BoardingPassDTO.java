package com.urbana.desafio.api.dtos;

import com.urbana.desafio.domain.entities.BoardingPass;
import com.urbana.desafio.domain.enums.TypeBoardingPass;

public record BoardingPassDTO(Long id, Long number, Boolean status, TypeBoardingPass typeBoardingPass) {
    public BoardingPassDTO(BoardingPass entity) {
        this(entity.getId(), entity.getNumber(), entity.getStatus(), entity.getTypeBoardingPass());
    }
}