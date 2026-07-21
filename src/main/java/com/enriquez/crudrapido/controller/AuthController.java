package com.enriquez.crudrapido.controller;

import com.enriquez.crudrapido.dto.AuthResponse;
import com.enriquez.crudrapido.dto.LoginRequest;
import com.enriquez.crudrapido.dto.RegisterRequest;
import com.enriquez.crudrapido.entity.User;
import com.enriquez.crudrapido.repository.UserRepository;
import com.enriquez.crudrapido.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya existe.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya está registrado.");
        }

        User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito.");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );

        String token = jwtUtils.generateToken(request.getUserName());

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .userName(request.getUserName())
                .message("Autenticación exitosa")
                .build();

        return ResponseEntity.ok(response);
    }
}