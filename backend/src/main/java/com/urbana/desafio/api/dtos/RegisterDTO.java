package com.urbana.desafio.api.dtos;

import com.urbana.desafio.domain.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String email, @NotNull String password, @NotNull UserRole role ) {

}
