package com.urbana.desafio.domain.repositories;

import com.urbana.desafio.domain.entities.BoardingPassType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardingPassTypeRepository extends JpaRepository<BoardingPassType, Long> {
}
