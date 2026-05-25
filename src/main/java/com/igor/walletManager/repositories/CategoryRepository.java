package com.igor.walletManager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.walletManager.entity.Category;
import com.igor.walletManager.entity.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByUser(User user);
	
}
