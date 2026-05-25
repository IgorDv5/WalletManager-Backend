package com.igor.walletManager.services.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.igor.walletManager.dtos.auth.LoginRequestDTO;
import com.igor.walletManager.dtos.auth.LoginResponseDTO;
import com.igor.walletManager.entity.User;
import com.igor.walletManager.repositories.UserRepository;
import com.igor.walletManager.security.JwtService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    
    public LoginResponseDTO login(LoginRequestDTO dto) {

        User user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(
        		user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponseDTO(token);
    }
	
} 
