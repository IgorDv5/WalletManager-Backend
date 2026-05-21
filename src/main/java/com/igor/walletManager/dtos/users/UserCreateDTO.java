package com.igor.walletManager.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(
		
		@NotBlank(message = "Campo Nome é Obrigatorio")
		String nome,
		
		@Email(message = "Email Invalido")
		@NotBlank(message = "Campo Nome é Obrigatorio")
		String email,
		@NotBlank(message = "Campo Senha é Obrigatorio")
		@Size(min = 6 , message = "Senha Não preenche Requesitos Mínimos")
		String password
		) {

}
