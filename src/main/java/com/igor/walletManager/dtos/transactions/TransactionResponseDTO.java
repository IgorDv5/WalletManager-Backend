package com.igor.walletManager.dtos.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.igor.walletManager.entity.enums.TransactionType;

public record TransactionResponseDTO(

		Long id,

		BigDecimal amount,
		
		String description,

		LocalDateTime date,

		TransactionType type,

		LocalDateTime deletedAt,

		Long categoryId,

		Long userId

) {

}
