package com.igor.walletManager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.walletManager.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmailIgnoreCase(String email);
	
	Optional<User> findByEmail(String email);
	
}
