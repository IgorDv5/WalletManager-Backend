package com.igor.walletManager.services.security;

import org.springframework.stereotype.Component;

import com.igor.walletManager.entity.User;
import com.igor.walletManager.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionSecurity {

	private final SecurityService securityService;
	private final TransactionRepository repository;

	public boolean isOwner(Long transactionId) {

		User current = securityService.authenticated();

		return repository.findById(transactionId).map(t -> t.getUser().getId().equals(current.getId())).orElse(false);
	}

	public boolean isOwnerOfUser(Long userId) {

		User current = securityService.authenticated();

		return current.getId().equals(userId);
	}

}
