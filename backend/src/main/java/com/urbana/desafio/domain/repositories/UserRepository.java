package com.urbana.desafio.domain.repositories;

import com.urbana.desafio.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
