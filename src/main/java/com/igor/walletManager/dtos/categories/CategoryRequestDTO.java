package com.igor.walletManager.dtos.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(
		
		@NotBlank(message = "Campo Nome é Obrigatorio")
		String name,
		
		@NotNull(message = "Campo User é obrigatorio")
		Long userId
		) {

}
