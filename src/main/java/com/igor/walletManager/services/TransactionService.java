package com.igor.walletManager.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.igor.walletManager.dtos.transactions.TransactionCreateDTO;
import com.igor.walletManager.dtos.transactions.TransactionResponseDTO;
import com.igor.walletManager.dtos.transactions.TransactionUpdateDTO;
import com.igor.walletManager.dtos.transactions.summaries.TransactionSummaryDTO;
import com.igor.walletManager.entity.Category;
import com.igor.walletManager.entity.Transaction;
import com.igor.walletManager.entity.User;
import com.igor.walletManager.entity.enums.TransactionType;
import com.igor.walletManager.exceptions.custom.ResourceNotFoundException;
import com.igor.walletManager.mappers.TransactionMapper;
import com.igor.walletManager.repositories.CategoryRepository;
import com.igor.walletManager.repositories.TransactionRepository;
import com.igor.walletManager.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final TransactionRepository transactionRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;
	private final TransactionMapper mapper;

	public TransactionResponseDTO findById(Long id) {
		Transaction transaction = findEntityById(id);
		return mapper.toDTO(transaction);
	}

	public List<TransactionResponseDTO> findAll() {
		return transactionRepository.findAll().stream().map(mapper::toDTO).toList();
	}

	@Transactional
	public TransactionResponseDTO create(TransactionCreateDTO dto) {
		Transaction transaction = mapper.toEntity(dto);
		Category category = findCategoryById(dto.categoryId());

		User user = findUserById(dto.userId());

		transaction.setCategory(category);
		transaction.setUser(user);

		if (dto.date() == null) {
			transaction.setDate(LocalDateTime.now());
		}

		Transaction transactionCreated = transactionRepository.save(transaction);
		return mapper.toDTO(transactionCreated);
	}

	@Transactional
	public TransactionResponseDTO update(Long id, TransactionUpdateDTO dto) {
		Transaction transaction = findEntityById(id);
		Category category = findCategoryById(dto.categoryId());

		transaction.setAmount(dto.amount());
		transaction.setDescription(dto.description());
		transaction.setDate(dto.date());
		transaction.setType(dto.type());
		transaction.setCategory(category);

		Transaction transactionCreated = transactionRepository.save(transaction);
		return mapper.toDTO(transactionCreated);
	}

	@Transactional
	public void toggleSoftDelete(Long id) {
		Transaction transaction = findEntityById(id);

		if (transaction.getDeletedAt() == null) {
			transaction.setDeletedAt(LocalDateTime.now());
		} else {
			transaction.setDeletedAt(null);
		}

		transactionRepository.save(transaction);
	}
	

	public TransactionSummaryDTO getSummary(Long userId, LocalDate start, LocalDate end) {

	    LocalDateTime startDateTime = start.atStartOfDay();
	    LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

	    List<Transaction> transactions =
	            transactionRepository.findByUserIdAndDateBetweenAndDeletedAtIsNull(
	                    userId,
	                    startDateTime,
	                    endDateTime
	            );

	    BigDecimal income = BigDecimal.ZERO;
	    BigDecimal expense = BigDecimal.ZERO;

	    for (Transaction t : transactions) {
	        if (t.getType() == TransactionType.INCOME) {
	            income = income.add(t.getAmount());
	        } else {
	            expense = expense.add(t.getAmount());
	        }
	    }

	    return new TransactionSummaryDTO(
	            income,
	            expense,
	            income.subtract(expense)
	    );
	}

	// *****************
	// Metodo Privados!
	// *****************

	private Transaction findEntityById(Long id) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Transação Com Id:" + id + " Não Encontrada"));

		return transaction;
	}

	private Category findCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

		return category;
	}

	private User findUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Nao Encontrado"));

		return user;
	}
}
