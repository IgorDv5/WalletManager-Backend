package com.igor.walletManager.services.security;

import org.springframework.stereotype.Component;

import com.igor.walletManager.entity.User;
import com.igor.walletManager.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategorySecurity {

	private final CategoryRepository categoryRepository;
	private final SecurityService securityService;

	public boolean isOwner(Long categoryId) {
		User user = securityService.authenticated();

		return categoryRepository.findById(categoryId).map(cat -> cat.getUser().getId().equals(user.getId()))
				.orElse(false);
	}
}
