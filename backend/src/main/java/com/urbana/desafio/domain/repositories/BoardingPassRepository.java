package com.urbana.desafio.domain.repositories;

import com.urbana.desafio.domain.entities.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardingPassRepository extends JpaRepository<BoardingPass, Long> {
}
