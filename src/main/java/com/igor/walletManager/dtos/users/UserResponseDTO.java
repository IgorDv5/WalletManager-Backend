package com.igor.walletManager.dtos.users;

import java.time.LocalDateTime;

import com.igor.walletManager.entity.enums.UserRole;

public record UserResponseDTO(
		
		Long id,
		
		String nome,
		
		String email,
		
		LocalDateTime deletedAt,
		
		UserRole role
		
		
		) {

}
