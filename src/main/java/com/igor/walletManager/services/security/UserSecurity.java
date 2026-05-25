package com.igor.walletManager.services.security;

import org.springframework.stereotype.Component;

import com.igor.walletManager.entity.User;
import com.igor.walletManager.entity.enums.UserRole;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserSecurity {

	private final SecurityService securityService;

	public boolean isOwnerOrAdmin(Long userId) {

	    User current = securityService.authenticated();

	    if (current.getRole() == UserRole.ADMIN) {
	        return true;
	    }

	    return current.getId().equals(userId);
	}

}
