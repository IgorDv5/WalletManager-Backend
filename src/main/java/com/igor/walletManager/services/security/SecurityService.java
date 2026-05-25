package com.igor.walletManager.services.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.igor.walletManager.entity.User;

@Service
public class SecurityService {
	
	
	public User authenticated() {

	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated()) {
	        throw new RuntimeException("Usuário Não Autenticado");
	    }

	    return (User) authentication.getPrincipal();
	}
	

}
