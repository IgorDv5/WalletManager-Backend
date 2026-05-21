package com.igor.walletManager.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(
		
		@NotBlank(message = "Campo Nome é Obrigatorio")
		String nome,
		
		@Email(message = "Email Invalido")
		@NotBlank(message = "Campo Email é Obrigatorio")
		String email
		) {

}
