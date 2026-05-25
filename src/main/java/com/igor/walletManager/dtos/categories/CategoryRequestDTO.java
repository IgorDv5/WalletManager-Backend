package com.igor.walletManager.dtos.categories;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
		
		@NotBlank(message = "Campo Nome é Obrigatorio")
		String name
		
		) {

}
