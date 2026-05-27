package com.igor.walletManager.controllers;

import com.igor.walletManager.dtos.users.UserCreateDTO;
import com.igor.walletManager.dtos.users.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.walletManager.dtos.auth.LoginRequestDTO;
import com.igor.walletManager.dtos.auth.LoginResponseDTO;
import com.igor.walletManager.services.security.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
		return ResponseEntity.ok(authService.login(dto));
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserCreateDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(dto));
	}

}
