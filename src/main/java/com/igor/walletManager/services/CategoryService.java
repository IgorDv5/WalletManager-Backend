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
import com.igor.walletManager.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper mapper;
	private final UserRepository userRepository;
	private final TransactionRepository transactionRepository;

	public CategoryResponseDTO findById(Long id) {
		Category category = findEntityById(id);

		return mapper.toDTO(category);
	}

	public List<CategoryResponseDTO> findAll() {
		return categoryRepository.findAll().stream().map(mapper::toDTO).toList();
	}

	public CategoryResponseDTO create(CategoryRequestDTO dto) {
		Category category = mapper.toEntity(dto);
		User user = userRepository.findById(dto.userId())
				.orElseThrow(() -> new ResourceNotFoundException("User Não Encontrado"));
		category.setUser(user);
		Category categoryCreated = categoryRepository.save(category);
		return mapper.toDTO(categoryCreated);
	}

	public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
		Category category = findEntityById(id);

		category.setName(dto.name());

		Category categoryUpdated = categoryRepository.save(category);

		return mapper.toDTO(categoryUpdated);
	}

	public void delete(Long id) {
		Category category = findEntityById(id);


		boolean hasTransactions = transactionRepository.existsByCategoryId(id);
		
	    if (hasTransactions) {
            throw new ConflictException("\"Você não pode deletar a categoria porque ela possui transações\"");
        }

        categoryRepository.delete(category);
    
	}

	private Category findEntityById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria Com Id: " + id + " Não Encntrada"));

		return category;
	}
}
