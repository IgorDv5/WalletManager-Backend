package com.igor.walletManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.walletManager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
	
}
