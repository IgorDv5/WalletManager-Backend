package com.igor.walletManager.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igor.walletManager.dtos.transactions.TransactionCreateDTO;
import com.igor.walletManager.dtos.transactions.TransactionResponseDTO;
import com.igor.walletManager.dtos.transactions.TransactionUpdateDTO;
import com.igor.walletManager.dtos.transactions.summaries.TransactionSummaryDTO;
import com.igor.walletManager.services.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "transactions")
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService service;

	@PreAuthorize("hasRole('ADMIN') or @transactionSecurity.isOwner(#id)")
	@GetMapping("/{id}")
	public ResponseEntity<TransactionResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping
	public ResponseEntity<List<TransactionResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping
	public ResponseEntity<TransactionResponseDTO> create(@Valid @RequestBody TransactionCreateDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}

	@PreAuthorize("hasRole('ADMIN') or @transactionSecurity.isOwner(#id)")
	@PutMapping("/{id}")
	public ResponseEntity<TransactionResponseDTO> update(@PathVariable Long id,
			@Valid @RequestBody TransactionUpdateDTO dto) {
		service.update(id, dto);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasRole('ADMIN') or @transactionSecurity.isOwner(#id)")
	@PatchMapping("/{id}/toggle")
	public ResponseEntity<Void> toggleSoftDelete(@PathVariable Long id) {
		service.toggleSoftDelete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/summary")
	public ResponseEntity<TransactionSummaryDTO> summaryByPeriod(@RequestParam LocalDate start,
			@RequestParam LocalDate end) {
		return ResponseEntity.ok(service.getSummary(start, end));
	}

}
