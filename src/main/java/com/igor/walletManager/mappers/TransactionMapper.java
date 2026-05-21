package com.igor.walletManager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.igor.walletManager.dtos.transactions.TransactionCreateDTO;
import com.igor.walletManager.dtos.transactions.TransactionResponseDTO;
import com.igor.walletManager.dtos.transactions.TransactionUpdateDTO;
import com.igor.walletManager.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

	Transaction toEntity(TransactionCreateDTO dto);
	Transaction toEntity(TransactionUpdateDTO dto);

	@Mapping(source = "user.id",target = "userId")
	@Mapping(source = "category.id",target = "categoryId")
	TransactionResponseDTO toDTO(Transaction entity);

}
