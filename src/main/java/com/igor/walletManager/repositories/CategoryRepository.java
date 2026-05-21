package com.igor.walletManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.walletManager.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
