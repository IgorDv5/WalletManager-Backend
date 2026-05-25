package com.igor.walletManager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.igor.walletManager.dtos.categories.CategoryRequestDTO;
import com.igor.walletManager.dtos.categories.CategoryResponseDTO;
import com.igor.walletManager.entity.Category;
import com.igor.walletManager.entity.User;
import com.igor.walletManager.exceptions.custom.ConflictException;
import com.igor.walletManager.exceptions.custom.ResourceNotFoundException;
import com.igor.walletManager.mappers.CategoryMapper;
import com.igor.walletManager.repositories.CategoryRepository;
import com.igor.walletManager.repositories.TransactionRepository;
import com.igor.walletManager.services.security.SecurityService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper mapper;
	private final TransactionRepository transactionRepository;
	private final SecurityService securityService;

	public CategoryResponseDTO findById(Long id) {
		Category category = findEntityById(id);
		return mapper.toDTO(category);
	}

	public List<CategoryResponseDTO> findAll() {
		User user = securityService.authenticated();

		List<Category> categories;

		if (isAdmin(user)) {
			categories = categoryRepository.findAll();
		} else {
			categories = categoryRepository.findByUser(user);
		}

		return categories.stream().map(mapper::toDTO).toList();
	}

	@Transactional
	public CategoryResponseDTO create(CategoryRequestDTO dto) {
		User user = securityService.authenticated();
		Category category = mapper.toEntity(dto);
		category.setUser(user);

		Category categoryCreated = categoryRepository.save(category);

		return mapper.toDTO(categoryCreated);
	}

	@Transactional
	public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {

		Category category = findEntityById(id);
		category.setName(dto.name());

		return mapper.toDTO(categoryRepository.save(category));
	}

	@Transactional
	public void delete(Long id) {

		Category category = findEntityById(id);

		boolean hasTransactions = transactionRepository.existsByCategoryId(id);

		if (hasTransactions) {
			throw new ConflictException("Você não pode deletar a categoria porque ela possui transações");
		}

		categoryRepository.delete(category);
	}

	private Category findEntityById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria Com Id: " + id + " Não Encntrada"));

		return category;
	}

	private boolean isAdmin(User user) {
		return user.getRole().name().equals("ADMIN");
	}

}
