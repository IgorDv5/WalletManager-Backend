package com.igor.walletManager.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.walletManager.dtos.users.UserCreateDTO;
import com.igor.walletManager.dtos.users.UserUpdateDTO;
import com.igor.walletManager.dtos.users.UserResponseDTO;
import com.igor.walletManager.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreateDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long id ,@Valid @RequestBody UserUpdateDTO dto ){
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@PatchMapping("/toggle/{id}")
	public ResponseEntity<Void> toggleUserDeleted(@PathVariable Long id){
		service.toggleUserDeleted(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

}
