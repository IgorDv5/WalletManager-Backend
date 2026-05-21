package com.igor.walletManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.walletManager.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	boolean existsByCategoryId(Long categoryId);
	
}
