package com.igor.walletManager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.igor.walletManager.dtos.categories.CategoryRequestDTO;
import com.igor.walletManager.dtos.categories.CategoryResponseDTO;
import com.igor.walletManager.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	Category toEntity(CategoryRequestDTO dto);
	
	@Mapping(source = "user.id", target = "userId")
	CategoryResponseDTO toDTO(Category category);

}
