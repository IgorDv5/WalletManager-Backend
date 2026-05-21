package com.igor.walletManager.dtos.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.igor.walletManager.entity.enums.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionUpdateDTO(
		
		@NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser maior que zero")
        BigDecimal amount,

        @NotBlank(message = "Descrição é obrigatória")
        String description,

        
        LocalDateTime date,

        @NotNull(message = "Tipo da transação é obrigatório")
        TransactionType type,

        @NotNull(message = "Categoria é obrigatória")
        Long categoryId

		) {

}
