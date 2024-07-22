package com.urbana.desafio.auth.service;

import com.urbana.desafio.api.dtos.AuthetinticationDTO;
import com.urbana.desafio.api.dtos.LoginResponseDTO;
import com.urbana.desafio.api.dtos.RegisterDTO;
import com.urbana.desafio.domain.entities.User;
import com.urbana.desafio.repositories.UserRepository;
import com.urbana.desafio.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@Service
public class AuthorizationService implements UserDetailsService{
    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<Object> login(@RequestBody @Valid AuthetinticationDTO data){
        authenticationManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        // Adiciona o token no cabeçalho Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        // Retorna a resposta com o cabeçalho Authorization
        return ResponseEntity.ok().headers(headers).body(new LoginResponseDTO(token));
    }

    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDto){
        if (this.userRepository.findByEmail(registerDto.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

        User newUser = new User(registerDto.email(), encryptedPassword, registerDto.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
