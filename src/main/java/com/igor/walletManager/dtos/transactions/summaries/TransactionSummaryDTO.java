package com.igor.walletManager.dtos.transactions.summaries;

import java.math.BigDecimal;

public record TransactionSummaryDTO(

		BigDecimal income, BigDecimal expense, BigDecimal balance) {

}
