package com.igor.walletManager.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.walletManager.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	boolean existsByCategoryId(Long categoryId);
	
	List<Transaction> findByUserIdAndDateBetweenAndDeletedAtIsNull(
	        Long userId,
	        LocalDateTime start,
	        LocalDateTime end
	);
	
	List<Transaction> findByUserId(Long userId);
	
}
